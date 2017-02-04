package com.javaweb.controller.commands;

import com.javaweb.controller.commands.login.LoginSubmitCommand;
import com.javaweb.controller.commands.login.LoginCommand;
import com.javaweb.controller.commands.register.RegisterCommand;
import com.javaweb.controller.commands.register.RegisterSubmitCommand;

import java.util.HashMap;
import java.util.Map;

import static com.javaweb.util.Paths.*;

/**
 * @author Andrii Chernysh on 24-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class CommandHolder {
    private static final String GET = "GET:";
    private static final String POST = "POST:";

    private final UnsupportedOperationCommand unsupportedOperationCommand =
            new UnsupportedOperationCommand();
    private Map<String, Command> commands;

    public CommandHolder() {
        commands = new HashMap<>();
        initCommands();
    }

    private void initCommands(){
        commands.put(POST + LOGIN, new LoginSubmitCommand());
        commands.put(GET + LOGIN, new LoginCommand());
        commands.put(GET + LOGOUT, new LogoutCommand());
        commands.put(GET + USER_INFO_USERNAME, new GetUserInfo());
        commands.put(GET + HOME, new GetHomeCommand());
        commands.put(GET + SUBJECTS, new GetSubjectsCommand());
        commands.put(GET + CONCRETE_SUBJECT, new GetTestsCommand());
        commands.put(GET + CONCRETE_STUDENT_TEST, new GetStudentConcreteTestCommand());
        commands.put(POST + ADD_SUBJECT, new PostAddSubjectCommand());
        commands.put(POST + REGISTER, new RegisterSubmitCommand());
        commands.put(GET + REGISTER, new RegisterCommand());
        commands.put(POST + SAVE_TEST_RECORD, new PostSavePersonTestRecord());
        commands.put(POST + CONCRETE_STUDENT_TEST, new PostAddAnswerCommand());
        commands.put(GET + TEST_RESULTS, new TestResultCommand());
        commands.put(POST + CONCRETE_SUBJECT, new PostAddTestCommand());
        commands.put(GET + CONCRETE_TUTOR_TEST, new GetTutorConcreteTestCommand());
    }

    public Command getCommandByKey(String commandKey){
        return commands.getOrDefault(commandKey, unsupportedOperationCommand);
    }
}
