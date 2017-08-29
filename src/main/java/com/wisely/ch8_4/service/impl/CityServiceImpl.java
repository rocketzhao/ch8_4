package com.wisely.ch8_4.service.impl;

import com.wisely.ch8_4.dao.cluster.UserMapper;
import com.wisely.ch8_4.dao.master.CityMapper;
import com.wisely.ch8_4.domain.City;
import com.wisely.ch8_4.domain.User;
import com.wisely.ch8_4.service.CityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * Created by jun.zhao on 2017/8/28.
 */
@Service("cityService")
public class CityServiceImpl implements CityService {

    @Resource
    private CityMapper cityMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public City findByName(String cityName) {
        User user = userMapper.selectByPrimaryKey(1L);
        System.out.println(user == null ? user : user.getUserName());
        return cityMapper.findByName(cityName);
    }
}
