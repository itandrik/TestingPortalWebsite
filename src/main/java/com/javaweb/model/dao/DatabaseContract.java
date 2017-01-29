package com.javaweb.model.dao;

public interface DatabaseContract {
	String DB_NAME = "testing_portal_javaweb";
	
	/* Table name and names of columns for Answer table */
	String ANSWER_TABLE_NAME = "answer";
	String ANSWER_ID_COLUMN_NAME = "answer_id";
	String ANSWER_TEXT_COLUMN_NAME = "text";
	String ANSWER_IS_CORRECT_COLUMN_NAME = "is_correct";
	
	/* Table name and names of columns for Person_has_answer table */
	String PERSON_HAS_ANSWER_TABLE_NAME = "Person_has_answer";
	
	/* Table name and names of columns for M2m_tests_tasks table */
	String M2M_TESTS_TASKS_TABLE_NAME = "M2m_tests_tasks";
	
	/* Table name and names of columns for Person table */
	String PERSON_TABLE_NAME = "person";
	String PERSON_ID_COLUMN_NAME = "person_id";
	String PERSON_FIRST_NAME_COLUMN_NAME = "first_name";
	String PERSON_SECOND_NAME_COLUMN_NAME = "second_name";
	String PERSON_GENDER_COLUMN_NAME = "gender";
	String PERSON_LOGIN_COLUMN_NAME = "login";
	String PERSON_PASSWORD_COLUMN_NAME = "password";
	String PERSON_ROLE_COLUMN_NAME = "role";
	
	/* Table name and names of columns for Person table */
	String PERSON_TEST_HISTORY_TABLE_NAME = "person_test_history";
	
	/* Table name and names of columns for Subject table */
	String SUBJECT_TABLE_NAME = "subject";
	String SUBJECT_ID_COLUMN_NAME = "subject_id";
	String SUBJECT_NAME_COLUMN_NAME = "name";
	
	/* Table name and names of columns for Task table */
	String TASK_TABLE_NAME = "task";
	String TASK_ID_COLUMN_NAME = "task_id";
	String TASK_QUESTION_COLUMN_NAME = "question";
	String TASK_ANSWERS_TYPE_COLUMN_NAME = "answers_type";
	String TASK_EXPLANATION_COLUMN_NAME = "explanation";
	
	/* Table name and names of columns for Test table */
	String TEST_TABLE_NAME = "test";
	String TEST_ID_COLUMN_NAME = "test_id";
	String TEST_NAME_COLUMN_NAME = "name";
	String TEST_DURATION_TIME_COLUMN_NAME = "duration_time_in_minutes";

	/* Table name and names of columns for Person_test_history table */
	String PERSON_HISTORY_TABLE_NAME = "Person_test_history";
	String END_TIME_COLUMN_NAME = "end_time";
	String GRADE_COLUMN_NAME = "grade";

}
