package com.javaweb.model.services.exception;

import com.javaweb.exception.ApplicationException;

/**
 * @author Andrii Chernysh on 28-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class ServiceException extends ApplicationException {
    @Override
    public ServiceException addMessage(String message) {
        super.addMessage(message);
        return this;
    }

    @Override
    public ServiceException addLogMessage(String logMessage) {
        super.addLogMessage(logMessage);
        return this;
    }
}
