package com.yany.dao.impl;

import com.yany.dao.ITestDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yanyong on 2017/7/9.
 */
@Repository("testDao")
public class TestDaoImpl implements ITestDao {

    @Resource(name = "adsJdbcTemplate")
    JdbcTemplate adsJdbcTemplate;


    @Resource(name = "rdsJdbcTemplate")
    JdbcTemplate rdsJdbcTemplate;


    @Override
    public String adsQueryUserId() {
        String sql = "select id from users where id = 1";
        List<String> list = adsJdbcTemplate.queryForList(sql, String.class);
        if (null == list || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public String rdsQueryUserId() {
        String sql = "select id from users where id = 1";
        List<String> list = rdsJdbcTemplate.queryForList(sql, String.class);
        if (null == list || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void adsInsertTest() {
        String sql = "INSERT into testads (id,name) values (3,'yany2') ";
        adsJdbcTemplate.execute(sql);
    }

    @Override
    public void rdsInsertTest() {
        String sql = "INSERT into testrds (id,name) values (3,'yany2') ";
        rdsJdbcTemplate.execute(sql);
    }


    /**
     * java.sql.SQLException: Can't call commit when autocommit=true
     */
    @Override
    public void multiInsertTest() {
        try {
            String adssql = "INSERT into testads (id,name) values (3,'yany2') ";
            adsJdbcTemplate.execute(adssql);

            String rdssql = "INSERT into testrds (id,name) values (3,'yany2') ";
            rdsJdbcTemplate.execute(rdssql);
        } catch (Exception e) {
            try {
                adsJdbcTemplate.getDataSource().getConnection().rollback();
                rdsJdbcTemplate.getDataSource().getConnection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                adsJdbcTemplate.getDataSource().getConnection().commit();
                rdsJdbcTemplate.getDataSource().getConnection().commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




}
