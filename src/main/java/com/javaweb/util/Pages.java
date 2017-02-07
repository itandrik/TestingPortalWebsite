package com.javaweb.util;

public final class Pages {

    private static final String JSP = ".jsp";
    private static final String PATH = "/WEB-INF/view/jsp";

    public static final String LOGIN_PAGE = PATH + "/login" + JSP;
    public static final String REGISTER_PAGE = PATH + "/register" + JSP;
    public static final String USER_ACCOUNT_PAGE = PATH + "/user_account" + JSP;
    public static final String HOME_PAGE = PATH + "/home" + JSP;
    public static final String SUBJECTS_PAGE = PATH + "/subjects" + JSP;
    public static final String TESTS_PAGE = PATH + "/tests" + JSP;
    public static final String CONCRETE_STUDENT_TEST_PAGE = PATH + "/concrete_student_test" + JSP;
    public static final String CONCRETE_TUTOR_TEST_PAGE = PATH + "/concrete_tutor_test" + JSP;

    public static final String TEST_RESULTS_PAGE = PATH + "/test_results" + JSP;
    public static final String ADD_TEST_PAGE = PATH + "/test_add" + JSP;
}
