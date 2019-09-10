package cn.metathought.craw.service.impl;

import cn.metathought.craw.dao.WeatherMapper;
import cn.metathought.craw.dao.WeatherStationMapper;
import cn.metathought.craw.entity.WeatherStation;
import cn.metathought.craw.entity.WeatherWeather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 自定义Pipeline处理抓取的数据
 *
 * @author zhoubinglong
 */
@Service
@Slf4j
public class CustomPipeline implements Pipeline {
    @Autowired
    private WeatherStationMapper weatherStationMapper;
    @Autowired
    private WeatherMapper weatherMapper;

    /**
     * 保存抓取的天气数据
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
        WeatherWeather weather = resultItems.get("weather");
        WeatherStation weatherStation = weatherStationMapper.getWeatherStation(resultItems.get("stationCode").toString());
        if (weatherStation != null) {
            weather.setStationId(weatherStation.getId());
            WeatherWeather oldWeather = weatherMapper.getWeather(weatherStation.getId());
            if (oldWeather == null) {
                weatherMapper.insert(weather);
            } else {
                weather.setId(oldWeather.getId());
                weatherMapper.update(weather);
            }

        }

    }
}