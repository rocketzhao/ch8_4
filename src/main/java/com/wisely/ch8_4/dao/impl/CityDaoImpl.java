package com.wisely.ch8_4.dao.impl;

import com.wisely.ch8_4.dao.CityDao;
import com.wisely.ch8_4.domain.City;
import com.wisely.ch8_4.mapper.master.CityMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * cityDao
 *
 * Created by jun.zhao on 2017/8/30.
 */
@Component("cityDao")
public class CityDaoImpl implements CityDao {

    @Resource
    private CityMapper cityMapper;

    @Override
    public City findByName(String cityName) {
        return cityMapper.findByName(cityName);
    }
}
