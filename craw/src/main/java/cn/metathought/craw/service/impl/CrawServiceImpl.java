package cn.metathought.craw.service.impl;

import cn.metathought.craw.dao.WeatherMapper;
import cn.metathought.craw.dao.WeatherStationMapper;
import cn.metathought.craw.entity.WeatherStation;
import cn.metathought.craw.entity.WeatherWeather;
import cn.metathought.craw.service.CrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 爬虫对外接口
 *
 * @author zhoubinglong
 */
@Service
public class CrawServiceImpl implements CrawService {
    @Autowired
    private WeatherMapper weatherMapper;
    @Autowired
    private WeatherStationMapper weatherStationMapper;

    @Override
    public WeatherStation getStation(String name) {
        return weatherStationMapper.getStation(name);
    }

    @Override
    public WeatherWeather getWeather(Long stationId) {
        return weatherMapper.getWeather(stationId);
    }
}