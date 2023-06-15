package com.example.c;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**A class that reads input from the console*/
public class InputClireader {
    private static AvailableCommands availableCommands;
    /* вызываем входящий поток */
    private static Scanner inputReader = new Scanner(System.in);

    public static Scanner getInputReader() {
        return inputReader;
    }

    public static DataInOutStatus openStream() {
        try {
            OutStream.outputIntoCLI("Type commands");
            String inputData = inputReader.nextLine();
            while (true) {

                inputData = inputData.trim();
                DataInOutStatus checkedCommand = new CommandValidator().validate(inputData);
                if (checkedCommand != DataInOutStatus.SUCCESSFULLY) {
                    OutStream.outputIntoCLI(checkedCommand.getName());
                } if (inputData.equals("exit")){
                    System.out.println("Good buy my little friend");
                    break;
                }

                    OutStream.outputIntoCLI("Type commands");
                    inputData = inputReader.nextLine();

                }

            return DataInOutStatus.SUCCESSFULLY;
        } catch (NullPointerException | NoSuchElementException e) {
            OutStream.outputIntoCLI("You pressed ctrl + D");
            return DataInOutStatus.FAILED;
        }
    }
}
