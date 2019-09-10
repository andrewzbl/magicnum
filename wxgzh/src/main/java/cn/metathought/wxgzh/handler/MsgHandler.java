package cn.metathought.wxgzh.handler;

import cn.metathought.wxgzh.common.Const;
import cn.metathought.wxgzh.common.builder.TextBuilder;
import java.util.Map;
import me.chanjar.weixin.common.api.WxConsts.XmlMsgType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 消息事件处理器
 *
 * @author zhoubinglong
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService weixinService, WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        try {
            if (StringUtils.startsWithAny(wxMessage.getContent(), "你好", "客服")
                && weixinService.getKefuService().kfOnlineList().getKfOnlineList().size() > 0) {
                return WxMpXmlOutMessage.TRANSFER_CUSTOMER_SERVICE()
                    .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser()).build();
            }
        } catch (WxErrorException e) {
            logger.error("客服异常", e);
        }

        String input = wxMessage.getContent();
        String content = Const.DEFAULT_REPLAY;
        if (StringUtils.isNotBlank(input)) {
        }

        return new TextBuilder().build(content, wxMessage, weixinService);

    }

}
