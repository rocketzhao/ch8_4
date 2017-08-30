package com.wisely.ch8_4.dao;

import com.wisely.ch8_4.domain.City;

/**
 * cityDao
 *
 * Created by jun.zhao on 2017/8/30.
 */
public interface CityDao {

    City findByName(String cityName);

}
