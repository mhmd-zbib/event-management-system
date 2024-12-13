package dev.zbib.providerservice.config;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class LoggingAspect {

    // Log method execution before the actual method is called (for all layers)
    @Before("execution(* dev.zbib.providerservice..*(..))")
    public void logMethodExecution(JoinPoint joinPoint) {
        String layer = getLayer(joinPoint);
        String methodName = getMethodName(joinPoint);
        Object[] args = joinPoint.getArgs();
        log.info("[{}] Executing method: {}({})", layer, methodName, formatArguments(args));
    }

    // Log method return value after the method is executed (for all layers)
    @AfterReturning(value = "execution(* dev.zbib.providerservice..*(..))", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        String layer = getLayer(joinPoint);
        String methodName = getMethodName(joinPoint);
        log.info("[{}] Method executed: {} returned: {}", layer, methodName, result);
    }

    // Log method execution time and status after method execution (for all layers)
    @After("execution(* dev.zbib.providerservice..*(..))")
    public void logMethodExit(JoinPoint joinPoint) {
        String layer = getLayer(joinPoint);
        String methodName = getMethodName(joinPoint);
        log.info("[{}] Method executed: {} completed", layer, methodName);
    }

    // Log exceptions thrown by any method (for all layers)
    @AfterThrowing(value = "execution(* dev.zbib.providerservice..*(..))", throwing = "exception")
    public void logMethodException(JoinPoint joinPoint, Throwable exception) {
        String layer = getLayer(joinPoint);
        String methodName = getMethodName(joinPoint);
        log.error("[{}] Method execution failed: {} with exception: {}", layer, methodName, exception.getMessage());
    }

    // Helper method to determine the layer private String getLayer(JoinPoint joinPoint) {
    String className = joinPoint.getSignature().getDeclaringTypeName();

     extract the package components
    String[] packages = className.split("\\.");

   to extract the desired part
    if (packages.length > 3) {
        String layer = packages[3]; 
        return capitalize(layer);  
    }
    
    return "Unknown Layer"; 
}

// Helper method to capitalize the first letter of the string
private String capitalize(String str) {
    if (str == null || str.isEmpty()) return str;
    return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
}


    // Helper method to remove the package prefix from the class name
    private String getClassName(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        // Remove the package prefix `dev.zbib.providerservice`
        return className.replace("dev.zbib.providerservice", "");
    }

    // Helper method to get the method name without package prefix
    private String getMethodName(JoinPoint joinPoint) {
        return joinPoint.getSignature().getName();
    }

    // Helper method to format arguments for logging
    private String formatArguments(Object[] args) {
        if (args == null || args.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            if (i < args.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
