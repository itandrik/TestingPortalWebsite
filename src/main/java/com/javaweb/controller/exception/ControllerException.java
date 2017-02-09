package com.javaweb.controller.exception;

import com.javaweb.exception.ApplicationException;

/**
 * @author Andrii Chernysh on 28-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class ControllerException extends ApplicationException{
    @Override
    public ControllerException addMessage(String message) {
        super.addMessage(message);
        return this;
    }

    @Override
    public ControllerException addLogMessage(String logMessage) {
        super.addLogMessage(logMessage);
        return this;
    }
}
