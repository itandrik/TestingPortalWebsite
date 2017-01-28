package com.javaweb.model.entity.util;

/**
 * @author Andrii Chernysh on 28-Jan-17. E-Mail : itcherry97@gmail.com
 */
public class LoginData {
    private String login;
    private String password;

    public LoginData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public LoginData setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginData setPassword(String password) {
        this.password = password;
        return this;
    }

    public void encryptPassword(){

    }
}
