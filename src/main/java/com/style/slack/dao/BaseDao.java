package com.style.slack.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;

public class BaseDao extends SqlSessionDaoSupport {

    @Resource
     public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
                super.setSqlSessionFactory(sqlSessionFactory);
    }
}
