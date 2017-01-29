package com.javaweb.controller;

import java.util.regex.Pattern;

/**
 * @author Andrii Chernysh on 26-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public final class CommandRegexAndPatterns {
    public static final String LETTERS_BEFORE_INDEX_REGEX = "\\D+";
    public static final String INDEX_ENDING_URI_REGEX = "\\d+$";
    public static final String USER_ACCOUNT_PAGE_REGEX = ".*rest\\/user\\/.+";
    public static final String ANSWER_BEGINNING_REGEX = "^answer";

    public static final String LOGIN_REGEX = "^[a-zA-Z0-9_-]{3,15}$";
    public static final String PASSWORD_REGEX = "\\S{4,14}";

    public static final Pattern LOGIN_PATTERN =
            Pattern.compile(LOGIN_REGEX);
    public static final Pattern PASSWORD_PATTERN =
            Pattern.compile(PASSWORD_REGEX);
}
