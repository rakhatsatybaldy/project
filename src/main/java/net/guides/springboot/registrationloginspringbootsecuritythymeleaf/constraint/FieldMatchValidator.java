package net.guides.springboot.registrationloginspringbootsecuritythymeleaf.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FieldMatchValidator implements ConstraintValidator < FieldMatch, Object > {
    private String nameField;

    @Override
    public void initialize(final FieldMatch constraintAnnotation) {
        nameField = constraintAnnotation.first();

    }

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        try {
            final Object firstObj = BeanUtils.getProperty(value, nameField);
            return firstObj == null  || firstObj != null && firstObj.equals(firstObj);
        } catch (final Exception ignore) {}
        return true;
    }
}
