package Days;

import utility.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day21 {
    ArrayList<String> fileContent = new FileReader("resources/D21/input").fileReaderArrayList();

    static Map<Integer, Weapon> weaponShop = new HashMap<>();
    static Map<Integer, Armor> armorShop = new HashMap<>();
    static Map<Integer, Ring> ringShop = new HashMap<>();

    public Day21() {

        ProcessedFileContent prfc = processFileContent(fileContent);
        initShop();
        System.out.println("D21 - The minimal cost when player is win: " + genFightMinimalCost(prfc.bossHit, prfc.bossDamage, prfc.bossArmor));
        System.out.println("D21/2 - The maximal cost when player is lose: " + genFightMaximalLosingCost(prfc.bossHit, prfc.bossDamage, prfc.bossArmor));
    }

    static ProcessedFileContent processFileContent(ArrayList<String> fileContent) {
        int bossHit = 0;
        int bossDamage = 0;
        int bossArmor = 0;
        for (String line : fileContent) {
            Matcher matcher = Pattern.compile("Hit Points: (\\d+)").matcher(line);
            if (matcher.find()) {
                bossHit = Integer.parseInt(matcher.group(1));
            }
            matcher = Pattern.compile("Damage: (\\d+)").matcher(line);
            if (matcher.find()) {
                bossDamage = Integer.parseInt(matcher.group(1));
            }
            matcher = Pattern.compile("Armor: (\\d+)").matcher(line);
            if (matcher.find()) {
                bossArmor = Integer.parseInt(matcher.group(1));
            }
        }
        return new ProcessedFileContent(bossHit, bossDamage, bossArmor);
    }

    static class ProcessedFileContent {
        int bossHit;
        int bossDamage;
        int bossArmor;

        public ProcessedFileContent(int bossHit, int bossDamage, int bossArmor) {
            this.bossHit = bossHit;
            this.bossDamage = bossDamage;
            this.bossArmor = bossArmor;
        }
    }

    static int genFightMinimalCost(int bossHit, int bossDamage, int bossArmor) {
        int minimalCost = Integer.MAX_VALUE;
        for (int weapon = 0; weapon <= 4; weapon++) {
            for (int armor = -1; armor <= 4; armor++) {
                for (int ring1 = -1; ring1 <= 2; ring1++) {
                    for (int ring2 = 3; ring2 <= 6; ring2++) {
                        Character boss = new Character.CharacterBuilder(bossHit, bossDamage, bossArmor).build();
                        Character player = new Character.CharacterBuilder(100, 0, 0).withWeapon(weapon).withArmor(armor).withRing1(ring1).withRing2(ring2).build();
                        if (isPlayerWin(player, boss) && minimalCost > player.getCost()) {
                            minimalCost = player.getCost();
                        }
                    }
                }
            }
        }
        return minimalCost;
    }

    static int genFightMaximalLosingCost(int bossHitBase, int bossDamageBase, int bossArmorBase) {
        int maximalCost = Integer.MIN_VALUE;
        for (int playerWeapon = 0; playerWeapon <= 4; playerWeapon++) {
            for (int playerArmor = -1; playerArmor <= 4; playerArmor++) {
                for (int playerRing1 = -1; playerRing1 <= 2; playerRing1++) {
                    for (int playerRing2 = 3; playerRing2 <= 6; playerRing2++) {
                        Character boss = new Character.CharacterBuilder(bossHitBase, bossDamageBase, bossArmorBase).build();
                        Character player = new Character.CharacterBuilder(100, 0, 0).withWeapon(playerWeapon).withArmor(playerArmor).withRing1(playerRing1).withRing2(playerRing2).build();
                        if (!isPlayerWin(player, boss) && maximalCost < player.getCost()) {
                            maximalCost = player.getCost();
                        }

                    }
                }
            }
        }
        return maximalCost;
    }


    static boolean isPlayerWin(Character player, Character boss) {
        do {
            boss.setHit(boss.getHit() - Math.max(player.getDamage() - boss.getArmor(), 1));
            if (boss.getHit() <= 0) {
                break;
            }
            player.setHit(player.getHit() - Math.max(boss.getDamage() - player.getArmor(), 1));
            if (player.getHit() <= 0) {
                break;
            }

        } while (player.getHit() > 0 && boss.getHit() > 0);

        return player.getHit() > 0;

    }

    static class Character {

        private final int cost;
        private int hit;
        private final int damage;
        private final int armor;

        private Character(CharacterBuilder character) {
            this.hit = character.hit;
            this.damage = character.damage;
            this.armor = character.armor;
            this.cost = character.cost;
        }

        public static class CharacterBuilder {
            private int cost = 0;
            private final int hit;
            private int damage;
            private int armor;

            public CharacterBuilder(int hit, int damage, int armor) {
                this.hit = hit;
                this.damage = damage;
                this.armor = armor;
            }

            public CharacterBuilder withWeapon(int weapon) {
                this.damage += weaponShop.get(weapon).getDamage();
                this.armor += weaponShop.get(weapon).getArmor();
                this.cost += weaponShop.get(weapon).getCost();
                return this;
            }

            public CharacterBuilder withArmor(int armor) {
                if (armor != -1) {
                    this.damage += armorShop.get(armor).getDamage();
                    this.armor += armorShop.get(armor).getArmor();
                    this.cost += armorShop.get(armor).getCost();
                }
                return this;
            }

            public CharacterBuilder withRing1(int ring) {
                if (ring != -1) {
                    this.damage += ringShop.get(ring).getDamage();
                    this.armor += ringShop.get(ring).getArmor();
                    this.cost += ringShop.get(ring).getCost();
                }
                return this;
            }

            public CharacterBuilder withRing2(int ring) {
                if (ring != 6) {
                    this.damage += ringShop.get(ring).getDamage();
                    this.armor += ringShop.get(ring).getArmor();
                    this.cost += ringShop.get(ring).getCost();
                }
                return this;
            }

            public Character build() {
                return new Character(this);
            }
        }

        public int getCost() {
            return cost;
        }

        public int getHit() {
            return hit;
        }

        public int getDamage() {
            return damage;
        }

        public int getArmor() {
            return armor;
        }

        public void setHit(int hit) {
            this.hit = hit;
        }
    }

    static void initShop() {
        weaponShop.put(0, new Weapon("Dagger", 8, 4, 0));
        weaponShop.put(1, new Weapon("Shortsword", 10, 5, 0));
        weaponShop.put(2, new Weapon("Warhammer", 25, 6, 0));
        weaponShop.put(3, new Weapon("Longsword", 40, 7, 0));
        weaponShop.put(4, new Weapon("Greataxe", 74, 8, 0));
        armorShop.put(0, new Armor("Leather", 13, 0, 1));
        armorShop.put(1, new Armor("Chainmail", 31, 0, 2));
        armorShop.put(2, new Armor("Splintmail", 53, 0, 3));
        armorShop.put(3, new Armor("Bandedmail", 75, 0, 4));
        armorShop.put(4, new Armor("Platemail", 102, 0, 5));
        ringShop.put(0, new Ring("Damage +1", 25, 1, 0));
        ringShop.put(1, new Ring("Damage +2", 50, 2, 0));
        ringShop.put(2, new Ring("Damage +3", 100, 3, 0));
        ringShop.put(3, new Ring("Defense +1", 20, 0, 1));
        ringShop.put(4, new Ring("Defense +2", 40, 0, 2));
        ringShop.put(5, new Ring("Defense +3", 80, 0, 3));
    }

    static class Ring {
        String name;
        int cost;
        int damage;
        int armor;

        public Ring(String name, int cost, int damage, int armor) {
            this.name = name;
            this.cost = cost;
            this.damage = damage;
            this.armor = armor;
        }

        public int getCost() {
            return cost;
        }

        public int getDamage() {
            return damage;
        }

        public int getArmor() {
            return armor;
        }
    }

    static class Armor {
        String name;
        int cost;
        int damage;
        int armor;

        public Armor(String name, int cost, int damage, int armor) {
            this.name = name;
            this.cost = cost;
            this.damage = damage;
            this.armor = armor;
        }

        public int getCost() {
            return cost;
        }

        public int getDamage() {
            return damage;
        }

        public int getArmor() {
            return armor;
        }
    }

    static class Weapon {
        String name;
        int cost;
        int damage;
        int armor;

        public Weapon(String name, int cost, int damage, int armor) {
            this.name = name;
            this.cost = cost;
            this.damage = damage;
            this.armor = armor;
        }

        public int getCost() {
            return cost;
        }

        public int getDamage() {
            return damage;
        }

        public int getArmor() {
            return armor;
        }
    }
}
