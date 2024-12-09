import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class Main {


    public static void processFile(String inputPath, String outputPath) {

        String vowels = "аеёиоуыэюяАЕЁИОУЫЭЮЯ";
        String consonants = "бвгджзйклмнпрстфхцчшщБВГДЖЗЙКЛМНПРСТФХЦЧШЩ";


        int vowelCount = 0;
        int consonantCount = 0;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputPath), StandardCharsets.UTF_8);
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputPath), StandardCharsets.UTF_8)) {

            String line;
            while ((line = reader.readLine()) != null) {
                StringBuilder processedLine = new StringBuilder();

                for (char c : line.toCharArray()) {
                    if (vowels.contains(String.valueOf(c))) {
                        processedLine.append('а');
                        vowelCount++;
                    } else if (consonants.contains(String.valueOf(c))) {
                        processedLine.append('м');
                        consonantCount++;
                    } else {
                        processedLine.append(c);
                    }
                }

                writer.write(processedLine.toString());
                writer.newLine();
            }

            System.out.println("Статистика нового файла:");
            System.out.println("Количество гласных: " + vowelCount);
            System.out.println("Количество согласных: " + consonantCount);

        } catch (IOException e) {
            System.err.println("Ошибка при обработке файла: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String inputFile = "text.txt";  // Replace with your input file path
        String outputFile = "newtext"; // Replace with your output file path
        processFile(inputFile, outputFile);
    }
}













