//package com.example.c;
//import main.org.example.main.CommandFactory;
//
//import java.io.*;
//import java.util.ArrayList;
//
//public class ScriptReader {
//    private static ArrayList<String> historyOfFiles = new ArrayList<>();
//    private static int readiedCommands = new ArrayList<Object>();
//    private static Integer currentCommand;
//    private static String file;
//    private static boolean executeStatus = false;
//    private final File files;
//    private final CommandReader commandReader;
//
//    public ScriptReader(File aFile) throws FileNotFoundException {
//        files = aFile;
//        commandReader = CommandReader.getInstance();
//    }
//
//
//    public static void setFile(String file) {
//        ScriptReader.file = file;
//    }
//
//    public static void setCurrentCommand(Integer currentCommand) {
//        ScriptReader.currentCommand = currentCommand;
//    }
//
//    public static Integer getCurrentCommand() {
//        return currentCommand;
//    }
//
//    public static ArrayList<String> getReadiedCommands() {
//        return readiedCommands;
//    }
//
//    public static void setExecuteStatus(boolean executeStatus1) {
//        executeStatus = executeStatus1;
//    }
//
//    public static boolean getExecuteStatus() {
//        return executeStatus;
//    }
//
//    public static void execute() {
//        StringBuilder execution = new StringBuilder();
//        if (historyOfFiles.contains(file)) {
//            historyOfFiles = new ArrayList<>();
//            System.out.println(execution.append("Recursion was detected in your files"));
//
//        } else {
//            historyOfFiles.add(file);
//            currentCommand = 0;
//            try {
//                readiedCommands = new FileReader(file).read();
//            } catch (FileNotFoundException e) {
//                throw new RuntimeException(e);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            int iter = 0;
//
//            if (readiedCommands != 0) {
//                setExecuteStatus(true);
//                while (iter < readiedCommands) {
//                    String commandLine = String.valueOf(readiedCommands);
//                    if (new CommandValidator().validate(commandLine) != DataInOutStatus.SUCCESSFULLY) {
//                        System.out.append(execution.append("Check correctness of commands in your script '").append(file).append("'. Failed.\nSome commands can't be completed.").toString());
//                    }
//                    currentCommand++;
//                    iter = currentCommand;
//                }
//                setExecuteStatus(false);
//            } else {
//                System.out.append(execution.append("There are some errors with file '").append(file).append("'. Try again.").toString());
//            }
//        }
//    }
//
//    public void read() throws IOException {
//        String nextLine;
//        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(files))) {
//            do {
//                nextLine = bufferedReader.readLine();
//                if (nextLine == null) return;
//
//                Consolee.getInstance().setBufferedReader(bufferedReader);
//
//                CommandFactory newCommand = commandReader.readCommand(nextLine + " ");
//                CommandReader.getInstance().execute(newCommand);
//            } while (true);
//        }
//
//    }
//}