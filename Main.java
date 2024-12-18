//import java.io.*;
//import java.util.*;
//
//public class Main {
//    public static void main(String[] args) {
//        try {
//
//            BufferedReader reader = new BufferedReader(new FileReader("text.txt"));
//            Map<String, Integer> wordCount = new TreeMap<>();
//            String line;
//
//
//            while ((line = reader.readLine()) != null) {
//
//                String[] words = line.toLowerCase()
//                        .replaceAll("[^a-zA-Z0-9\\s]", " ")
//                        .split("\\s+");
//
//                for (String word : words) {
//                    if (!word.isEmpty()) {
//                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
//                    }
//                }
//            }
//            reader.close();
//
//
//            System.out.println("Все слова в алфавитном порядке:");
//            for (String word : wordCount.keySet()) {
//                System.out.println(word);
//            }
//
//            // Display word frequencies
//            System.out.println("\nЧастота встречаемости слов:");
//            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
//                System.out.println(entry.getKey() + ": " + entry.getValue());
//            }
//
//            // Find maximum frequency
//            int maxFrequency = 0;
//            for (int frequency : wordCount.values()) {
//                if (frequency > maxFrequency) {
//                    maxFrequency = frequency;
//                }
//            }
//
//            // Display words with maximum frequency
//            System.out.println("\nСлова с максимальной частотой (" + maxFrequency + " раз):");
//            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
//                if (entry.getValue() == maxFrequency) {
//                    System.out.println(entry.getKey());
//                }
//            }
//
//        } catch (IOException e) {
//            System.out.println("Ошибка при чтении файла: " + e.getMessage());
//        }
//    }
//}
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        // Создаем Map для хранения слов и их частот
        Map<String, Integer> wordFrequency = new HashMap<>();
        int totalWords = 0;

        // Читаем файл с помощью BufferedReader
        try (BufferedReader reader = new BufferedReader(new FileReader("text.txt"))) {
            String line;
            // Читаем файл построчно
            while ((line = reader.readLine()) != null) {
                // Преобразуем строку:
                // 1. Приводим к нижнему регистру
                // 2. Заменяем все не-буквенные символы на пробелы
                // 3. Убираем лишние пробелы в начале и конце
                String processedLine = line.toLowerCase()
                        .replaceAll("[^а-яёa-z\\s]", " ")
                        .trim();

                // Разбиваем строку на слова
                String[] words = processedLine.split("\\s+");

                // Обрабатываем каждое слово
                for (String word : words) {
                    // Пропускаем пустые строки и слова с цифрами
                    if (!word.isEmpty() && word.matches("[а-яёa-z]+")) {
                        // Увеличиваем счетчик для слова
                        wordFrequency.merge(word, 1, Integer::sum);
                        totalWords++;
                    }
                }
            }

            // Проверяем, есть ли слова в файле
            if (wordFrequency.isEmpty()) {
                System.out.println("В файле нет подходящих слов для анализа.");
                return;
            }

            // 1. Вывод слов в алфавитном порядке
            System.out.println("\nСлова в алфавитном порядке:");
            System.out.println("==========================");
            List<String> sortedWords = new ArrayList<>(wordFrequency.keySet());
            Collections.sort(sortedWords);
            for (String word : sortedWords) {
                System.out.println(word);
            }

            // 2. Вывод статистики
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

            // 3. Поиск и вывод слов с максимальной частотой
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

            // 4. Общая статистика
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