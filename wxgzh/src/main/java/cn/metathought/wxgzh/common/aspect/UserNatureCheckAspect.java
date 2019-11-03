package cn.metathought.wxgzh.common.aspect;

import cn.metathought.tool.exception.BussinessException;
import cn.metathought.wxgzh.common.RedisUtil;
import cn.metathought.wxgzh.common.Const;
import cn.metathought.wxgzh.common.annotation.UserNatureCheck;
import java.lang.reflect.Method;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class UserNatureCheckAspect {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisUtil redisUtil;

    @Before("execution( * cn.metathought.wxgzh..controller..*.*(..))")
    public void doBefore(JoinPoint joinPoint) {
        //从切点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //注解对象
        UserNatureCheck userNatureCheckAnnotation = method.getAnnotation(UserNatureCheck.class);
        if (userNatureCheckAnnotation != null) {
            String token = request.getHeader(Const.OAUTH_TOKEN);
            Map<Object, Object> redisMap = redisUtil.hmget(token);
            if (redisMap != null) {
                //TODO 权限验证
            } else {
                log.error("登录超时，请重新登录");
                throw new BussinessException("登录超时，请重新登录");
            }
        }
    }
}
