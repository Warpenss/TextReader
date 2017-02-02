package main.java;

import org.mozilla.universalchardet.UniversalDetector;

import java.io.*;

public class TextReader {
    public static void main(String[] args) {
        System.out.println("Введите директорию .txt файла: ");

        String userInput = getUserInput().replace("\"", "");

        System.out.println("Вы ввели = " + userInput);

        String everything = plainText(userInput);

        System.out.println(" ");
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

        if (getUserInput.contains("\"")){
            System.out.println("В адресе бнаружен недопустимый символ \" . Он будет удалён.");
        }

        return getUserInput.replace("\"", "");
    }

    private static String encoding(String userInput){
        byte[] buf = new byte[4096];

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(userInput);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
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

        return encoding;
    }

    private static String plainText(String userInput){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(userInput), encoding(userInput)));
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
            System.out.println("Неверный адрес. Попробуйте снова.");
            System.out.println(" ");
            main(null);


        }

        return userInput;
    }


}
