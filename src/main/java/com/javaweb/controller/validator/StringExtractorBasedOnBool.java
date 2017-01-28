package com.javaweb.controller.validator;

/**
 * @author Andrii Chernysh on 28-Jan-17. E-Mail : itcherry97@gmail.com
 */
@FunctionalInterface
public interface StringExtractorBasedOnBool {
    String extractIfPositive(boolean logicalExpression, String message);
}
