package cn.metathought.wxgzh.service.remote;

import cn.metathought.wxgzh.entity.WeatherStation;
import cn.metathought.wxgzh.entity.WeatherWeather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 爬虫服务feign接口
 *
 * @author zhoubinglong
 */
@FeignClient(name = "eureka-client-craw")
public interface CrawService {
    /**
     * 根据站点名称获取站点信息
     *
     * @param name
     * @return
     */
    @GetMapping("/craw/weatherStation/{name}")
    WeatherStation getStationByName(@PathVariable("name") String name);

    /**
     * 根据站点id获取天气信息
     *
     * @param stationId
     * @return
     */
    @GetMapping("/craw/weather/{stationId}")
    WeatherWeather getWeatherByStationId(@PathVariable("stationId") Long stationId);
}