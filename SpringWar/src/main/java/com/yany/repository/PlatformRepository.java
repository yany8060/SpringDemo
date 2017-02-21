package com.yany.repository;

import com.mongodb.DBCollection;
import com.yany.model.PlatformModel;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by yanyong on 2017/2/9.
 */
@Repository
public class PlatformRepository {

    @Resource(name = "mongoTemplate")
    private MongoTemplate mongoTemplate;


    public List<PlatformModel> getAllPlatForm() {
        List<PlatformModel> platformModelList = mongoTemplate.findAll(PlatformModel.class);

        DBCollection dbCollection = mongoTemplate.getCollection("yanyPlatform");
        System.out.println(dbCollection.count());


//        PlatformModel platformModel = new PlatformModel();
//
//        platformModel.setId(UUID.randomUUID().toString().replace("-",""));
//        platformModel.setPlatName("yanyPF");
//        platformModel.setPlatCode(UUID.randomUUID().toString().replace("-",""));
//        platformModel.setPlatPrefix("yany");
//        platformModel.setPlatType("pc");
//        platformModel.setPlatVersion("1.0.0");
//        platformModel.setDelFlag(1);
//        platformModel.setAddTime(new Date().getTime());
//        mongoTemplate.save(platformModel,"yanyPlatform");

        return platformModelList;
    }


}
