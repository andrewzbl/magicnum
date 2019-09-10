package cn.metathought.wxgzh.service;

import cn.metathought.tool.util.Const;
import cn.metathought.wxgzh.WxgzhApplicationTests;
import cn.metathought.wxgzh.entity.member.WxMemberInfo;
import cn.metathought.wxgzh.service.remote.WxMemberInfoService;
import java.util.Date;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WxMemberInfoServiceTest extends WxgzhApplicationTests {

    @Autowired
    private WxMemberInfoService wxMemberInfoService;

    @Test
    public void testWxMemberInfoRemote() {
        WxMemberInfo wxMemberInfo = WxMemberInfo.builder().openId("test-money").nickname("test-money").status(Const.YES).subscribeTime(new Date()).build();
        wxMemberInfoService.saveSubscribe(wxMemberInfo);
        wxMemberInfo = wxMemberInfoService.selectWxMemberInfoByOpenId(wxMemberInfo.getOpenId());
        logger.info("Find Coffee: {}", wxMemberInfo);
    }
}
