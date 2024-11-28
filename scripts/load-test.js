import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  stages: [
    { duration: '1m', target: 1000 }, // 1분 동안 1000명의 유저를 유지
  ],
  thresholds: {
    http_req_duration: ['p(95)<500'], // 95%의 요청이 500ms 이하로 응답해야 함
  },
};

export default function () {
  // 여기에 테스트할 API URL을 입력하세요
  const url = 'http://localhost:8080/api/v1/points/';

  // 필요한 경우 헤더 추가
  const headers = {
    'Content-Type': 'application/json',
  };

  // 요청 데이터 (필요하다면 수정)
  const payload = JSON.stringify({
      userId: Math.floor(Math.random() * 50) + 1,
      amount: 100
  });

  // HTTP POST 요청
  const res = http.patch(url, payload, { headers });

  // 응답 결과 로깅 (옵션)
  console.log(`Response status: ${res.status}`);

  // 요청 간격 조절
  sleep(1);
}
