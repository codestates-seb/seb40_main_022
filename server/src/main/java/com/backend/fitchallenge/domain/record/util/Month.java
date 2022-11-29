package com.backend.fitchallenge.domain.record.util;

import javax.validation.Constraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Min(1) @Max(12)
public @interface Month {
}
