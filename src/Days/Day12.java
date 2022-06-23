package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12 {
    public Day12(){
        try (Scanner scanner = new Scanner(new File("resources/D12/input"))) {
            int sum=0;
            while (scanner.hasNext()) {
                sum = sumNum(scanner.nextLine());
            }
            System.out.println("D12 - the sum of all numbers: " + sum);

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private int sumNum(String text){
        int sum=0;
        Matcher matcher = Pattern.compile("(-?\\d+)").matcher(text);
        while (matcher.find()) {
            sum+=Integer.parseInt(matcher.group());
        }
        return sum;
    }
}
