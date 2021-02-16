package net.temirov.aspectj;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;
import org.aspectj.lang.Aspects;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.lang.reflect.SourceLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MethodLoggingAspectTest {
    private MethodLoggingAspect methodLoggingAspect;
    private ProceedingJoinPoint proceedingJoinPoint;

    @BeforeEach
    void setUp() {
        methodLoggingAspect = new MethodLoggingAspect();
        proceedingJoinPoint = mock(ProceedingJoinPoint.class);
    }

    @Test
    void methodLog() throws Throwable {
        SourceLocation sourceLocation = mock(SourceLocation.class);
        when(sourceLocation.getWithinType()).thenReturn(this.getClass());
        when(proceedingJoinPoint.getSourceLocation()).thenReturn(sourceLocation);
        when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{});
        when(proceedingJoinPoint.proceed()).thenReturn(null);

        MethodSignature methodSignature = mock(MethodSignature.class);
        when(methodSignature.toShortString()).thenReturn("CalculationService.calculate(..)");
        when(proceedingJoinPoint.getSignature()).thenReturn(methodSignature);

        final TestAppender appender = new TestAppender();
        final org.apache.log4j.Logger logger =
                Logger.getLogger(proceedingJoinPoint.getSourceLocation().getWithinType());
        logger.addAppender(appender);
        methodLoggingAspect.methodLog(proceedingJoinPoint);
        final List<LoggingEvent> log = appender.getLog();
        final LoggingEvent firstLogEntry = log.get(0);
        assertTrue(firstLogEntry.getMessage().toString().contains("method=CalculationService.calculate, millis"));
    }

    @Test
    void hasAspect() {
        assertTrue(Aspects.hasAspect(MethodLoggingAspect.class));
    }

    @Test
    void methodLogException() throws Throwable {
        SourceLocation sourceLocation = mock(SourceLocation.class);
        when(sourceLocation.getWithinType()).thenReturn(this.getClass());
        when(proceedingJoinPoint.getSourceLocation()).thenReturn(sourceLocation);
        when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{});
        when(proceedingJoinPoint.proceed()).thenThrow(RuntimeException.class);

        MethodSignature methodSignature = mock(MethodSignature.class);
        when(methodSignature.toShortString()).thenReturn("CalculationService.calculate(..)");
        when(proceedingJoinPoint.getSignature()).thenReturn(methodSignature);

        final TestAppender appender = new TestAppender();
        final org.apache.log4j.Logger logger =
                Logger.getLogger(proceedingJoinPoint.getSourceLocation().getWithinType());
        logger.addAppender(appender);
        assertThrows(RuntimeException.class, () -> methodLoggingAspect.methodLog(proceedingJoinPoint));
        final List<LoggingEvent> log = appender.getLog();
        final LoggingEvent firstLogEntry = log.get(0);
        assertFalse(firstLogEntry.getMessage().toString().contains("method=CalculationService.calculate, millis"));
    }

    static class TestAppender extends AppenderSkeleton {
        private final List<LoggingEvent> log = new ArrayList<>();

        @Override
        public boolean requiresLayout() {
            return false;
        }

        @Override
        protected void append(final LoggingEvent loggingEvent) {
            log.add(loggingEvent);
        }

        @Override
        public void close() {
        }

        public List<LoggingEvent> getLog() {
            return new ArrayList<>(log);
        }
    }
}
