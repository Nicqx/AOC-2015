package Days;

import utility.FileReader;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day15 {
    ArrayList<String> fileContent = new FileReader("resources/D15/input").fileReaderArrayList();
    private static final int INGREDIENTS = 100;
    ArrayList<Ingredients> ingredientsList = new ArrayList<>();
    final ArrayList<int[]> combinations = combinations();


    public Day15() {
        processFileContent();

        System.out.println("D15 - The highest-scoring cookie's score is: " + calculateHighestScoring());

        System.out.println("D15/2 - The highest-scoring cookie with calorie limit score is: " + calculateHighestScoringWithCalorieLimits());
    }

    private int calculateHighestScoringWithCalorieLimits() {
        int result = 0;
        for (int[] combination : combinations) {
            if (calculateScoreWithCalorieLimit(ingredientsList, combination) > result) {
                result = calculateScoreWithCalorieLimit(ingredientsList, combination);
            }
        }
        return result;
    }

    private int calculateHighestScoring() {
        int result = 0;
        for (int[] combination : combinations) {
            if (calculateScore(ingredientsList, combination) > result) {
                result = calculateScore(ingredientsList, combination);
            }
        }
        return result;
    }

    private void processFileContent() {
        for (String line : fileContent) {
            Matcher matcher = Pattern.compile("(\\w+): capacity (-?\\d+), durability (-?\\d+), flavor (-?\\d+), texture (-?\\d+), calories (-?\\d+)").matcher(line);
            if (matcher.find()) {
                ingredientsList.add(new Ingredients(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6))));
            }
        }
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

    private static ArrayList<int[]> combinations() {
        ArrayList<int[]> result = new ArrayList<>();
        for (int i = 0; i <= INGREDIENTS; i++) {
            for (int j = 0; j <= INGREDIENTS; j++) {
                for (int k = 0; k <= INGREDIENTS; k++) {
                    for (int l = 0; l <= INGREDIENTS; l++) {
                        if (i + j + k + l == INGREDIENTS) {
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
