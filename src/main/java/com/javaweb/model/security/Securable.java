package com.javaweb.model.security;

/**
 * @author Andrii Chernysh on 12-Feb-17. E-Mail : itcherry97@gmail.com
 */
public interface Securable {
    String encryptPassword(String password);
    boolean checkPassword(String password, String hash);
}
