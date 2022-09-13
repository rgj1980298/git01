package com.group.five.aspect;

import com.group.five.annotations.RequirePermission;
import com.group.five.dao.LoggingMapper;
import com.group.five.dao.ModuleMapper;
import com.group.five.exceptions.AuthException;
import com.group.five.exceptions.NoLoginException;
import com.group.five.pojo.Logging;
import com.group.five.pojo.Module;
import com.group.five.utils.CookieUtil;
import com.group.five.utils.LoginUserUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Component
@Aspect
public class PermissionProxy {
    @Autowired
    public HttpSession session;

    @Autowired
    public HttpServletRequest request;

    @Autowired(required = false)
    public ModuleMapper moduleMapper;

    @Autowired(required = false)
    public LoggingMapper loggingMapper;

    @Around(value = "@annotation(com.group.five.annotations.RequirePermission)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        List<String> permissions = (List<String>) session.getAttribute("permissions");
        if(null == permissions){
            throw new NoLoginException();
        }
        Object result =null;
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        RequirePermission requirePermission =
                methodSignature.getMethod().getDeclaredAnnotation(RequirePermission.class);

        String code = requirePermission.code();

        if(!(permissions.contains(code))){
            throw new AuthException();
        }
        result= pjp.proceed();

        //记录日志
        Logging logging = new Logging();
        logging.setUserId(LoginUserUtil.releaseUserIdFromCookie(request));
        logging.setUserName(CookieUtil.getCookieValue(request,"userName"));
        logging.setUpdateDate(new Date());
        logging.setIsValid(1);

        Module module = moduleMapper.selectByOptValue(code);
        logging.setModuleId(module.getId());
        logging.setModuleName(module.getModuleName());

        loggingMapper.insertSelective(logging);
        return result;
    }

}