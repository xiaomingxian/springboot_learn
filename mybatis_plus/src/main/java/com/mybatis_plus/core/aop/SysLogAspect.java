package com.mybatis_plus.core.aop;

import com.mybatis_plus.controller.ProcessController;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 系统日志切面
 *
 * @author xxm
 */
@Aspect  // 使用@Aspect注解声明一个切面
@Component
public class SysLogAspect {
    private static final Logger log = LoggerFactory.getLogger(SysLogAspect.class);

    /**
     * 这里我们使用注解的形式
     * 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     * 切点表达式:   execution(...)
     */
    @Pointcut("@annotation(com.mybatis_plus.core.aop.Log)")
    public void logPointCut() {
    }

    /**
     * 环绕通知 @Around  ， 当然也可以使用 @Before (前置通知)  @After (后置通知)
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        try {
            saveLog(point, time);
        } catch (Exception e) {
            System.out.println("--------->捕获到异常");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 保存日志
     *
     * @param joinPoint
     * @param time
     */
    private void saveLog(ProceedingJoinPoint joinPoint, long time) {


        //用户信息
        Object principal = SecurityUtils.getSubject().getPrincipal();
        log.info("~~~~~" + principal);

        ////获取被增强的方法相关信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        log.info("~~~~~" + signature);
        Method method = signature.getMethod();
        log.info("~~~~~" + method);
        //请求的 类名、方法名
        String className = joinPoint.getTarget().getClass().getName();
        log.info("~~~~~" + className);
        String methodName = signature.getName();
        log.info("~~~~~" + methodName);
        //请求的参数
        Object[] args = joinPoint.getArgs();
        log.info("~~~~~" + args);

        //ip
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = request.getHeader("x-forwarded-for");
        log.info("~~~~~" + ip);

        Log sysLog = method.getAnnotation(Log.class);
        log.info("~~~~~" + sysLog.function_id());
        log.info("~~~~~" + sysLog.operate_type());
        log.info("~~~~~" + sysLog.operate_content());
        log.info("~~~~~执行时长：" + time);


        //调用服务存储 相关信息
    }
}