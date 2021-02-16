package net.temirov.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@Aspect
public class MethodLoggingAspect {

    /**
     * Logging method for the aspect, execute when {@link LogExecutionTime}
     * annotation used and method executed. requestId will be logged if exists
     *
     * @param joinPoint A proceeding joinpoint, supplied by the annotation framework
     * @return Object
     * @throws Throwable rethrows exception adding the information about the method
     */
    @Around("@annotation(net.temirov.aspectj.LogExecutionTime)")
    public Object methodLog(ProceedingJoinPoint joinPoint) throws Throwable {
        final Logger logger = LoggerFactory.getLogger(joinPoint.getSourceLocation().getWithinType());
        final String method = joinPoint.getSignature().toShortString().replace("(..)", "");

        Object proceed;
        final long start = System.nanoTime();
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error("method=" + method, throwable);
            throw throwable;
        }
        final long end = System.nanoTime();

        logger.info("method={}, millis={}",
                method,
                TimeUnit.NANOSECONDS.toMillis(end - start));
        return proceed;
    }
}
