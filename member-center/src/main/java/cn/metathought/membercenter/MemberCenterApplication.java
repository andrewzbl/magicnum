package cn.metathought.membercenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhoubinglong
 */
@SpringBootApplication
@MapperScan("cn.metathought.membercenter.dao")
@EnableDiscoveryClient
public class MemberCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberCenterApplication.class, args);
    }

}
