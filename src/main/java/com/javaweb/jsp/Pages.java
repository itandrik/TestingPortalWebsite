package com.javaweb.jsp;

public class Pages {
    public static final String FORMAT = ".jsp";
    public static final String PATH = "/WEB-INF/view/jsp";
    public static final String TUTOR_PATH = "/tutor";
    public static final String STUDENT_PATH = "/student";

    public static final String LOGIN_PAGE = "/login";
    public static final String SUBJECTS_PAGE = "/subjects";

    public static final String LOGIN_PAGE_WITH_PATH = PATH + LOGIN_PAGE + FORMAT;
    public static final String HOME_PAGE = LOGIN_PAGE_WITH_PATH;

    public static final String STUDENT_SUBJECTS_PAGE_WITH_PATH =
            PATH + STUDENT_PATH + SUBJECTS_PAGE + FORMAT;
}
