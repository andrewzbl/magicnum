package cn.metathought.membercenter.entity;

import cn.metathought.tool.util.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 微信用户信息
 *
 * @author zhoubinglong
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WxMemberInfo extends BaseEntity implements Serializable {
    private String openId;
    private String unionId;
    private String nickname;
    private String sex;
    private String country;
    private String province;
    private String city;
    private String language;
    private String headimgurl;
    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date subscribeTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date unsubscribeTime;
}