package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day21 {
    Map<Integer, Weapon> weaponShop = new HashMap<>();
    Map<Integer, Armor> armorShop = new HashMap<>();
    Map<Integer, Ring> ringShop = new HashMap<>();

    public Day21() {
        int bossHit = 0;
        int bossDamage = 0;
        int bossArmor = 0;
        try (Scanner scanner = new Scanner(new File("resources/D19/input"))) {
            while (scanner.hasNext()) {
                Matcher matcher = Pattern.compile("Hit Points: (\\d+)").matcher(scanner.nextLine());
                if (matcher.find()) {
                    bossHit = Integer.parseInt(matcher.group(1));
                }
                matcher = Pattern.compile("Damage: (\\d+)").matcher(scanner.nextLine());
                if (matcher.find()) {
                    bossDamage = Integer.parseInt(matcher.group(1));
                }
                matcher = Pattern.compile("Armor: (\\d+)").matcher(scanner.nextLine());
                if (matcher.find()) {
                    bossArmor = Integer.parseInt(matcher.group(1));
                }
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        initShop();
//        Character boss = new Character(bossHit, bossDamage, bossArmor);
        Character boss = new Character(12, 7, 2);
        Character player = new Character(8, 5, 5);
        System.out.println(isPlayerWin(player,boss));
    }

    private boolean isPlayerWin(Character player, Character boss){
        do{
            boss.setHit(boss.getHit()- (player.getDamage()- boss.getArmor()));
            if(boss.getHit()<=0){
                break;
            }
            player.setHit(player.getHit() - (boss.getDamage()- player.getArmor()));
            if(player.getHit()<=0){
                break;
            }

        }while(player.getHit()>0 && boss.getHit()>0);

        return player.getHit()>0;

    }

    static class Character {
        private int hit;
        private final int damage;
        private final int armor;

        public Character(int hit, int damage, int armor) {
            this.hit = hit;
            this.damage = damage;
            this.armor = armor;
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

    private void initShop() {
        weaponShop.put(0, new Weapon("Dagger", 8, 4, 0));
        weaponShop.put(1, new Weapon("Shortsword", 10, 5, 0));
        weaponShop.put(2, new Weapon("Warhammer", 25, 6, 0));
        weaponShop.put(3, new Weapon("Longsword", 40, 7, 0));
        weaponShop.put(4, new Weapon("Greataxe", 47, 8, 0));
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
        public Ring(String name, int cost, int damage, int armor) {
        }
    }

    static class Armor {
        public Armor(String name, int cost, int damage, int armor) {
        }

    }

    static class Weapon {
        public Weapon(String name, int cost, int damage, int armor) {
        }

    }
}
