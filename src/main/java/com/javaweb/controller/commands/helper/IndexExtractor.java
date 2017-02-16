package com.javaweb.controller.commands.helper;

import com.javaweb.controller.exception.ControllerException;
import com.javaweb.i18n.ErrorMessageKeys;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;

import static com.javaweb.controller.CommandRegexAndPatterns.INDEX_INSIDE_URI_PATTERN;
import static com.javaweb.controller.CommandRegexAndPatterns.LETTERS_BEFORE_INDEX_REGEX;
import static com.javaweb.controller.CommandRegexAndPatterns.PARAMETER_INDEX_ENDING_PATTERN;

/**
 * @author Andrii Chernysh on 16-Feb-17. E-Mail : itcherry97@gmail.com
 */
public class IndexExtractor {
    private static final String INCORRECT_URI_LOG_MSG =
            "URI %s is incorrect";
    private static final String INCORRECT_PARAMETER_LOG_MSG =
            "Parameter %s is incorrect";
    private static final String INCORRECT_OR_EMPTY_NUMBER_FIELD_LOG =
            "Number field with parameter %s was empty or incorrect";

    private static class Holder {
        static final IndexExtractor INSTANCE = new IndexExtractor();
    }

    public static IndexExtractor getInstance() {
        return IndexExtractor.Holder.INSTANCE;
    }

    private IndexExtractor() {
    }

    public int extractIndexInsideUriFromRequest(HttpServletRequest request) {
        String requestedURI = request.getRequestURI();
        int index = 0;
        Matcher idMatcher = INDEX_INSIDE_URI_PATTERN.matcher(requestedURI);
        if (idMatcher.find()) {
            index = Integer.parseInt(idMatcher.group(0));
        }
        return index;
    }

    public int extractIndexFromMatcher(Matcher matcher, String requestURI) {
        int result;
        if (matcher.find()) {
            result = Integer.parseInt(matcher.group(0));
        } else {
            throw new ControllerException()
                    .addMessage(ErrorMessageKeys.ERROR_INCORRECT_URI)
                    .addLogMessage(String.format(INCORRECT_URI_LOG_MSG, requestURI))
                    .setClassThrowsException(IndexExtractor.class);
        }
        return result;
    }

    public int extractLastPartUriIndexFromRequest(HttpServletRequest request) {
        String requestedURI = request.getRequestURI();
        return Integer.parseInt(requestedURI.replaceAll(LETTERS_BEFORE_INDEX_REGEX, ""));
    }

    public int extractIndexFromParameter(String parameter) {
        int answerId = 0;
        Matcher idMatcher = PARAMETER_INDEX_ENDING_PATTERN.matcher(parameter);
        if (idMatcher.find()) {
            answerId = Integer.parseInt(idMatcher.group(0));
        } else {
            throw new ControllerException()
                    .addMessage(ErrorMessageKeys.ERROR_INCORRECT_URI)
                    .addLogMessage(String.format(INCORRECT_PARAMETER_LOG_MSG, parameter))
                    .setClassThrowsException(IndexExtractor.class);
        }
        return answerId;
    }

    public int extractIndexParameterFromRequest(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (NumberFormatException e) {
            throw new ControllerException()
                    .addMessage(ErrorMessageKeys.ERROR_INCORRECT_OR_EMPTY_FIELD)
                    .addLogMessage(String.format(INCORRECT_OR_EMPTY_NUMBER_FIELD_LOG, parameterName))
                    .setClassThrowsException(IndexExtractor.class);
        }
    }
}
