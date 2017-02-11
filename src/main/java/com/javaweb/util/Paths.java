package com.javaweb.util;

public final class Paths {
	private static final String REST = "/rest";
	public static final String TUTOR = "/tutor";
	public static final String STUDENT = "/student";
	public static final String TEST = "/test";
	public static final String LOGIN = REST + "/login";
	public static final String HOME = REST + "/home";
	public static final String REGISTER = REST + "/register";
	public static final String LOGOUT = REST + "/logout";
	public static final String SUBJECTS = REST + "/subject";
	public static final String CONCRETE_SUBJECT = REST + "/subject/id";
	public static final String TESTS = REST + TEST;
	public static final String CONCRETE_STUDENT_TEST = TESTS + STUDENT + "/id";
	public static final String CONCRETE_TUTOR_TEST = TESTS + TUTOR + "/id";

	public static final String RESULTS = "/results";
	public static final String TEST_RESULTS = CONCRETE_STUDENT_TEST + RESULTS + "/id";

	public static final String ADD_ANSWER_TO_HISTORY = REST + "/add_answer";
	public static final String USER_INFO = REST + "/user";
	public static final String USER_INFO_USERNAME = REST + "/user/username";
	public static final String SAVE_TEST_RECORD = REST + "/save_test";

	public static final String ADD_SUBJECT = SUBJECTS + "/add";
	public static final String ADD_TEST = TESTS + "/add";

	public static final String ADD_TASK = "/add_task";
	public static final String UPDATE_TASK = "/update_task";
	public static final String ADD_TUTOR_TASK = CONCRETE_TUTOR_TEST + ADD_TASK;
	public static final String UPDATE_TUTOR_TASK = CONCRETE_TUTOR_TEST + UPDATE_TASK;

	public static final String STUDENTS_LIST = REST + "/students";
	public static final String STUDENT_TESTS_LIST = STUDENTS_LIST + "/id" + TEST;
	public static final String REDIRECTED = "redirected";

}
