package cn.metathought.wxgzh.handler;

import cn.metathought.wxgzh.service.remote.WxMemberInfoService;
import java.util.Map;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 公众号取消关注处理器
 *
 * @author zhoubinglong
 */
@Component
public class UnsubscribeHandler extends AbstractHandler {

    @Autowired
    private WxMemberInfoService wxMemberInfoService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) {
        String openId = wxMessage.getFromUser();
        logger.info("取消关注用户 OPENID: " + openId);

        if (wxMemberInfoService.saveUnsubscribe(openId)) {
            logger.info("用户取消关注成功，OPENID: " + openId);
        }

        return null;
    }

}
