package com.yany.common.kafka.bean;

/**
 * Created by yanyong on 2017/2/14.
 */
public interface IKafkaProducerService {
    public void sendMessage(String topic, String data);
}

