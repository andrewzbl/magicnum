package cn.metathought.craw.dao;

import cn.metathought.craw.entity.WeatherStation;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 站点接口
 *
 * @author zhoubinglong
 */
public interface WeatherStationMapper {
    /**
     * 根据站点编码获取站点信息
     *
     * @param code
     * @return
     */
    @Select("select * from weather_station where code = #{code}")
    @Results({
            @Result(property = "cityId", column = "city_id")
    })
    WeatherStation getWeatherStation(String code);

    /**
     * 获取所有站点信息
     *
     * @return
     */
    @Select("select * from weather_station")
    @Results({
            @Result(property = "cityId", column = "city_id")
    })
    List<WeatherStation> listStations();

    /**
     * 根据站点名称查询站点
     *
     * @param name
     * @return
     */
    @Select("select * from weather_station where name = #{name}")
    @Results({
            @Result(property = "cityId", column = "city_id")
    })
    WeatherStation getStation(String name);
}
