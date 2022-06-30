package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 {
    private final int MAXINGREDIENTS = 100;
    ArrayList<Ingredients> ingredientsList = new ArrayList<>();

    public Day15() {
        int highestScoring = 0;
        try (Scanner scanner = new Scanner(new File("resources/D15/input"))) {
            while (scanner.hasNext()) {
                Matcher matcher = Pattern.compile("(\\w+): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)").matcher(scanner.nextLine());
                if (matcher.find()) {
                    ingredientsList.add(new Ingredients(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6))));
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        final ArrayList<int[]> combinations = combinations(MAXINGREDIENTS);
        for (int[] combination : combinations) {
            if (calculateScore(ingredientsList, combination) > highestScoring) {
                highestScoring = calculateScore(ingredientsList, combination);
            }
        }
        System.out.println("D15 - The highest-scoring cookie's score is: " + highestScoring);
        highestScoring = 0;
        for (int[] combination : combinations) {
            if (calculateScoreWithCalorieLimit(ingredientsList, combination) > highestScoring) {
                highestScoring = calculateScoreWithCalorieLimit(ingredientsList, combination);
            }
        }
        System.out.println("D15/2 - The highest-scoring cookie with calorie limit score is: " + highestScoring);
    }

    private int calculateScore(ArrayList<Ingredients> ingredients, int[] combination) {
        int capacity = 0;
        int durability = 0;
        int flavour = 0;
        int texture = 0;
        for (int i = 0; i < ingredients.size(); i++) {
            capacity += ingredients.get(i).capacity() * combination[i];
            durability += ingredients.get(i).durability() * combination[i];
            flavour += ingredients.get(i).flavor() * combination[i];
            texture += ingredients.get(i).texture() * combination[i];
        }

        return positiveResult(capacity) * positiveResult(durability) * positiveResult(flavour) * positiveResult(texture);

    }

    private int calculateScoreWithCalorieLimit(ArrayList<Ingredients> ingredients, int[] combination) {
        int capacity = 0;
        int durability = 0;
        int flavour = 0;
        int texture = 0;
        int calorie = 0;
        for (int i = 0; i < ingredients.size(); i++) {
            capacity += ingredients.get(i).capacity() * combination[i];
            durability += ingredients.get(i).durability() * combination[i];
            flavour += ingredients.get(i).flavor() * combination[i];
            texture += ingredients.get(i).texture() * combination[i];
            calorie += ingredients.get(i).calories() * combination[i];
        }
        if (positiveResult(calorie) == 500) {
            return positiveResult(capacity) * positiveResult(durability) * positiveResult(flavour) * positiveResult(texture);
        } else {
            return 0;
        }
    }

    private int positiveResult(int value) {
        return Math.max(value, 0);
    }

    private static ArrayList<int[]> combinations(int maxIngredients) {
        ArrayList<int[]> result = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                for (int k = 0; k <= 100; k++) {
                    for (int l = 0; l <= 100; l++) {
                        if (i + j + k + l == 100) {
                            int[] tmp = {i, j, k, l};
                            result.add(tmp);
                        }
                    }
                }
            }
        }
        return result;
    }

    private record Ingredients(int capacity, int durability, int flavor, int texture, int calories) {
    }

}
