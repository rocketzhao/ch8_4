package com.wisely.ch8_4.dao.impl;

import com.wisely.ch8_4.config.ds.dynamic.TargetDataSource;
import com.wisely.ch8_4.dao.UserDao;
import com.wisely.ch8_4.domain.User;
import com.wisely.ch8_4.mapper.cluster.UserMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * userDaoImpl
 *
 * Created by jun.zhao on 2017/8/30.
 */
@Component("userDao")
public class UserDaoImpl implements UserDao{

    @Resource
    private UserMapper userMapper;

    @TargetDataSource("ds2DataSource")
    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
