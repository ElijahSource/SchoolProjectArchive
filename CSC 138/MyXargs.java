//Elijah *****, Csc 138, 09/15/2024, Project 1 MyXargs
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;//Won't function correctly unless specifically imported

public class MyXargs {
    private static final char[] blacklist = {';', '&', '|', '>', '<', '*', '?', '(', ')', '$'};//special character blacklist for input sanitization

    public static void main(String[] args) throws IOException, InterruptedException {

        //variables initialized
        boolean runOnlyWithInput = false; // for -r flag
        boolean printCommands = false;//for -t flag
        int maxArgs = -1; // max arguments for -n flag
        String replacementString = null; // string to replace for -i flag
        String usageError = "Usage: java MyXargs.java [-n num] [-I replace] [-t] [-r] command";

        List<String> baseCommand = new ArrayList<>(); // initialization of basecommand array, where we store commands before theyre fully processed

        for (int i = 0; i < args.length; i++) {//iterate through arguments
            String arg = args[i];//set string to current argument
            switch (arg) {//sets each flag given to active/does their logic
                case "-r":
                    runOnlyWithInput = true;
                    break;
                case "-t":
                    printCommands = true;
                    break;
                case "-n":
                    if (i + 1 < args.length) {// if next argument exists
                        try {
                            maxArgs = Integer.parseInt(args[++i]);//try parsing next argument for integer
                        } catch (NumberFormatException e) {
                            System.err.println(usageError);//error if argument after -n is not a valid number
                            System.exit(1);
                        }
                    } else {
                        System.err.println(usageError);//error if no argument after -n
                        System.exit(1);
                    }
                    break;
                case "-I":
                    if (i + 1 < args.length) { //if next arg exists
                        replacementString = args[++i]; //next arg set to replacement string
                    } else {
                        System.err.println(usageError);//error if no replacement string
                        System.exit(1);
                    }
                    break;
                default:
                    if (arg.startsWith("-")) {//if an arg starts with "-" and hasnt already been handled give usage error
                        System.err.println(usageError);
                        System.exit(1);
                    } else {
                        baseCommand.add(sanitizeInput(arg));//if arg doesn't start with '-' treat as part of command and sanitize input
                    }
                    break;
            }
        }

        if (baseCommand.isEmpty()) {//if base command empty throw usage
            System.err.println(usageError);
            System.exit(1);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));//read from input
        String line;
        StringBuilder inputBuilder = new StringBuilder();

        while ((line = reader.readLine()) != null) {//iterate through reader lines
            inputBuilder.append(sanitizeInput(line)).append(" ");
        }

        String input = inputBuilder.toString().trim();
        boolean hasInput = !input.isEmpty();

        if (hasInput) {
            if (maxArgs > 0) {
                // Process input words in batches
                List<String> inputWords = Arrays.asList(input.split("\\s+"));//uses \\s+ to split on white space
                for (int i = 0; i < inputWords.size(); i += maxArgs) {
                    List<String> batch = inputWords.subList(i, Math.min(i + maxArgs, inputWords.size()));
                    List<String> fullCommand = buildCommand(baseCommand, batch, replacementString);
                    executeCommand(fullCommand, printCommands);
                }
            } else {
                // Process all input at once
                List<String> fullCommand = buildCommand(baseCommand, Arrays.asList(input.split("\\s+")), replacementString);
                executeCommand(fullCommand, printCommands);
            }
        } else if (!runOnlyWithInput) {//case where no input and -r flag not set
            executeCommand(baseCommand, printCommands);
        } else {//case where no input and -r flag set
            System.exit(1);
        }
    }

    public static String sanitizeInput(String input) {//Sanitizes input for special characters found in blacklist
        if (input == null) { //if null input return blank
            return "";
        }

        StringBuilder pattern = new StringBuilder("[");
        for (char c : blacklist) {
            pattern.append(Pattern.quote(String.valueOf(c)));
        }
        pattern.append("]");

        return input.replaceAll(pattern.toString(), "");//remove characters from the blacklist
    }

    private static String formatCommand(List<String> command) {//format command function so commands appear readable to user rather than as an array
        StringBuilder sb = new StringBuilder();
        for (String arg : command) {//iterates through arguments
            if (sb.length() > 0) {
                sb.append(' ');
            }
            if (arg.contains(" ") || arg.contains("\"")) {//if spaces our quotes present
                sb.append('"').append(arg.replace("\"", "\\\"")).append('"');//Get rid of spaces/quotes
            } else {
                sb.append(arg);//appends to string builder
            }
        }
        return sb.toString();
    }

    private static List<String> buildCommand(List<String> baseCommand, List<String> args, String replacementString) {//builds full command
        List<String> fullCommand = new ArrayList<>(baseCommand);
        if (replacementString != null) {//if replacement string exits, -I set
            for (int i = 0; i < fullCommand.size(); i++) {//Iterate through new array containing basecommand
                String cmd = fullCommand.get(i);
                cmd = cmd.replace(replacementString, String.join(" ", args));//if replacement string replaces the replacement string with the intended output
                fullCommand.set(i, cmd);
            }
        } else {
            fullCommand.addAll(args);//if -I not set add all arguments to full command
        }
        return fullCommand;
    }

    private static void executeCommand(List<String> command, boolean printCommands) {
        try {
            if (printCommands) {//if printcommands , -t flag, set to true
                System.out.println("+ " + formatCommand(command));//formats and prints command
            }

            ProcessBuilder pb = new ProcessBuilder(command);//initialize process builder
            pb.inheritIO();//Inherit input output

            Process process = pb.start();//Start command
            int exitCode = process.waitFor();//Wait for exit code

            if (exitCode != 0) {//error checking
                System.err.println("Command failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error executing command: " + e.getMessage());
        }
    }
}
