package com.javaweb.controller.commands;

import com.javaweb.exception.ApplicationException;
import com.javaweb.i18n.ErrorMessageKeys;
import com.javaweb.util.Attributes;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Andrii Chernysh on 15-Feb-17. E-Mail : itcherry97@gmail.com
 */
public abstract class AbstractCommandWrapper<E> implements Command {
    private String pageToGoWithErrors;
    private static final Logger LOGGER = Logger.getLogger(AbstractCommandWrapper.class);

    protected AbstractCommandWrapper(String pageToGoWithErrors) {
        this.pageToGoWithErrors = pageToGoWithErrors;
    }

    protected abstract String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected abstract E getDataFromRequest(HttpServletRequest request);
    protected abstract void writePreviousDataToRequest(HttpServletRequest request, E data);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try {
            return performExecute(request, response);
        } catch (ApplicationException e) {
            processApplicationError(request, e);
        } catch (Exception e) {
            processUnknownException(request, e);
        }

        return pageToGoWithErrors;
    }

    private void processUnknownException(HttpServletRequest request, Exception e) {
        request.setAttribute(Attributes.ERROR_MESSAGE, ErrorMessageKeys.ERROR_UNKNOWN_EXCEPTION);
        LOGGER.error(e.getMessage());
    }

    private void processApplicationError(HttpServletRequest request, ApplicationException e) {
        request.setAttribute(Attributes.ERROR_MESSAGE, e.getMessage());
        Logger.getLogger(e.getClassThrowsException())
                .error(e.getLogMessage());
    }

}
