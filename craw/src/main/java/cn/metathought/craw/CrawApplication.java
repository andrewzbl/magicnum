package cn.metathought.craw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhoubinglong
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@MapperScan("cn.metathought.craw.dao")
public class CrawApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrawApplication.class, args);
	}
}
