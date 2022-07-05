package com.example.demo.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaSampleProducerService {
	
	/**
	 * application.yml에 설정해놓은 kafka 서버로 바로 통신할 수 있게 해주는 역할을 한다. 
	 */
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(String message) {
		System.out.println("send message : "+message);
		this.kafkaTemplate.send("oingdaddy", message);   // 첫번째 파라미터 : topic, 두번째 파라미터 : 실제로 보낼 메시지 
	}
}
