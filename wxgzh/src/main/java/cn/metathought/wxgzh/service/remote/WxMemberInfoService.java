package cn.metathought.wxgzh.service.remote;

import cn.metathought.wxgzh.entity.member.WxMemberInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 爬虫服务feign接口
 *
 * @author zhoubinglong
 */
@FeignClient(name = "eureka-client-membercenter")
public interface WxMemberInfoService {

    /**
     * 微信用户关注公众号
     *
     * @param memberInfo 用户信息
     * @return 关注公众号成功/失败
     */
    @PostMapping("/member/wxMemberInfo")
    boolean saveSubscribe(@RequestBody WxMemberInfo memberInfo);

    /**
     * 微信用户取消关注公众号
     *
     * @param openId openId
     * @return 取关成功/失败
     */
    @GetMapping("/member/wxMemberInfo/unsubscribe/{openId}")
    boolean saveUnsubscribe(@PathVariable("openId") String openId);

    /**
     * 根据openId获取公众号用户信息
     *
     * @param openId openId
     * @return 公众号用户信息
     */
    @GetMapping("/member/wxMemberInfo/{openId}")
    WxMemberInfo selectWxMemberInfoByOpenId(@PathVariable("openId") String openId);
}