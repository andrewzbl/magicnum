package cn.metathought.wxgzh.handler;

import cn.metathought.tool.util.Const;
import cn.metathought.wxgzh.common.builder.TextBuilder;
import cn.metathought.wxgzh.entity.member.WxMemberInfo;
import cn.metathought.wxgzh.service.remote.WxMemberInfoService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 公众号关注处理器
 *
 * @author zhoubinglong
 */
@Component
public class SubscribeHandler extends AbstractHandler {

    @Autowired
    private WxMemberInfoService wxMemberInfoService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService weixinService, WxSessionManager sessionManager)
        throws WxErrorException {
        logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        WxMpUser userWxInfo = weixinService.getUserService().userInfo(wxMessage.getFromUser(), null);

        if (userWxInfo != null) {
            logger.info("通过userInfo获取的微信用户信息：" + userWxInfo);
            WxMemberInfo wxMemberInfo = new WxMemberInfo();
            wxMemberInfo.setOpenId(userWxInfo.getOpenId());
            wxMemberInfo.setUnionId(userWxInfo.getUnionId());
            wxMemberInfo.setNickname(userWxInfo.getNickname());
            wxMemberInfo.setSex(userWxInfo.getSexDesc());
            wxMemberInfo.setHeadimgurl(userWxInfo.getHeadImgUrl());
            wxMemberInfo.setCountry(userWxInfo.getCountry());
            wxMemberInfo.setProvince(userWxInfo.getProvince());
            wxMemberInfo.setCity(userWxInfo.getCity());
            wxMemberInfo.setLanguage(userWxInfo.getLanguage());
            wxMemberInfo.setStatus(userWxInfo.getSubscribe() ? Const.YES : Const.NO);
            if (wxMemberInfoService.saveSubscribe(wxMemberInfo)) {
                logger.info("新增/更新用户信息成功 OPENID: " + wxMessage.getFromUser());
            }
        }

        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = handleSpecial(wxMessage);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build("感谢关注", wxMessage, weixinService);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
        throws Exception {
        //TODO
        return null;
    }

}
