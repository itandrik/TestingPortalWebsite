package com.javaweb.controller.validator;

/**
 * @author Andrii Chernysh on 30-Jan-17. E-Mail : itcherry97@gmail.com
 */
@FunctionalInterface
public interface NullChecker<T> {
    boolean isEmpty(T object);
}
