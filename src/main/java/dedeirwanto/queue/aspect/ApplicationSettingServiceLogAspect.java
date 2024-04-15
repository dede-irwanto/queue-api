package dedeirwanto.queue.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ApplicationSettingServiceLogAspect {

    @Pointcut("target(dedeirwanto.queue.service.ApplicationSettingService)")
    public void applicationSettingServiceMethod() {
    }

    @Around("applicationSettingServiceMethod()")
    public Object aroundApplicationSettingServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        try {
            log.info("Start " + className + "." + methodName + "()");
            return joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            log.info("Error " + className + "." + methodName + "()");
            throw throwable;
        } finally {
            log.info("Finish " + className + "." + methodName + "()");
        }
    }

    @Pointcut("execution(* dedeirwanto.queue.service.ApplicationSettingService.*(java.lang.Object))")
    public void applicationSettingServiceMethodParam() {
    }

    @Before("applicationSettingServiceMethod() && args(name)")
    public void beforeApplicationSettingServiceParam(JoinPoint joinPoint, String name) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Execute " + className + "." + methodName + "() with parameter : " + name.substring(0, 2) + "***")
        ;
    }
}
