package com.yany.common.kafka.bean.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;


/**
 * Created by yanyong on 2017/2/15.
 */
public class KafkaConsumerServiceImpl implements MessageListener<Integer, String> {
    private final static Logger logger = LoggerFactory.getLogger(KafkaConsumerServiceImpl.class);


    @Override
    public void onMessage(ConsumerRecord<Integer, String> integerStringConsumerRecord) {
        logger.info("[kafka] msg:{}",integerStringConsumerRecord);
    }
}
