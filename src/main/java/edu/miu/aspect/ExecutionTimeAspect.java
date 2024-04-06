package edu.miu.aspect;

import edu.miu.annotation.ExecutionTime;
import edu.miu.model.ActivityLog;
import edu.miu.repository.ActivityLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class ExecutionTimeAspect {
    private final ActivityLogRepository activityLogRepository;

    @Around("@annotation(executionTime)")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint, ExecutionTime executionTime) throws Throwable {
        String className = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String methodName = proceedingJoinPoint.getSignature().getName();

        long startTime = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        ActivityLog activityLog = ActivityLog.builder()
                        .date(LocalDateTime.now())
                        .operation(className + "." + methodName)
                        .duration(duration)
                        .build();

        activityLogRepository.save(activityLog);

        return proceed;
    }
}
