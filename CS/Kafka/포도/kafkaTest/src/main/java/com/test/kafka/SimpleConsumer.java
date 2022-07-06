package com.test.kafka;

import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

public class SimpleConsumer {
    private static KafkaConsumer<String, String> consumer;
    private final Properties props = new Properties();

    public SimpleConsumer(){
        props.put("bootstrap.servers", "localhost:9092"); // kafka server host 및 port (실제 사용하는 경우 여러 개를 지정하는 것 추천)
        props.put("session.timeout.ms", "10000"); // session 설정
        props.put("group.id", "quickstart-events"); // topic 설정
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // key deserializer
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer"); // value deserializer

        consumer = new KafkaConsumer<String, String>(props); // consumer 생성

        consumer.subscribe(Arrays.asList("quickstart-events")); // topic 설정
    }

    public static void main(String[] args) {
        new SimpleConsumer();

        while (true){
            consumer.subscribe(Arrays.asList("quickstart-events")); // 전체 파티션이 아니라 일부 파티션의 데이터만 가지고 오고 싶은 경우 assign() 메서드 사용

            ConsumerRecords<String, String> records = consumer.poll(500); // 폴링루프 : poll 메서드가 포함된 무한루프 (컨슈머 API의 핵심 로직)
            // 브로커에서 연속적으로, 컨슈머가 허락하는 한 만은 데이터를 읽는 것을 목적으로 함
            // 500ms 동안 데이터가 도착하기를 기다리고 이후 코드를 실행, 만약 데이터가 도착하지 않으면 빈 값의 records를 반환

            for(ConsumerRecord<String, String> record : records){
                String input = record.topic();
                if("quickstart-events".equals(input)){
                    System.out.println(record.value());
                } else {
                    throw new IllegalStateException("get message on topic" + record.topic());
                }
            }
        }
    }

}
