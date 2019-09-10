package cn.metathought.craw.entity;

import lombok.Data;

/**
 * @author zbl
 * @Description: 天气实体
 * @date 2017/10/21
 */
@Data
public class WeatherStation {
    private Long id;
    private String code;
    private String cityId;
    private String name;
    private String description;
}
