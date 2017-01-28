package com.javaweb.util;

public final class Pages {

    private static final String JSP = ".jsp";
    private static final String PATH = "/WEB-INF/view/jsp";

    public static final String LOGIN_PAGE = "/login";
    public static final String REGISTER_PAGE = "/register";
    public static final String HOME_PAGE = "/home";
    public static final String SUBJECTS_PAGE = "/subjects";
    public static final String TESTS_PAGE = "/tests";
    public static final String CONCRETE_TEST_PAGE = "/concrete_test";

    public static final String LOGIN_PAGE_WITH_PATH = PATH + LOGIN_PAGE + JSP;
    public static final String REGISTER_PAGE_WITH_PATH = PATH + REGISTER_PAGE + JSP;
    public static final String HOME_PAGE_WITH_PATH = PATH + HOME_PAGE + JSP;

    public static final String SUBJECTS_PAGE_WITH_PATH =
            PATH  + SUBJECTS_PAGE + JSP;
    public static final String TESTS_PAGE_WITH_PATH =
            PATH + TESTS_PAGE + JSP;
    public static final String CONCRETE_TEST_PAGE_WITH_PATH =
            PATH + CONCRETE_TEST_PAGE + JSP;
}
