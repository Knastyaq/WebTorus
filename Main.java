import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {



        Map<String, Integer> wordFrequency = new HashMap<>();
        int totalWords = 0;


        try (BufferedReader reader = new BufferedReader(new FileReader("text.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {

                String processedLine = line.toLowerCase()
                        .replaceAll("[^а-яёa-z\\s]", " ")
                        .trim();


                String[] words = processedLine.split("\\s+");


                for (String word : words) {

                    if (!word.isEmpty() && word.matches("[а-яёa-z]+")) {

                        wordFrequency.merge(word, 1, Integer::sum);
                        totalWords++;
                    }
                }
            }


            if (wordFrequency.isEmpty()) {
                System.out.println("В файле нет подходящих слов для анализа.");
                return;
            }


            System.out.println("\nСлова в алфавитном порядке:");
            System.out.println("==========================");
            List<String> sortedWords = new ArrayList<>(wordFrequency.keySet());
            Collections.sort(sortedWords);
            for (String word : sortedWords) {
                System.out.println(word);
            }


            System.out.println("\nСтатистика по словам:");
            System.out.println("====================");
            System.out.printf("%-20s | %-10s | %-10s%n", "Слово", "Количество", "Процент");
            System.out.println("-".repeat(45));

            for (String word : sortedWords) {
                int count = wordFrequency.get(word);
                double percentage = (count * 100.0) / totalWords;
                System.out.printf("%-20s | %-10d | %6.2f%%%n",
                        word, count, percentage);
            }


            int maxFrequency = Collections.max(wordFrequency.values());

            System.out.println("\nСлова с максимальной частотой:");
            System.out.println("============================");
            System.out.printf("Частота: %d раз(а)%n", maxFrequency);
            System.out.println("Слова:");

            for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
                if (entry.getValue() == maxFrequency) {
                    double percentage = (entry.getValue() * 100.0) / totalWords;
                    System.out.printf("  - %s (%d раз(а), %.2f%%)%n",
                            entry.getKey(), entry.getValue(), percentage);
                }
            }


            System.out.println("\nОбщая статистика:");
            System.out.println("================");
            System.out.printf("Всего слов: %d%n", totalWords);
            System.out.printf("Уникальных слов: %d%n", wordFrequency.size());

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: Файл не найден - " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}