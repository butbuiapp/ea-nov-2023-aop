package edu.miu.aspect;

import edu.miu.exception.AopIsAwesomeHeaderException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AopIsAwesomeAspect {
    @Before("within(edu.miu.service.*)")
    public void checkAopIsAwesomeHeader(JoinPoint joinPoint) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String httpMethod = request.getMethod();
        if (httpMethod.equals("POST")) {
            String headerValue = request.getHeader("AOP-IS-AWESOME");
            if (headerValue == null) {
                throw new AopIsAwesomeHeaderException("AOP-IS-AWESOME header is missing");
            }
        }
    }
}
