import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public  class Main {
    public static void main (String[] args) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("text.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("newtext"));

            String line;
            int vowels = 0;
            int consonants = 0;

            while ((line = reader.readLine()) != null) {
                for (char letter : line.toCharArray()) {
                    char lowerCaseLetter = Character.toLowerCase(letter);
                    if (Character.isLetter(lowerCaseLetter)) {
                        if (lowerCaseLetter == 'а' || lowerCaseLetter == 'е' || lowerCaseLetter == 'ё' || lowerCaseLetter == 'и' || lowerCaseLetter == 'о' || lowerCaseLetter == 'у' || lowerCaseLetter == 'ы' || lowerCaseLetter == 'э' || lowerCaseLetter == 'ю' || lowerCaseLetter == 'я') {
                            writer.write('а');
                            vowels++;
                        } else {
                            writer.write('м');
                            consonants++;
                        }
                    } else {
                        writer.write(letter);
                    }
                }
                writer.newLine();
            }

            reader.close();
            writer.close();

            System.out.println("Гласных букв: " + vowels);
            System.out.println("Согласных букв: " + consonants);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    }








