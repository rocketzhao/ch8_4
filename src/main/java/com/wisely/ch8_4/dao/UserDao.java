package com.wisely.ch8_4.dao;

import com.wisely.ch8_4.domain.User;

/**
 * userDao
 *
 * Created by jun.zhao on 2017/8/30.
 */
public interface UserDao {

    User selectByPrimaryKey(Long id);

}
