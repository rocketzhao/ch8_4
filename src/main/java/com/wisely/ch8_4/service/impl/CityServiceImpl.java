package com.wisely.ch8_4.service.impl;

import com.wisely.ch8_4.dao.CityDao;
import com.wisely.ch8_4.dao.UserDao;
import com.wisely.ch8_4.domain.City;
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
    private UserDao userDao;

    @Resource
    private CityDao cityDao;

    @Override
    public City findByName(String cityName) {
//        User user = userDao.selectByPrimaryKey(1L);
//        System.out.println(user == null ? null : user.getUserName());
        return cityDao.findByName(cityName);
    }
}
