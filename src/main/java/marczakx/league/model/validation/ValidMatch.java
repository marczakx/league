package marczakx.league.model.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = MatchValidator.class)
@Retention(RUNTIME)
public @interface ValidMatch {
	String message() default "Invalid match";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
