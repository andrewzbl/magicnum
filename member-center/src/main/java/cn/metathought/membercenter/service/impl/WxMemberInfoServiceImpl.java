package cn.metathought.membercenter.service.impl;

import cn.metathought.membercenter.dao.WxMemberInfoMapper;
import cn.metathought.membercenter.entity.WxMemberInfo;
import cn.metathought.membercenter.entity.WxMemberInfoExample;
import cn.metathought.membercenter.service.WxMemberInfoService;
import cn.metathought.tool.util.Const;
import cn.metathought.tool.util.base.BaseDao;
import cn.metathought.tool.util.base.BaseServiceImpl;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhoubinglong
 */
@Service
public class WxMemberInfoServiceImpl extends BaseServiceImpl<WxMemberInfo, WxMemberInfoExample> implements WxMemberInfoService {

    @Autowired
    private WxMemberInfoMapper wxMemberInfoMapper;

    @Override
    protected BaseDao<WxMemberInfo, WxMemberInfoExample> getDao() {
        return wxMemberInfoMapper;
    }

    @Override
    public boolean saveSubscribe(WxMemberInfo memberInfo) {
        WxMemberInfoExample example = new WxMemberInfoExample();
        example.createCriteria().andOpenIdEqualTo(memberInfo.getOpenId());
        WxMemberInfo wxMemberInfo = selectOneByExample(example);
        //以前关注过，重新关注
        if (wxMemberInfo != null) {
            memberInfo.setId(wxMemberInfo.getId());
            memberInfo.setStatus(Const.YES);
            memberInfo.setSubscribeTime(new Date());
            int i = updateByPrimaryKeySelective(memberInfo);
            if (i > 0) {
                return true;
            }
        } else {//新关注用户
            int i = insert(memberInfo);
            if (i > 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean saveUnsubscribe(String openId) {
        WxMemberInfoExample example = new WxMemberInfoExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        WxMemberInfo wxMemberInfo = selectOneByExample(example);
        if (wxMemberInfo != null) {
            wxMemberInfo.setStatus(Const.NO);
            wxMemberInfo.setUnsubscribeTime(new Date());
            int i = updateByPrimaryKeySelective(wxMemberInfo);
            if (i > 0) {
                return true;
            }
        }
        return false;
    }
}