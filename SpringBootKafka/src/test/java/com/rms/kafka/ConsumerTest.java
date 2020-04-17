package com.rms.kafka;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

public class ConsumerTest {
	public  static void manualOffsetCommitOfPartition() {
	    Properties props = new Properties();
	    props.put("bootstrap.servers", "39.107.238.105:9092");
	    props.put("group.id", "test");
	    props.put("enable.auto.commit", "false");
	    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

	    KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
	    consumer.subscribe(Arrays.asList("SnapReq01"));

	    boolean running = true;
	    try {
	        while (running) {
	            ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
	            for (TopicPartition partition : records.partitions()) {
	                List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
	                for (ConsumerRecord<String, String> record : partitionRecords) {
	                    System.out.println(record.topic()+"_"+record.offset() + "\n" + record.value());
	                }
	                long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
	                consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
	            }
	        }
	    } finally {
	        consumer.close();
	    }
	}
	public static void main(String[] args) {
		ConsumerTest.manualOffsetCommitOfPartition();
	}
}
