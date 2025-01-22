package dev.zbib.shared.config;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    @Before("execution(* dev.zbib..*(..))")
    public void logMethodExecution(JoinPoint joinPoint) {
        String layer = getLayer(joinPoint);
        String methodName = getMethodName(joinPoint);
        Object[] args = joinPoint.getArgs();
        log.info(
                "\u001B[33m⏳ [Layer: {}] - Executing method: {} with args: {} \u001B[0m",
                layer,
                methodName,
                formatArgs(args));
    }

    @AfterReturning(value = "execution(* dev.zbib..*(..))", returning = "result")
    public void logMethodReturn(
            JoinPoint joinPoint,
            Object result) {
        String layer = getLayer(joinPoint);
        String methodName = getMethodName(joinPoint);
        log.info(
                "\u001B[34m📦 [Layer: {}] - Method executed: {} returned: {} \u001B[0m",
                layer,
                methodName,
                formatResult(result));
    }

    @After("execution(* dev.zbib..*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String layer = getLayer(joinPoint);
        String methodName = getMethodName(joinPoint);
        log.info("\u001B[32m✅ [Layer: {}] - Method exit: {} \u001B[0m", layer, methodName);
    }

    @AfterThrowing(value = "execution(* dev.zbib..*(..))", throwing = "exception")
    public void logMethodException(
            JoinPoint joinPoint,
            Throwable exception) {
        String layer = getLayer(joinPoint);
        String methodName = getMethodName(joinPoint);
        log.error(
                "\u001B[31m❌ [Layer: {}] - Method execution failed: {} with exception: {} \u001B[0m",
                layer,
                methodName,
                exception.getMessage());
    }

    private String formatArgs(Object[] args) {
        if (args == null || args.length == 0) {
            return "No arguments";
        }

        StringBuilder formattedArgs = new StringBuilder();
        for (Object arg : args) {
            if (arg == null) {
                formattedArgs.append("null");
            } else if (arg instanceof String) {
                formattedArgs.append("\"")
                        .append(arg)
                        .append("\"");
            } else if (arg instanceof Collection<?> || arg instanceof Map) {
                formattedArgs.append(arg.getClass()
                                             .getSimpleName())
                        .append("[")
                        .append(((arg instanceof Collection) ? ((Collection<?>) arg).size() : ((Map<?, ?>) arg).size()))
                        .append(" elements]");
            } else if (arg.getClass()
                    .isArray()) {
                formattedArgs.append(arg.getClass()
                                             .getSimpleName())
                        .append("[length=")
                        .append(Array.getLength(arg))
                        .append("]");
            } else {
                formattedArgs.append(arg.getClass()
                                             .getName() + "@" + Integer.toHexString(System.identityHashCode(arg)));
            }
            formattedArgs.append(", ");
        }
        int length = formattedArgs.length();
        if (length > 0) {
            formattedArgs.delete(length - 2, length);
        }
        return formattedArgs.toString();
    }

    private String formatResult(Object result) {
        if (result == null) {
            return "null";
        } else if (result instanceof String) {
            return "\"" + result + "\"";
        } else if (result instanceof Collection || result instanceof Map) {
            assert result instanceof Collection<?>;
            return result.getClass()
                    .getSimpleName() + "[size=" + ((Collection<?>) result).size() + "]";
        }
        return result.getClass()
                .getName() + "@" + Integer.toHexString(System.identityHashCode(result));
    }

    private String getMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature()
                .getName();
    }

    private String getLayer(JoinPoint joinPoint) {
        String className = joinPoint.getTarget()
                .getClass()
                .getName();
        String packageName = className.substring(0, className.lastIndexOf("."));
        String layer = packageName.substring(packageName.lastIndexOf('.') + 1);
        return layer.substring(0, 1)
                .toUpperCase() + layer.substring(1);
    }
}
