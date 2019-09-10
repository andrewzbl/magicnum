package cn.metathought.wxgzh.entity;

import lombok.Data;

/**
 * @author zbl
 * @Description: 天气实体
 * @date 2017/10/21
 */
@Data
public class WeatherWeather {
    private String id;
    private Long stationId;
    private String hour;
    private String today;
    private String nextday;
    private String next2day;
    private String next3day;
    private String next4day;
    private String next5day;
    private String next6day;
    private String t24situation;
    private String other;
}
