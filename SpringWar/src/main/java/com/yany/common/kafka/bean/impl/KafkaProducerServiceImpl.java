package com.yany.common.kafka.bean.impl;

import com.yany.common.kafka.bean.IKafkaProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by yanyong on 2017/2/14.
 */
@Component
public class KafkaProducerServiceImpl implements IKafkaProducerService {

    @Resource
    private KafkaTemplate kafkaTemplate;

    @Override
    public void sendMessage(String topic, String data) {
        kafkaTemplate.send(topic, data);
    }

}
