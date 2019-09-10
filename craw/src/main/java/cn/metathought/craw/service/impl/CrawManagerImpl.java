package cn.metathought.craw.service.impl;

import cn.metathought.craw.common.WeatherSpider;
import cn.metathought.craw.service.CrawManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.model.OOSpider;

/**
 * 爬虫
 *
 * @author zhoubinglong
 */
@Service
public class CrawManagerImpl implements CrawManager {

    @Autowired
    private CustomPipeline customPipeline;

    @Override
    public void weatherCrawl(String stationCode) {
        OOSpider.create(new WeatherSpider()).addPipeline(customPipeline)
                .addUrl("http://www.weather.com.cn/weather/" + stationCode + ".shtml")
                .thread(1)
                .run();
    }
}