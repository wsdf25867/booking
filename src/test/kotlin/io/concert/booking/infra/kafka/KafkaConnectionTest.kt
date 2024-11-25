import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.NewTopic
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.utility.DockerImageName
import java.time.Duration
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KafkaConnectionTest {

    private val kafkaContainer = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"))
        .apply { this.start() }

    private val producerProperties = Properties().apply {
        this[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaContainer.bootstrapServers
        this[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
        this[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name
    }
    private val consumerProperties = Properties().apply {
        this[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaContainer.bootstrapServers
        this[ConsumerConfig.GROUP_ID_CONFIG] = "test-group"
        this[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        this[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
        this[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
    }


    @BeforeAll
    fun setUp() {
        val adminClient = AdminClient.create(producerProperties)
        adminClient.createTopics(listOf(NewTopic(TOPIC, 1, 1))).all().get()
        adminClient.close()
    }

    @AfterAll
    fun tearDown() {
        kafkaContainer.stop()
    }

    @Test
    fun `카프카 이벤트 발행 소비 테스트`() {
        KafkaProducer<String, String>(producerProperties).use {
            it.send(ProducerRecord(TOPIC, "key", "message")).get()
        }

        KafkaConsumer<String, String>(consumerProperties).use {
            it.subscribe(listOf(TOPIC))
            val records = it.poll(Duration.ofSeconds(10))
            Assertions.assertEquals(1, records.count())
            Assertions.assertEquals("message", records.first().value())
        }
    }

    companion object {
        const val TOPIC = "test-topic"
    }
}
