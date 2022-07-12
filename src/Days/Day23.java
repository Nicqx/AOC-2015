package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day23 {
    Registers registers;

    ArrayList<String> commandList = new ArrayList<>();

    public Day23() {
        fileReader("resources/D23/input");
        calculateB(0);
        System.out.println("D23 - the value in register b: " + registers.getB());
        calculateB(1);
        System.out.println("D23/2 - the value in register b: " + registers.getB());
    }

    private void calculateB(int a) {
        registers = new Registers(a, 0);
        boolean found;
        do {
            found = process(registers);
        } while (found);
    }

    private void fileReader(String res) {
        try (Scanner scanner = new Scanner(new File(res))) {
            while (scanner.hasNext()) {
                commandList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    private boolean process(Registers registers) {
        if (commandList.size() < registers.getNextCoord() + 1 || registers.getNextCoord() < 0) {
            return false;
        }
        if (commandList.get(registers.getNextCoord()).equals("inc a")) {
            registers.setA(registers.getA() + 1);
            registers.setNextCoord(registers.getNextCoord() + 1);
            return true;
        }
        if (commandList.get(registers.getNextCoord()).equals("inc b")) {
            registers.setB(registers.getB() + 1);
            registers.setNextCoord(registers.getNextCoord() + 1);
            return true;
        }
        if (commandList.get(registers.getNextCoord()).equals("hlf a")) {
            registers.setA(registers.getA() / 2);
            registers.setNextCoord(registers.getNextCoord() + 1);
            return true;
        }
        if (commandList.get(registers.getNextCoord()).equals("tpl a")) {
            registers.setA(registers.getA() * 3);
            registers.setNextCoord(registers.getNextCoord() + 1);
            return true;
        }
        if (commandList.get(registers.getNextCoord()).startsWith("jmp")) {
            String num = commandList.get(registers.getNextCoord()).split(" ")[1];
            if (num.startsWith("+")) {
                registers.setNextCoord(registers.getNextCoord() + Integer.parseInt(num.split("\\+")[1]));
            } else {
                registers.setNextCoord(registers.getNextCoord() - Integer.parseInt(num.split("-")[1]));
            }
            return true;
        }
        Matcher matcher = Pattern.compile("jie a, \\+(\\d+)").matcher(commandList.get(registers.getNextCoord()));
        if (matcher.find()) {
            if (registers.getA() % 2 == 0) {
                registers.setNextCoord(registers.getNextCoord() + Integer.parseInt(matcher.group(1)));
            } else {
                registers.setNextCoord(registers.getNextCoord() + 1);
            }
            return true;
        }
        matcher = Pattern.compile("jio a, \\+(\\d+)").matcher(commandList.get(registers.getNextCoord()));
        if (matcher.find()) {
            if (registers.getA() == 1) {
                registers.setNextCoord(registers.getNextCoord() + Integer.parseInt(matcher.group(1)));
            } else {
                registers.setNextCoord(registers.getNextCoord() + 1);
            }
            return true;
        }
        return true;
    }

    static class Registers {
        int a;
        int b;

        int nextCoord = 0;

        public Registers(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public int getNextCoord() {
            return nextCoord;
        }

        public void setNextCoord(int nextCoord) {
            this.nextCoord = nextCoord;
        }
    }
}
