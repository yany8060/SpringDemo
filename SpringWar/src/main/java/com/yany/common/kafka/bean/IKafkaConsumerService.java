package com.yany.common.kafka.bean;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * Created by yanyong on 2017/2/15.
 */
public interface IKafkaConsumerService {
    public void onMessage(ConsumerRecord<Integer, String> record);
}
