package cn.metathought.craw.service;

import cn.metathought.craw.entity.WeatherStation;
import cn.metathought.craw.entity.WeatherWeather;

/**
 * 对外feign接口
 *
 * @author zhoubinglong
 */
public interface CrawService {
    /**
     * 根据站点名称获取站点信息
     *
     * @param name
     * @return
     */
    WeatherStation getStation(String name);

    /**
     * 根据站点id获取天气信息
     *
     * @param stationId
     * @return
     */
    WeatherWeather getWeather(Long stationId);
}