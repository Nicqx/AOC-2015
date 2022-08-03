package Days;

import utility.FileReader;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day23 {
    ArrayList<String> fileContent = new FileReader("resources/D23/input").fileReaderArrayList();

    public Day23() {
        Registers registers = calculateB(0, fileContent);
        System.out.println("D23 - the value in register b: " + registers.getB());
        registers = calculateB(1, fileContent);
        System.out.println("D23/2 - the value in register b: " + registers.getB());
    }

    static Registers calculateB(int a, ArrayList<String> fileContent) {
        Registers registers = new Registers(a, 0);
        boolean found;
        do {
            found = process(registers, fileContent);
        } while (found);
        return registers;
    }

    static boolean process(Registers registers, ArrayList<String> fileContent) {
        if (fileContent.size() < registers.getNextCoord() + 1 || registers.getNextCoord() < 0) {
            return false;
        }
        if (fileContent.get(registers.getNextCoord()).equals("inc a")) {
            registers.setA(registers.getA() + 1);
            registers.setNextCoord(registers.getNextCoord() + 1);
            return true;
        }
        if (fileContent.get(registers.getNextCoord()).equals("inc b")) {
            registers.setB(registers.getB() + 1);
            registers.setNextCoord(registers.getNextCoord() + 1);
            return true;
        }
        if (fileContent.get(registers.getNextCoord()).equals("hlf a")) {
            registers.setA(registers.getA() / 2);
            registers.setNextCoord(registers.getNextCoord() + 1);
            return true;
        }
        if (fileContent.get(registers.getNextCoord()).equals("tpl a")) {
            registers.setA(registers.getA() * 3);
            registers.setNextCoord(registers.getNextCoord() + 1);
            return true;
        }
        if (fileContent.get(registers.getNextCoord()).startsWith("jmp")) {
            String num = fileContent.get(registers.getNextCoord()).split(" ")[1];
            if (num.startsWith("+")) {
                registers.setNextCoord(registers.getNextCoord() + Integer.parseInt(num.split("\\+")[1]));
            } else {
                registers.setNextCoord(registers.getNextCoord() - Integer.parseInt(num.split("-")[1]));
            }
            return true;
        }
        Matcher matcher = Pattern.compile("jie a, \\+(\\d+)").matcher(fileContent.get(registers.getNextCoord()));
        if (matcher.find()) {
            if (registers.getA() % 2 == 0) {
                registers.setNextCoord(registers.getNextCoord() + Integer.parseInt(matcher.group(1)));
            } else {
                registers.setNextCoord(registers.getNextCoord() + 1);
            }
            return true;
        }
        matcher = Pattern.compile("jio a, \\+(\\d+)").matcher(fileContent.get(registers.getNextCoord()));
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
