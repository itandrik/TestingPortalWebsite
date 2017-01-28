package com.javaweb.util;

public final class Paths {
	private static final String REST = "/rest";

	public static final String LOGIN = REST + "/login";
	public static final String HOME = REST + "/home";
	public static final String REGISTER = REST + "/register";
	public static final String LOGOUT = REST + "/logout";
	public static final String SUBJECTS = REST + "/subject";
	public static final String CONCRETE_SUBJECT = REST + "/subject/id";
	public static final String TESTS = REST + "/test";
	public static final String CONCRETE_TEST = REST + "/test/id";
	public static final String USER_INFO = REST + "/user/username";

	public static final String ADD_SUBJECT = SUBJECTS + "/add";
	public static final String REDIRECTED = "redirected";

}
