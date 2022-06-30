package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {
    public Day14() {
        final int RACETIME = 2503;
        ArrayList<Reindeer> fleet = new ArrayList<>();
        int maxDistance = 0;
        int maxPoints = 0;

        try (Scanner scanner = new Scanner(new File("resources/D14/input"))) {
            while (scanner.hasNext()) {
                Matcher matcher = Pattern.compile("(\\w+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds.").matcher(scanner.nextLine());
                if (matcher.find()) {
                    fleet.add(new Reindeer(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))));
                }
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        for (Reindeer racer : fleet) {
            if (compete(racer, RACETIME) > maxDistance) {
                maxDistance = compete(racer, RACETIME);
            }
        }
        System.out.println("D14 - The longest distance has the winning reindeer traveled is: " + maxDistance);
        competeAgain(RACETIME, fleet);
        for (Reindeer racer : fleet) {
            if (racer.getScore() > maxPoints) {
                maxPoints = racer.getScore();
            }
        }
        System.out.println("D14/2 - The highest points the winning reindeer has: " + maxPoints);

    }

    private void competeAgain(int time, ArrayList<Reindeer> fleet) {
        int actualTime = time;
        while (actualTime > 0) {
            for (Reindeer racer : fleet) {
                racer.action();
            }
            int maxPos = 0;
            for (Reindeer racer : fleet) {
                if (maxPos < racer.getPos()) {
                    maxPos = racer.getPos();
                }
            }
            for (Reindeer racer : fleet) {
                if (racer.getPos() == maxPos) {
                    racer.increaseScore();
                }
            }
            actualTime--;
        }

    }

    private int compete(Reindeer racer, int time) {
        int result = 0;
        while (time > 0) {
            if (time - racer.getTime() > 0) {
                result += racer.getTime() * racer.getSpeed();
                time -= racer.getTime();
            } else {
                result += time * racer.getSpeed();
                time = 0;
            }
            if (time - racer.getRest() > 0) {
                time -= racer.getRest();
            } else {
                time = 0;
            }
        }
        return result;
    }

    static class Reindeer {
        enum States {RUNNING, RESTING}

        private States actualState;
        private final int speed;
        private final int time;
        private final int rest;

        private int remainingTime;
        private int remainingRest;
        private int score = 0;
        private int pos = 0;

        public Reindeer(int speed, int time, int rest) {
            this.speed = speed;
            this.time = time;
            this.rest = rest;
            this.actualState = States.RUNNING;
            this.remainingRest = rest;
            this.remainingTime = time;

        }

        public void action() {
            if (actualState.equals(States.RUNNING)) {
                remainingTime--;
                pos += speed;
                if (remainingTime == 0) {
                    actualState = States.RESTING;
                    remainingRest = rest;
                }
            } else {
                remainingRest--;
                if (remainingRest == 0) {
                    actualState = States.RUNNING;
                    remainingTime = time;
                }
            }

        }

        public int getSpeed() {
            return this.speed;
        }

        public int getTime() {
            return this.time;
        }

        public int getRest() {
            return this.rest;
        }

        public int getScore() {
            return score;
        }

        public void increaseScore() {
            this.score++;
        }

        public int getPos() {
            return this.pos;
        }
    }
}
