package Commands;

import Commands.Builders.*;
import Common.Input;
import Common.Program;
import Data.DAO;

import java.util.HashMap;

/**
 * Class that recognize command from string words.
 * Class contains hashmap of commandBuilder that can build command objects.
 *
 * @since 1.0
 * @author safarislava
 */
public class CommandController {
    private final HashMap<String, CommandBuilder> commandBuilders;

    /**
     * Standard constructor
     *
     * @param program Value of class providing lifecycle of program
     * @param data Value of data access object
     * @param input Value of class proving asking missing information
     */
    public CommandController(Program program, DAO data, Input input) {
        this.commandBuilders = new HashMap<>(){{
            put("info", new InfoCommandBuilder(data));
            put("show", new ShowCommandBuilder(data));
            put("insert", new InsertCommandBuilder(data, input));
            put("update", new UpdateCommandBuilder(data, input));
            put("remove_key", new RemoveCommandBuilder(data));
            put("exit", new ExitCommandBuilder(program));
            put("save", new SaveCommandBuilder(data));
            put("clear", new ClearCommandBuilder(data));
            put("execute_script", new ExecuteScriptCommandBuilder(data));
            put("remove_greater", new RemoveGreaterCommandBuilder(input, data));
            put("remove_lower", new RemoveLowerCommandBuilder(input, data));
            put("remove_greater_key", new RemoveGreaterKeyCommandBuilder(data));
            put("count_less_than_type", new CountLessTypeCommandBuilder(data));
            put("filter_by_full_name", new FilterFullNameCommandBuilder(data));
            put("filter_contains_name", new FilterContainsNameCommandBuilder(data));
        }};
        this.commandBuilders.put("help", new HelpCommandBuilder(this));
    }

    /**
     * By string name of command recognize one and build command.
     *
     * @param name Value of command name
     * @param args Value of arguments this command
     */
    public void execute(String name, String[] args) {
        CommandBuilder builder = commandBuilders.get(name);

        if (builder == null) {
            throw new RuntimeException("No command found for name : " + name);
        }

        Command command = builder.build(args);
        command.execute();
    }

    /**
     * Getter of main routing hashmap
     *
     * @return Value of hashmap
     */
    public HashMap<String, CommandBuilder> getCommandBuilders() {
        return commandBuilders;
    }
}
