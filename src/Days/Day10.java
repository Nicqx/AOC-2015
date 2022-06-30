package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day10 {
    private final Map<Character, Integer> store = new HashMap<>();
    int iterations = 40;
    int nextIterations = 50;

    public Day10() {
        try (Scanner scanner = new Scanner(new File("resources/D10/input"))) {
            while (scanner.hasNext()) {
                String process = process(scanner.nextLine(), iterations);
                System.out.println("D10 - the length of the string after " + iterations + " iteration(s) is: " + process.length());
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        try (Scanner scanner = new Scanner(new File("resources/D10/input"))) {
            while (scanner.hasNext()) {
                String process = process(scanner.nextLine(), nextIterations);
                System.out.println("D10/2 - the length of the string after " + nextIterations + " iteration(s) is: " + process.length());
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }


    private String process(String text, int iterations) {
        String mainResult = text;
        while (iterations > 0) {
            store.clear();
            StringBuilder result = new StringBuilder();
            CharacterIterator it = new StringCharacterIterator(mainResult);
            Character tmp = null;
            while ((it.current() != CharacterIterator.DONE)) {
                if (store.isEmpty()) {
                    store.put(it.current(), 1);
                } else if (it.current() == tmp) {
                    store.put(it.current(), store.get(it.current()) + 1);
                } else {
                    for (Character key : store.keySet()) {
                        result.append(store.get(key)).append(key);
                    }
                    store.clear();
                    store.put(it.current(), 1);
                }
                tmp = it.current();
                it.next();
            }
            for (Character key : store.keySet()) {
                result.append(store.get(key)).append(key);
            }
            mainResult = result.toString();
            iterations--;
        }
        return mainResult;
    }
}