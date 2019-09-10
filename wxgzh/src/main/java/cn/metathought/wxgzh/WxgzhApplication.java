package cn.metathought.wxgzh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhoubinglong
 */
@SpringBootApplication
@MapperScan("cn.metathought.wxgzh.dao")
@EnableDiscoveryClient
@EnableFeignClients
public class WxgzhApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxgzhApplication.class, args);
    }
}
