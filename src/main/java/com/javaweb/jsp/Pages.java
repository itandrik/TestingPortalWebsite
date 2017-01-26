package com.javaweb.jsp;

public final class Pages {

    private static final String FORMAT = ".jsp";
    private static final String PATH = "/WEB-INF/view/jsp";

    public static final String LOGIN_PAGE = "/login";
    public static final String HOME_PAGE = "/home";
    public static final String SUBJECTS_PAGE = "/subjects";
    public static final String TESTS_PAGE = "/tests";
    public static final String CONCRETE_TEST_PAGE = "/concrete_test";

    public static final String LOGIN_PAGE_WITH_PATH = PATH + LOGIN_PAGE + FORMAT;
    public static final String HOME_PAGE_WITH_PATH = PATH + HOME_PAGE + FORMAT;

    public static final String SUBJECTS_PAGE_WITH_PATH =
            PATH  + SUBJECTS_PAGE + FORMAT;
    public static final String TESTS_PAGE_WITH_PATH =
            PATH + TESTS_PAGE + FORMAT;
    public static final String CONCRETE_TEST_PAGE_WITH_PATH =
            PATH + CONCRETE_TEST_PAGE + FORMAT;
}
