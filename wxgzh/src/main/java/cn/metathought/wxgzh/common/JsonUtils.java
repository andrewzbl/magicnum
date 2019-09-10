package cn.metathought.wxgzh.common;

import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

/**
 * @author zhoubinglong
 */
public class JsonUtils {
    public static String toJson(Object obj) {
        return WxMpGsonBuilder.create().toJson(obj);
    }
}