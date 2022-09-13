package com.group.five.exceptionsResolvers;

import com.alibaba.fastjson.JSON;
import com.group.five.base.ResultInfo;
import com.group.five.exceptions.AuthException;
import com.group.five.exceptions.NoLoginException;
import com.group.five.exceptions.ParamsException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        e.printStackTrace();

        ModelAndView mv = new ModelAndView();

        if(e instanceof NoLoginException){
            mv.setViewName("redirect:index");
            return mv;
        }

        mv.setViewName("error");
        mv.addObject("code",400);
        mv.addObject("msg","异常请重试");

        if(o instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) o;
            ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
            if(null == responseBody){
                if(e instanceof ParamsException){
                    ParamsException paramsException = (ParamsException) e;
                    mv.addObject("code",paramsException.getCode());
                    mv.addObject("msg",paramsException.getMsg());
                }
                return mv;
            }else{
                ResultInfo resultInfo = new ResultInfo();
                resultInfo.setCode(300);
                resultInfo.setResult("异常请重试");
                if(e instanceof ParamsException) {
                    ParamsException paramsException = (ParamsException) e;
                    resultInfo.setCode(paramsException.getCode());
                    resultInfo.setMsg(paramsException.getMsg());
                }else if (e instanceof AuthException) {
                    AuthException pe = (AuthException) e;
                    resultInfo.setCode(pe.getCode());
                    resultInfo.setMsg(pe.getMsg());
                }
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter printWriter = null;
                try{
                    printWriter = httpServletResponse.getWriter();
                    printWriter.write(JSON.toJSONString(resultInfo));
                    printWriter.flush();
                }catch(Exception ex){
                   ex.printStackTrace();
                }finally {
                    if(printWriter!=null){
                        printWriter.close();
                    }
                }
                return null;
            }
        }
        return mv;
    }
}
