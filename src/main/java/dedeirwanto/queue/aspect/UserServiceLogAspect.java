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
public class UserServiceLogAspect {

    @Pointcut("target(dedeirwanto.queue.service.UserService)")
    public void userServiceMethod() {
    }

    @Around("userServiceMethod()")
    public Object aroundUserServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
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

    @Pointcut("execution(* dedeirwanto.queue.service.UserService.*(java.lang.Object))")
    public void userServiceMethodParam() {
    }

    @Before("userServiceMethod() && args(name)")
    public void beforeUserServiceParam(JoinPoint joinPoint, String name) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Execute " + className + "." + methodName + "() with parameter : " + name.substring(0, 2) + "***")
        ;
    }
}
