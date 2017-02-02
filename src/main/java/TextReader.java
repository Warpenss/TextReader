package main.java;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;

public class TextReader {
    public static void main(String[] args) {
        System.out.println("Введите директорию .txt файла: ");

        String userInput = getUserInput().replace("\"", "");

        System.out.println("Вы ввели = " + userInput);

        encoding(userInput);

        String everything = "";

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

    private static void encoding(String userInput){
        byte[] buf = new byte[4096];

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(userInput);
        }
        catch (FileNotFoundException e) {
            System.out.println("Неверный адрес. Попробуйте снова.");
            main(null);
        }

        UniversalDetector detector = new UniversalDetector(null);

        int nread;

        try {
            if (fis != null) {
                while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                        detector.handleData(buf, 0, nread);
                    }
            }
        }
        catch (NullPointerException npe) {
            System.out.println("Беда - NullPointerException. Откуда он появился?");
            npe.printStackTrace();
        }
        catch (IOException ioe) {
            System.out.println("Страшная беда! Wild IOException Appeared!");
            ioe.printStackTrace();
        }


        detector.dataEnd();

        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            System.out.println("Определена кодировка = " + encoding);
        } else {
            System.out.println("Не удалось определить кодировку. ");
        }

        detector.reset();
    }


    /*private static String plainText(String userInput){
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
    }*/


}
