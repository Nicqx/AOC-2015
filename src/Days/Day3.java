package Days;

import utility.FileReader;

import java.util.HashSet;
import java.util.Set;

public class Day3 {

    public Day3() {
        String text = new FileReader("resources/D3/input").fileReaderString();
        System.out.println("D3 - These amount of houses was visited by Santa at least once: " + processRoute(text));
        System.out.println("D3/2 - These amount of houses was visited with Robo-Santa at least once: " + processRouteWithRoboSanta(text));
    }

    static int processRoute(String text) {
        Set<String> coords = new HashSet<>();
        int x = 0;
        int y = 0;
        coords.add("0;0");
        for (int i = 0; i < text.length(); i++) {
            switch (text.charAt(i)) {
                case '^' -> {
                    x++;
                    coords.add(x + ";" + y);
                }
                case '>' -> {
                    y++;
                    coords.add(x + ";" + y);
                }
                case '<' -> {
                    y--;
                    coords.add(x + ";" + y);
                }
                case 'v' -> {
                    x--;
                    coords.add(x + ";" + y);
                }
            }
        }
        return coords.size();
    }

    static int processRouteWithRoboSanta(String text) {
        Set<String> coords = new HashSet<>();

        int Sx = 0;
        int Sy = 0;
        int Rx = 0;
        int Ry = 0;
        coords.add("0;0");
        for (int i = 0; i < text.length(); i++) {
            if (i % 2 == 0) {
                switch (text.charAt(i)) {
                    case '^' -> {
                        Sx++;
                        coords.add(Sx + ";" + Sy);
                    }
                    case '<' -> {
                        Sy--;
                        coords.add(Sx + ";" + Sy);
                    }
                    case '>' -> {
                        Sy++;
                        coords.add(Sx + ";" + Sy);
                    }
                    case 'v' -> {
                        Sx--;
                        coords.add(Sx + ";" + Sy);
                    }
                }
            } else {
                switch (text.charAt(i)){
                    case  '^'->{
                        Rx++;
                        coords.add(Rx + ";" + Ry);
                    }
                    case '>'->{
                        Ry++;
                        coords.add(Rx + ";" + Ry);
                    }
                    case '<' ->{
                        Ry--;
                        coords.add(Rx + ";" + Ry);
                    }
                    case 'v'->{
                        Rx--;
                        coords.add(Rx + ";" + Ry);
                    }
                }
            }
        }
        return coords.size();
    }
}
