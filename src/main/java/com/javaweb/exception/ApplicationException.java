package com.javaweb.exception;

/**
 * @author Andrii Chernysh on 28-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class ApplicationException extends RuntimeException {
    private StringBuffer message;
    private StringBuffer logMessage;

    public ApplicationException() {
        this.message = new StringBuffer();
        this.logMessage = new StringBuffer();
    }

    public ApplicationException(String message) {
        super(message);
        this.message = new StringBuffer(message);
        this.logMessage = new StringBuffer();
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
        this.message = new StringBuffer(message);
        this.logMessage = new StringBuffer();
    }

    protected ApplicationException addMessage(String message) {
        this.message.append(message);
        return this;
    }

    protected ApplicationException addLogMessage(String logMessage) {
        this.logMessage.append(logMessage);
        return this;
    }

    @Override
    public String getMessage() {
        return message.toString();
    }

    public String getLogMessage() {
        return logMessage.toString();
    }


}
