package cn.metathought.craw.controller;

import cn.metathought.craw.entity.WeatherStation;
import cn.metathought.craw.entity.WeatherWeather;
import cn.metathought.craw.service.CrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author zhoubinglong
 * @Description:爬虫外部接口
 * @date 2017/10/25
 */
@Controller
public class CrawController {
    @Autowired
    private CrawService crawService;

    /**
     * 根据站点名称获取站点信息
     *
     * @param name
     * @return
     */
    @GetMapping("/craw/weatherStation/{name}")
    public WeatherStation getStationByName(@PathVariable("name") String name) {
        return crawService.getStation(name);
    }

    /**
     * 根据站点id获取天气信息
     *
     * @param stationId
     * @return
     */
    @GetMapping("/craw/weather/{stationId}")
    public WeatherWeather getWeatherByStationId(@PathVariable("stationId") Long stationId) {
        return crawService.getWeather(stationId);
    }
}
