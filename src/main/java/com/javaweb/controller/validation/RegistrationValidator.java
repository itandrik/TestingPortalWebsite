package com.javaweb.controller.validation;

import com.javaweb.model.entity.person.Person;
import com.javaweb.model.entity.util.LoginData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.javaweb.controller.CommandRegexAndPatterns.NAME_PATTERN;
import static com.javaweb.i18n.ErrorMessageKeys.*;

/**
 * @author Andrii Chernysh on 15-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class RegistrationValidator implements Validator<Person> {
    private boolean isNotEmptyFirstName;
    private boolean isValidFirstName;
    private boolean isNotEmptySecondName;
    private boolean isValidSecondName;
    private boolean isNotEmptyGender;
    private boolean isNotEmptyRole;

    private Validator<LoginData> loginDataValidator = new AuthValidator();
    private StringExtractorBasedOnBool extractor = (isPositive, msg) -> isPositive ? msg : null;
    private NullChecker<String> nameNullChecker = (name) -> name == null || name.isEmpty();

    public boolean isValid(Person person) {
        isNotEmptyFirstName = !nameNullChecker.isEmpty(person.getFirstName());
        isValidFirstName = isValidName(person.getFirstName());
        isNotEmptySecondName = !nameNullChecker.isEmpty(person.getSecondName());
        isValidSecondName = isValidName(person.getSecondName());
        isNotEmptyGender = person.getGender() != null;
        isNotEmptyRole = person.getRole() != null;
        LoginData loginData = new LoginData(person.getLogin(), person.getPassword());

        return loginDataValidator.isValid(loginData) && isNotEmptyFirstName &&
                isNotEmptySecondName && isValidFirstName && isValidSecondName &&
                isNotEmptyGender && isNotEmptyRole;
    }

    private boolean isValidName(String name) {
        return NAME_PATTERN.matcher(name).matches();
    }

    @Override
    public List<String> getErrorMessages() {
        List<String> result = new ArrayList<>();

        result.add(extractor.extractIfPositive(!isNotEmptyFirstName, ERROR_EMPTY_FIRST_NAME));
        result.add(extractor.extractIfPositive(!isNotEmptySecondName, ERROR_EMPTY_SECOND_NAME));
        result.add(extractor.extractIfPositive(!isNotEmptyGender, ERROR_EMPTY_GENDER));
        result.add(extractor.extractIfPositive(!isNotEmptyRole, ERROR_EMPTY_ROLE));
        result.addAll(loginDataValidator.getErrorMessages());

        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<String> getErrorValidationMessages() {
        List<String> result = new ArrayList<>();

        result.add(extractor.extractIfPositive(!isValidFirstName, ERROR_WRONG_FIRST_NAME));
        result.add(extractor.extractIfPositive(!isValidSecondName, ERROR_WRONG_SECOND_NAME));
        result.addAll(loginDataValidator.getErrorValidationMessages());

        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
