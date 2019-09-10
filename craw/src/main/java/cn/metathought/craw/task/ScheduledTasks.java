package cn.metathought.craw.task;

import cn.metathought.craw.dao.WeatherMapper;
import cn.metathought.craw.dao.WeatherStationMapper;
import cn.metathought.craw.entity.WeatherStation;
import cn.metathought.craw.service.CrawManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务
 *
 * @author zhoubinglong
 */
@Component
@Configurable
@Slf4j
public class ScheduledTasks {

    @Autowired
    private WeatherStationMapper weatherStationMapper;
    @Autowired
    private WeatherMapper weatherMapper;
    @Autowired
    private CrawManager crawManager;

    /**
     * 每天8、11、20点5分更新数据
     */
    @Scheduled(cron = "0 5 8,11,20 * * * ")
    public void scheduleSpider() {
        log.info("每天8、11、20点5分爬取网页数据。。。");
        //首先清空数据
        weatherMapper.delete();
        //更新最新数据
        List<WeatherStation> weatherStations = weatherStationMapper.listStations();
        if (weatherStations != null) {
            weatherStations.forEach(w -> crawManager.weatherCrawl(w.getCode()));
        }
    }
}