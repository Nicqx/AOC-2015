package Days;

import utility.FileReader;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {
    ArrayList<String> fileContent = new FileReader("resources/D14/input").fileReaderArrayList();
    ArrayList<Reindeer> fleet = new ArrayList<>();
    final int RACETIME = 2503;


    public Day14() {
        processFileContent(fileContent, fleet);

        System.out.println("D14 - The longest distance has the winning reindeer traveled is: " + maxDistance(RACETIME, fleet));

        System.out.println("D14/2 - The highest points the winning reindeer has: " + maxPoints(RACETIME, fleet));

    }

    static void processFileContent(ArrayList<String> fileContent, ArrayList<Reindeer> fleet) {
        for (String line : fileContent) {
            Matcher matcher = Pattern.compile("(\\w+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds.").matcher(line);
            if (matcher.find()) {
                fleet.add(new Reindeer(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4))));
            }
        }
    }

    static int maxPoints(int raceTime, ArrayList<Reindeer> fleet) {
        int result = 0;
        competeAgain(raceTime, fleet);
        for (Reindeer racer : fleet) {
            if (racer.getScore() > result) {
                result = racer.getScore();
            }
        }
        return result;
    }

    static int maxDistance(int raceTime, ArrayList<Reindeer> fleet) {
        int result = 0;
        for (Reindeer racer : fleet) {
            if (compete(racer, raceTime) > result) {
                result = compete(racer, raceTime);
            }
        }
        return result;
    }

    static void competeAgain(int time, ArrayList<Reindeer> fleet) {
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

    static int compete(Reindeer racer, int time) {
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
