package com.wisely.ch8_4.service.impl;

import com.wisely.ch8_4.dao.CityDao;
import com.wisely.ch8_4.dao.UserDao;
import com.wisely.ch8_4.domain.City;
import com.wisely.ch8_4.domain.User;
import com.wisely.ch8_4.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * Created by jun.zhao on 2017/8/28.
 */
@Service("cityService")
public class CityServiceImpl implements CityService {

    private static Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    @Resource
    private UserDao userDao;

    @Resource
    private CityDao cityDao;

    @Override
    public City findByName(String cityName) {
        User user = userDao.selectByPrimaryKey(1L);
        logger.info("user: {}", user == null ? null : user.getUserName());
        return cityDao.findByName(cityName);
    }
}
