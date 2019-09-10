package cn.metathought.membercenter.controller;

import cn.metathought.membercenter.entity.WxMemberInfo;
import cn.metathought.membercenter.entity.WxMemberInfoExample;
import cn.metathought.membercenter.service.WxMemberInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoubinglong
 * @Description:微信用户服务外部接口
 * @date 2017/10/25
 */
@RestController
public class WxMemberInfoController {

    @Autowired
    private WxMemberInfoService wxMemberInfoService;

    /**
     * 微信用户关注公众号
     */
    @PostMapping("/member/wxMemberInfo")
    public boolean saveSubscribe(@RequestBody WxMemberInfo memberInfo) {
        return wxMemberInfoService.saveSubscribe(memberInfo);
    }

    /**
     * 微信用户取消关注公众号
     */
    @GetMapping("/member/wxMemberInfo/unsubscribe/{openId}")
    public boolean saveUnsubscribe(@PathVariable("openId") String openId) {
        return wxMemberInfoService.saveUnsubscribe(openId);
    }

    @GetMapping("/member/wxMemberInfo/{openId}")
    public WxMemberInfo selectWxMemberInfoByOpenId(@PathVariable("openId") String openId) {
        if (StringUtils.isBlank(openId)) {
            return null;
        }
        WxMemberInfoExample example = new WxMemberInfoExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        return wxMemberInfoService.selectOneByExample(example);
    }
}
