package com.javaweb.controller.commands;

import com.javaweb.controller.commands.login.GetAuthenticationCommand;
import com.javaweb.controller.commands.login.LoginCommand;
import com.javaweb.controller.commands.register.RegisterCommand;
import com.javaweb.controller.commands.register.RegisterSubmitCommand;

import java.util.HashMap;
import java.util.Map;

import static com.javaweb.jsp.Paths.*;

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
        commands.put(GET + AUTHENTICATE, new GetAuthenticationCommand());
        commands.put(GET + LOGIN, new LoginCommand());
        commands.put(GET + LOGOUT, null);//TODO
        commands.put(GET + HOME, new GetHomeCommand());
        commands.put(GET + SUBJECTS, new GetSubjectsCommand());
        commands.put(GET + CONCRETE_SUBJECT, new GetTestsCommand());
        commands.put(GET + CONCRETE_TEST, new GetConcreteTestCommand());
        commands.put(POST + ADD_SUBJECT, new PostAddSubjectCommand());
        commands.put(POST + REGISTER_SUBMIT, new RegisterSubmitCommand());
        commands.put(GET + REGISTER, new RegisterCommand());
    }

    public Command getCommandByKey(String commandKey){
        return commands.getOrDefault(commandKey, unsupportedOperationCommand);
    }
}
