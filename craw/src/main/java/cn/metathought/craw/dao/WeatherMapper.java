package cn.metathought.craw.dao;

import cn.metathought.craw.entity.WeatherWeather;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 天气接口
 *
 * @author zhoubinglong
 */
public interface WeatherMapper {
    /**
     * 根据站点id获取天气信息
     *
     * @param stationId
     * @return
     */
    @Select("select * from weather_weather where station_id = #{stationId}")
    WeatherWeather getWeather(Long stationId);

    @Insert("insert into weather_weather(station_id,hour,today,nextday,next2day,next3day,next4day,next5day,next6day,t24situation,other) values " +
            "(#{stationId},#{hour},#{today},#{nextday},#{next2day},#{next3day},#{next4day},#{next5day},#{next6day},#{t24situation},#{other})")
    boolean insert(WeatherWeather weather);

    @Update("update weather_weather" +
            "set station_id = #{stationId},hour = #{hour},today = #{today},nextday = #{nextday},next2day = #{next2day},next3day = #{next3day}," +
            "next4day = #{next4day},next5day = #{next5day},next6day = #{next6day},t24situation = #{t24situation},other = #{other}" +
            "where id = #{id}")
    boolean update(WeatherWeather weather);

    @Delete("delete from weather_weather")
    boolean delete();
}
