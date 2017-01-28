package com.javaweb.controller.validator;

import com.javaweb.model.entity.util.LoginData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.javaweb.controller.CommandRegexAndPatterns.LOGIN_PATTERN;
import static com.javaweb.controller.CommandRegexAndPatterns.PASSWORD_PATTERN;
import static com.javaweb.i18n.ErrorMessageKeys.*;

/**
 * @author Andrii Chernysh on 28-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class AuthValidator implements Validator<LoginData> {
    private boolean isLoginNotEmpty;
    private boolean isLoginValid;
    private boolean isPasswordNotEmpty;
    private boolean isPasswordValid;

    private StringExtractorBasedOnBool extractor = (isPositive, msg) -> isPositive ? msg : null;

    @Override
    public boolean isValid(LoginData loginData) {
        isLoginNotEmpty = (loginData.getLogin() != null) &&
                (!loginData.getLogin().isEmpty());
        isLoginValid = LOGIN_PATTERN.matcher(loginData.getLogin()).matches();
        isPasswordNotEmpty = loginData.getPassword() != null &&
                (!loginData.getPassword().isEmpty());
        isPasswordValid = PASSWORD_PATTERN.matcher(loginData.getLogin()).matches();

        return isLoginNotEmpty && isLoginValid && isPasswordNotEmpty && isPasswordValid;
    }

    @Override
    public List<String> getErrorMessages() {
        List<String> result = new ArrayList<>();

        result.add(extractor.extractIfPositive(!isLoginNotEmpty, ERROR_EMPTY_LOGIN));
        result.add(extractor.extractIfPositive(!isPasswordNotEmpty, ERROR_EMPTY_PASSWORD));

        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<String> getErrorValidationMessages() {
        List<String> result = new ArrayList<>();

        result.add(extractor.extractIfPositive(!isLoginValid, ERROR_WRONG_LOGIN));
        result.add(extractor.extractIfPositive(!isPasswordValid, ERROR_WRONG_PASSWORD));

        return result.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
