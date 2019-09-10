package cn.metathought.craw.service;

/**
 * @author zhoubinglong
 */
public interface CrawManager {
    /**
     * 天气爬虫
     *
     * @param stationCode 县城（区）的CODE
     */
    void weatherCrawl(String stationCode);
}