package dev.zbib.userservice.model.validation;

import dev.zbib.userservice.model.enums.UserRoles;
import dev.zbib.userservice.model.request.ProviderRequest;
import dev.zbib.userservice.model.request.UserRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ProviderDetailsValidator implements ConstraintValidator<ValidProviderDetails, UserRequest> {

    @Override
    public boolean isValid(
            UserRequest userRequest,
            ConstraintValidatorContext context) {
        if (userRequest.getRole() != null && userRequest.getRole() == UserRoles.PROVIDER) {

            ProviderRequest providerDetails = userRequest.getProviderDetails();

            if (providerDetails == null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Provider details are required for role PROVIDER.")
                        .addPropertyNode("providerDetails")
                        .addConstraintViolation();
                return false;
            }

            if (providerDetails.getServiceType() == null || providerDetails.getServiceType()
                    .isEmpty()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Service type is required for providers.")
                        .addPropertyNode("providerDetails.serviceType")
                        .addConstraintViolation();
                return false;
            }

            if (providerDetails.getHourlyRate() <= 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Hourly rate must be greater than zero.")
                        .addPropertyNode("providerDetails.hourlyRate")
                        .addConstraintViolation();
                return false;
            }

            if (providerDetails.getServiceArea() == null || providerDetails.getServiceArea()
                    .isEmpty()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Service area is required for providers.")
                        .addPropertyNode("providerDetails.serviceArea")
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }


}
