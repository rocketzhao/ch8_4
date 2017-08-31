package com.wisely.ch8_4.controller.city;

import com.wisely.ch8_4.domain.City;
import com.wisely.ch8_4.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by jun.zhao on 2017/8/28.
 *
 */
@Controller
public class CityController {

    private static Logger logger = LoggerFactory.getLogger(CityController.class);

    private String message = "Hello World";

    @Resource
    private CityService cityService;

    @ResponseBody
    @RequestMapping(value = "/findByName")
    public City findByName(String cityName) throws Exception {
        City city = cityService.findByName(cityName);
        if(city == null){
            throw new Exception(cityName);
        }
        logger.info("city : {}", city);
        return city;
    }

}
