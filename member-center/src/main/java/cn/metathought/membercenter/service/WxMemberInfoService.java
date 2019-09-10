package cn.metathought.membercenter.service;

import cn.metathought.membercenter.entity.WxMemberInfo;
import cn.metathought.membercenter.entity.WxMemberInfoExample;
import cn.metathought.tool.util.base.BaseService;

/**
 * 微信用户信息
 *
 * @author zhoubinglong
 */
public interface WxMemberInfoService extends BaseService<WxMemberInfo, WxMemberInfoExample> {

    /**
     * 微信用户关注公众号
     */
    boolean saveSubscribe(WxMemberInfo memberInfo);

    /**
     * 微信用户取消关注公众号
     */
    boolean saveUnsubscribe(String openId);

}