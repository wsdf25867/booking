package io.concert.booking.domain.user

class FakeUserRepository : UserRepository {
    private val store: MutableMap<Long, User> = mutableMapOf()
    private var sequence: Long = 1

    override fun findById(id: Long): User? {
        return store[id]
    }

    override fun save(user: User): User {
        val toSave = User(user.name, sequence++)
        store[toSave.id] = toSave
        return toSave
    }

}
