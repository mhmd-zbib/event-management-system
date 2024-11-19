package dev.zbib.userservice.model.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ProviderDetailsValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidProviderDetails {
    String message() default "Invalid provider details";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
