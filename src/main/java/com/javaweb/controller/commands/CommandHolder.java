package com.javaweb.controller.commands;

import java.util.HashMap;
import java.util.Map;
import static com.javaweb.jsp.Paths.*;

/**
 * @author Andrii Chernysh on 24-Jan-17.
 *         E-Mail : itcherry97@gmail.com
 */
public class CommandHolder {
    public static final String GET = "GET:";
    public static final String POST = "POST:";

    private final UnsupportedOperationCommand unsupportedOperationCommand =
            new UnsupportedOperationCommand();
    private Map<String, Command> commands;

    public CommandHolder() {
        commands = new HashMap<>();
        initCommands();
    }

    private void initCommands(){
        commands.put(GET + LOGIN, new GetAuthenticationCommand());
        commands.put(GET + LOGOUT, null);
        commands.put(GET + REGISTER, null);
        commands.put(GET + HOME, new GetHomeCommand());
        commands.put(GET + SUBJECTS, new GetSubjectsCommand());
    }

    public Command getCommandByKey(String commandKey){
        return commands.getOrDefault(commandKey, unsupportedOperationCommand);
    }
}
