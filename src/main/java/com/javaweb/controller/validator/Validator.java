package com.javaweb.controller.validator;

import java.util.List;

/**
 * @author Andrii Chernysh on 28-Jan-17. E-Mail : itcherry97@gmail.com
 */
public interface Validator<T> {
    boolean isValid(T object);
    List<String> getErrorMessages();
    List<String> getErrorValidationMessages();

}
