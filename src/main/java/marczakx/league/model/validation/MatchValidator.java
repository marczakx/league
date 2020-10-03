package marczakx.league.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import marczakx.league.model.Match;

public class MatchValidator implements ConstraintValidator<ValidMatch, Match> {

	@Override
	public void initialize(ValidMatch validMatch) {
	}

	@Override
	public boolean isValid(Match match, ConstraintValidatorContext cxt) {
		return match.checkIsMatchCorrect();
	}

}