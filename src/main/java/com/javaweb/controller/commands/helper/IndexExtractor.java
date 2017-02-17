package com.javaweb.controller.commands.helper;

import com.javaweb.controller.exception.ControllerException;
import com.javaweb.exception.ApplicationException;
import com.javaweb.i18n.ErrorMessageKeys;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;

import static com.javaweb.controller.CommandRegexAndPatterns.*;

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
    private static final String INCORRECT_INDEX_INSIDE_URI_FIELD_LOG =
            "Incorrect index inside URI : %s";

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
        try {
            Matcher idMatcher = INDEX_INSIDE_URI_PATTERN.matcher(requestedURI);
            if (idMatcher.find()) {
                return Integer.parseInt(idMatcher.group(0));
            } else {
                throw errorRequestUri(requestedURI);
            }
        } catch (NumberFormatException e) {
            throw errorIncorrectIndexInsideUri(requestedURI);
        }
    }

    public int extractIndexFromMatcher(Matcher matcher, String requestURI) {
        try {
            if (matcher.find()) {
                return Integer.parseInt(matcher.group(0));
            } else {
                throw errorRequestUri(requestURI);
            }
        } catch (NumberFormatException e) {
            throw errorIncorrectIndexInsideUri(requestURI);
        }
    }

    public int extractLastPartUriIndexFromRequest(HttpServletRequest request) {
        try {
            String requestedURI = request.getRequestURI();
            return Integer.parseInt(requestedURI.replaceAll(LETTERS_BEFORE_INDEX_REGEX, ""));
        } catch (NumberFormatException e) {
            throw errorIncorrectIndexInsideUri(request.getRequestURI());
        }
    }

    public int extractIndexFromParameter(String parameter) {
        try {
            Matcher idMatcher = PARAMETER_INDEX_ENDING_PATTERN.matcher(parameter);
            if (idMatcher.find()) {
                return Integer.parseInt(idMatcher.group(0));
            } else {
                throw errorIncorrectParameter(parameter);
            }
        } catch (NumberFormatException e) {
            throw errorIncorrectIndexParameter(parameter);
        }
    }

    public int extractIndexParameterFromRequest(HttpServletRequest request, String parameterName) {
        try {
            return Integer.parseInt(request.getParameter(parameterName));
        } catch (NumberFormatException e) {
            throw errorIncorrectIndexParameter(request.getParameter(parameterName));
        }
    }

    private ApplicationException errorRequestUri(String requestedURI) throws ControllerException {
        return new ControllerException()
                .addMessage(ErrorMessageKeys.ERROR_INCORRECT_URI)
                .addLogMessage(String.format(INCORRECT_URI_LOG_MSG, requestedURI))
                .setClassThrowsException(IndexExtractor.class);
    }

    private ApplicationException errorIncorrectIndexInsideUri(String requestedURI) throws ControllerException{
        return new ControllerException()
                .addMessage(ErrorMessageKeys.ERROR_INCORRECT_OR_EMPTY_FIELD)
                .addLogMessage(String.format(INCORRECT_INDEX_INSIDE_URI_FIELD_LOG, requestedURI))
                .setClassThrowsException(IndexExtractor.class);
    }

    private ApplicationException errorIncorrectParameter(String parameter) {
        return new ControllerException()
                .addMessage(ErrorMessageKeys.ERROR_INCORRECT_PARAMETER_NAME)
                .addLogMessage(String.format(INCORRECT_PARAMETER_LOG_MSG, parameter))
                .setClassThrowsException(IndexExtractor.class);
    }

    private ApplicationException errorIncorrectIndexParameter(String parameter) throws ControllerException{
        return new ControllerException()
                .addMessage(ErrorMessageKeys.ERROR_INCORRECT_OR_EMPTY_FIELD)
                .addLogMessage(String.format(INCORRECT_OR_EMPTY_NUMBER_FIELD_LOG, parameter))
                .setClassThrowsException(IndexExtractor.class);
    }
}
