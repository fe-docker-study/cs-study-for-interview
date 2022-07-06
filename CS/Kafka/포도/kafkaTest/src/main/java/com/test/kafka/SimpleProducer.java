package com.test.kafka;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {
    private static KafkaProducer<String, String> producer;
    private final Properties props = new Properties();

    public SimpleProducer(){
        // 카프카 브로커에 접속하기 위한 속성값
        props.put("bootstrap.servers", "localhost:9092"); // kafka host 및 server 설정
        props.put("acks", "all"); // 자신이 보낸 메시지에 대해 카프카로부터 확인을 기다리지 않음
        props.put("block.on.buffer.full", "true"); // 서버로 보낼 레코드를 버퍼링 할 때 사용할 수 있는 전체 메모리의 바이트수
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");   // serialize 설정
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer"); // serialize 설정

        // producer 생성
        producer = new KafkaProducer<String, String>(props); // 해당 config 정보를 프로듀서에게 전달
    }

    public static void main(String[] args) {
        SimpleProducer sp = new SimpleProducer();

        // message 전달
        for(int i = 0; i < 5; i++){
            String v = "hello " + i;
            producer.send(new ProducerRecord<String, String>("quickstart-events", v)); // quickstart-events 토픽에 v에 있는 데이터가 들어가게 됨
        }

        producer.flush();
        producer.close();
    }
}
