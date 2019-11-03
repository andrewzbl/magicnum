package cn.metathought.wxgzh.common.aspect;

import cn.metathought.tool.exception.BussinessException;
import cn.metathought.wxgzh.common.RedisUtil;
import cn.metathought.wxgzh.common.Const;
import cn.metathought.wxgzh.common.annotation.JurisdictionCheck;
import java.lang.reflect.Method;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class JurisdictionCheckAspect {

    @Autowired
    private RedisUtil redisUtil;

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {
        //从切点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        //若目标方法配置了权限检查
        if (method.isAnnotationPresent(JurisdictionCheck.class)) {
            //获取注解对象
            JurisdictionCheck jurisdictionCheck = method.getAnnotation(JurisdictionCheck.class);
            //获取注解参数值
            String value = jurisdictionCheck.value();
            //如果注解参数为空或者null则默认为不进行权限验证。
            if ("".equals(value) || value == null) {
                return pjp.proceed();
            }
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader(Const.OAUTH_TOKEN);
            Boolean haveAccess = false;
            Map<Object, Object> redisMap = redisUtil.hmget(token);
            if (redisMap == null) {
                throw new BussinessException("无操作权限，请联系管理员");
            }
            Map<String, Object> userFuncMap = (Map<String, Object>) redisMap.get("redisUserAccessMap");
            if (userFuncMap == null) {
                throw new BussinessException("无操作权限，请联系管理员");
            }
            String[] valueList = value.split(",");
            for (int i = 0; i < valueList.length; i++) {
                String item = valueList[i];
                String funcId = String.valueOf(userFuncMap.get(item));
                if (StringUtils.isNotBlank(funcId)) {
                    haveAccess = true;
                    break;
                } else {
                    continue;
                }
            }
            if (haveAccess) {
                return pjp.proceed();
            } else {
                throw new BussinessException("无操作权限，请联系管理员");
            }
        } else {
            return pjp.proceed();
        }
    }
}
