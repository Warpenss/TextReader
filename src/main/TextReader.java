package main;

import java.io.*;

public class TextReader {
    public static void main(String[] args) {
        System.out.println("Введите директорию .txt файла: ");

        String userInput = getUserInput().replace("\"", "");

        System.out.println("Вы ввели: " + userInput);

        String everything = plainText(userInput);

        System.out.println(everything);
    }

    private static String getUserInput(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String getUserInput = "";

        try {
            getUserInput = reader.readLine();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return getUserInput;
    }

    private static String plainText(String userInput){
        try (BufferedReader reader = new BufferedReader(new FileReader(userInput))) {
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();

            while (line != null){
                builder.append(line);
                builder.append(System.lineSeparator());
                line = reader.readLine();
            }

            userInput = builder.toString();
        }
        catch (IOException ioe) {
            System.out.println("Неправильный адрес");
            main(null);
        }

        return userInput;
    }

}
