package com.yany.model;

import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by yanyong on 2017/2/9.
 */
//@Document(collection = "yanyPlatorm")
public class PlatformModel implements Serializable {
//    @Id
    private String id;

    private String platName;

    private String platCode;

    private String platType;

    private String platPrefix;

    private String platVersion;

    private int delFlag;

    private long addTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getPlatCode() {
        return platCode;
    }

    public void setPlatCode(String platCode) {
        this.platCode = platCode;
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType;
    }

    public String getPlatPrefix() {
        return platPrefix;
    }

    public void setPlatPrefix(String platPrefix) {
        this.platPrefix = platPrefix;
    }

    public String getPlatVersion() {
        return platVersion;
    }

    public void setPlatVersion(String platVersion) {
        this.platVersion = platVersion;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "PlatformModel{" +
                "id='" + id + '\'' +
                ", platName='" + platName + '\'' +
                ", platCode='" + platCode + '\'' +
                ", platType='" + platType + '\'' +
                ", platPrefix='" + platPrefix + '\'' +
                ", platVersion='" + platVersion + '\'' +
                ", delFlag=" + delFlag +
                ", addTime=" + addTime +
                '}';
    }
}
