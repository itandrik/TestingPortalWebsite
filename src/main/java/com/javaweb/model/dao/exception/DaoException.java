package com.javaweb.model.dao.exception;

import com.javaweb.exception.ApplicationException;

/**
 * @author Andrii Chernysh on 28-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class DaoException extends ApplicationException{
    @Override
    public DaoException addMessage(String message) {
        super.addMessage(message);
        return this;
    }

    @Override
    public DaoException addLogMessage(String logMessage) {
        super.addLogMessage(logMessage);
        return this;
    }
}
