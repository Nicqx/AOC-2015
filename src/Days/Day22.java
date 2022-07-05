package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day22 {
    public Day22() {
        int bossHit = 0;
        int bossDamage = 0;
        try (Scanner scanner = new Scanner(new File("resources/D22/input"))) {
            while (scanner.hasNext()) {
                Matcher matcher = Pattern.compile("Hit Points: (\\d+)").matcher(scanner.nextLine());
                if (matcher.find()) {
                    bossHit = Integer.parseInt(matcher.group(1));
                }
                matcher = Pattern.compile("Damage: (\\d+)").matcher(scanner.nextLine());
                if (matcher.find()) {
                    bossDamage = Integer.parseInt(matcher.group(1));
                }
            }

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }


        int[] functionNumbers = {0, 1, 2, 3, 4};
        List<List<Integer>> functionPermutations = permute(functionNumbers);
        int leastMana = Integer.MAX_VALUE;
        for (List<Integer> combination : functionPermutations) {
            Spells spells = new Spells(new Boss(bossHit, bossDamage), new Player());
            Sequence s = new Sequence(spells);
            List<Runnable> methods = new ArrayList<>();
            methods.add(s::drain);
            methods.add(s::shield);
            methods.add(s::recharge);
            methods.add(s::poison);
            methods.add(s::missile);

            do {
                methods.get(combination.get(0)).run();
                methods.get(combination.get(1)).run();
                methods.get(combination.get(2)).run();
                methods.get(combination.get(3)).run();
                methods.get(combination.get(4)).run();

            } while (s.spells.getPlayer().getHit() > 0 && s.spells.getBoss().getHit() > 0 && s.spells.getPlayer().getMana() > 53);
            if (s.spells.isPlayerWins() && s.spells.spentMana < leastMana) {
//                System.out.println("player hit: " + s.spells.getPlayer().getHit() + " mana: " + s.spells.getPlayer().getMana());
//                System.out.println("boss hit: " + s.spells.getBoss().getHit());
//                System.out.println("mana: " + s.spells.spentMana);
                leastMana = s.spells.spentMana;
            }
        }
        System.out.println("D22 - The minimal used mana to player wins: " + leastMana);
    }

    static class Sequence {
        Spells spells;

        public Sequence(Spells spells) {
            this.spells = spells;
        }

        public void drain() {
            if (!spells.shouldBeContinue()) {
                return;
            }
            if (spells.getPlayer().getMana() > 73 && spells.drain == 0) {
                spells.castDrain();
                spells.castSpells();
                if (!spells.shouldBeContinue()) {
                    return;
                }
                spells.castBossAttack();
                spells.castSpells();
            }
        }

        public void shield() {
            if (!spells.shouldBeContinue()) {
                return;
            }
            if (spells.getPlayer().getMana() > 113 && spells.shield == 0) {
                spells.castShield();
                spells.castSpells();
                if (!spells.shouldBeContinue()) {
                    return;
                }
                spells.castBossAttack();
                spells.castSpells();
            }
        }

        public void recharge() {
            if (!spells.shouldBeContinue()) {
                return;
            }
            if (spells.getPlayer().getMana() > 229 && spells.recharge == 0) {
                spells.castRecharge();
                spells.castSpells();
                if (!spells.shouldBeContinue()) {
                    return;
                }
                spells.castBossAttack();
                spells.castSpells();
            }
        }

        public void poison() {
            if (!spells.shouldBeContinue()) {
                return;
            }
            if (spells.getPlayer().getMana() > 173 && spells.poison == 0) {
                spells.castPoison();
                spells.castSpells();
                if (!spells.shouldBeContinue()) {
                    return;
                }
                spells.castBossAttack();
                spells.castSpells();

            }
        }

        public void missile() {
            if (!spells.shouldBeContinue()) {
                return;
            }
            if (spells.getPlayer().getMana() > 53) {
                spells.shouldBeContinue();
                spells.castMagicMissile();
                spells.castSpells();
                if (!spells.shouldBeContinue()) {
                    return;
                }
                spells.castBossAttack();
                spells.castSpells();
            }
        }
    }

    static class Spells {
        Boss boss;
        Player player;
        int spentMana = 0;
        int magicMissile = 0;
        int drain = 0;
        int bossAttack = 0;
        int poison = 0;
        int recharge = 0;
        int shield = 0;

        public Spells(Boss boss, Player player) {
            this.boss = boss;
            this.player = player;
        }

        public Boss getBoss() {
            return boss;
        }

        public Player getPlayer() {
            return player;
        }

        public boolean shouldBeContinue() {
            return getPlayer().getHit() > 0 && getBoss().getHit() > 0;

        }

        public boolean isPlayerWins() {
            return player.getHit() > 0 && boss.getHit() <= 0;
        }

        private void castSpells() {
            if (magicMissile > 0) {
                magicMissile();
                magicMissile--;
            }
            if (drain > 0) {
                drain();
                drain--;
            }
            if (bossAttack > 0) {
                bossAttack();
                bossAttack--;
            }
            if (poison > 0) {
                poison();
                poison--;
            }
            if (recharge > 0) {
                recharge();
                recharge--;
            }
            if (shield == 0) {
                player.setArmor(0);

            }
            if (shield > 0) {
                shield();
                shield--;
            }
        }

        private void castMagicMissile() {
            player.setMana(player.getMana() - 53);
            spentMana += 53;
            magicMissile = 1;
        }

        private void castBossAttack() {
            bossAttack = 1;
        }

        private void castDrain() {
            player.setMana(player.getMana() - 73);
            spentMana += 73;
            drain = 1;
        }

        private void castPoison() {
            player.setMana(player.getMana() - 173);
            spentMana += 173;
            poison = 6;
        }

        private void castRecharge() {
            player.setMana(player.getMana() - 229);
            spentMana += 229;
            recharge = 5;
        }

        private void castShield() {
            player.setMana(player.getMana() - 113);
            spentMana += 113;
            shield = 6;
        }

        private void bossAttack() {
            if (boss.getDamage() - player.getArmor() > 0) {
                player.setHit(player.getHit() - (boss.getDamage() - player.getArmor()));
            } else {
                player.setHit(player.getHit() - 1);
            }
        }

        private void magicMissile() {
            boss.setHit(boss.getHit() - 4);
        }

        private void drain() {
            boss.setHit(boss.getHit() - 2);
            player.setHit(player.getHit() + 2);
        }

        private void poison() {
            boss.setHit(boss.getHit() - 3);
        }

        private void recharge() {
            player.setMana(player.getMana() + 101);
        }

        private void shield() {
            player.setArmor(7);
        }
    }

    private static class Player {
        int hit = 50;
        int mana = 500;
        int armor = 0;

        public Player() {
        }

        public int getArmor() {
            return armor;
        }

        public void setArmor(int armor) {
            this.armor = armor;
        }

        public int getHit() {
            return hit;
        }

        public void setHit(int hit) {
            this.hit = hit;
        }

        public int getMana() {
            return mana;
        }

        public void setMana(int mana) {
            this.mana = mana;
        }
    }

    private static class Boss {
        int hit;
        int damage;

        public Boss(int hit, int damage) {
            this.hit = hit;
            this.damage = damage;
        }

        public int getDamage() {
            return damage;
        }

        public int getHit() {
            return hit;
        }

        public void setHit(int hit) {
            this.hit = hit;
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Permutation(0, nums, result);
        return result;
    }

    private static void Permutation(int i, int[] nums, List<List<Integer>> result) {
        if (i == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int n : nums) list.add(n);
            result.add(list);
        } else {
            for (int j = i, l = nums.length; j < l; j++) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                Permutation(i + 1, nums, result);
                temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
        }
    }
}
