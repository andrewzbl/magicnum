package cn.metathought.membercenter.service;

import cn.metathought.membercenter.MemberCenterApplicationTests;
import cn.metathought.membercenter.entity.WxMemberInfo;
import cn.metathought.membercenter.entity.WxMemberInfoExample;
import cn.metathought.tool.util.Const;
import java.util.Date;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class WxMemberInfoServiceTest extends MemberCenterApplicationTests {

    @Autowired
    private WxMemberInfoService wxMemberInfoService;

    @Test
    public void testSaveSubscribe() {
        WxMemberInfo wxMemberInfo = WxMemberInfo.builder().openId("test-money").nickname("test-money").status(Const.YES).subscribeTime(new Date()).build();
        wxMemberInfoService.saveSubscribe(wxMemberInfo);
        WxMemberInfoExample example = new WxMemberInfoExample();
        example.createCriteria().andOpenIdEqualTo(wxMemberInfo.getOpenId());
        wxMemberInfo = wxMemberInfoService.selectOneByExample(example);
        logger.info("Find Coffee: {}", wxMemberInfo);
    }
}
