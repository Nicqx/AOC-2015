package Days;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day22 {
    enum action {INSTANT, TIMED}

    int bossHit = 0;
    int bossDamage = 0;
    Boss boss;
    Player player;
    SpellContainer spellContainer;

    public Day22() {
        fileReader("resources/D22/input");

        System.out.println("D22 - The minimal used mana to player wins: " + findLeastMana(false, 1000));
        ////////-----comment for manual casting
//        int leastMana = manualFight();
        //////----comment for automate
        int leastMana = findLeastMana(true, 100000);
        //////// ----comment end
        System.out.println("D22/2 - The minimal used mana with Hard difficulty to player wins: " + leastMana);

    }

    private int manualFight() {
        int leastMana = Integer.MAX_VALUE;
        boss = new Boss(bossHit, bossDamage);
        while (!boss.isBossDead()) {
            System.out.println("---New Round---");
            boss = new Boss(bossHit, bossDamage);
            player = new Player();
            spellContainer = new SpellContainer();
            do {
                System.out.println("---------");
                System.out.println("boss hit: " + boss.getHit());
                System.out.println("player hit: " + player.getHit() + " mana: " + player.getMana());
                if (!spellContainer.getActiveSpells().stream().filter(s -> s.getName().equals("Shield")).toList().isEmpty()) {
                    System.out.println("Shield is active for: " + spellContainer.getActiveSpells().stream().filter(s -> s.getName().equals("Shield")).toList().get(0).getTime());
                }
                if (!spellContainer.getActiveSpells().stream().filter(s -> s.getName().equals("Recharge")).toList().isEmpty()) {
                    System.out.println("Recharge is active for: " + spellContainer.getActiveSpells().stream().filter(s -> s.getName().equals("Recharge")).toList().get(0).getTime());
                }
                if (!spellContainer.getActiveSpells().stream().filter(s -> s.getName().equals("Poison")).toList().isEmpty()) {
                    System.out.println("Poison is active for: " + spellContainer.getActiveSpells().stream().filter(s -> s.getName().equals("Poison")).toList().get(0).getTime());
                }

                System.out.println("0-Drain(cost 73 hit 2 heal 2), 1-Shield(cost 113 armor 7), 2-Recharge(cost 229 mana+ 101), 3-Poison(cost 173 damage 3), 4-MagicMissile(cost 53, hit 4), 5-Skipp: ");
                Scanner attack = new Scanner(System.in);
                attackChooser(attack.nextInt(), boss, player, spellContainer);

                bossAttack(boss, player, spellContainer);

            } while (!player.isPlayerDead() && !boss.isBossDead());
            if (player.getManaCost() < leastMana && boss.isBossDead()) {
                leastMana = player.getManaCost();
            }
        }

        return leastMana;
    }

    private int findLeastMana(boolean isDifficultyHard, int rematchCounter) {
        int leastMana = Integer.MAX_VALUE;
        for (int i = 0; i < rematchCounter; i++) {  //1000 run to get the 900 result... and 100000 run to get the 1216 result with hard...
            boss = new Boss(bossHit, bossDamage);
            player = new Player();
            spellContainer = new SpellContainer();
            do {
                if (isDifficultyHard) {
                    player.setHit(player.getHit() - 1);
                    if (player.isPlayerDead()) {
                        break;
                    }
                }
                while (!attackChooser(new Random().nextInt(5), boss, player, spellContainer)) {
                    if (player.getMana() < 53 && testIfRechargeActive(spellContainer)) {
                        attackChooser(5, boss, player, spellContainer);
                    } else if (player.getMana() < 53 && !testIfRechargeActive(spellContainer)) {
                        break;

                    }
                }
                if (boss.isBossDead()) {
                    break;
                }

                bossAttack(boss, player, spellContainer);

            } while (!player.isPlayerDead() && !boss.isBossDead());
            if (player.getManaCost() < leastMana && boss.isBossDead()) {
                leastMana = player.getManaCost();
            }
        }
        return leastMana;
    }

    private void fileReader(String res) {
        try (Scanner scanner = new Scanner(new File(res))) {
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
    }

    private boolean testIfRechargeActive(SpellContainer spellContainer) {
        return (!spellContainer.getActiveSpells().stream().filter(s -> s.getName().equals("Recharge")).toList().isEmpty() && spellContainer.getActiveSpells().stream().filter(s -> s.getName().equals("Recharge")).toList().get(0).getTime() >= 1);
    }

    private boolean attackChooser(int choice, Boss boss, Player player, SpellContainer spellContainer) {
        switch (choice) {
            case 0 -> {
                if (player.getMana() < 73) {
                    return false;
                }
            }
            case 1 -> {
                if (player.getMana() < 113 || (!spellContainer.getActiveSpells().stream().filter(s -> s.getName().equals("Shield")).toList().isEmpty() && spellContainer.getActiveSpells().stream().filter(s -> s.getName().equals("Shield")).toList().get(0).getTime() > 1)) {
                    return false;
                }
            }
            case 2 -> {
                if (player.getMana() < 229 || (!spellContainer.getActiveSpells().stream().filter(s -> s.getName().equals("Recharge")).toList().isEmpty() && spellContainer.getActiveSpells().stream().filter(s -> s.getName().equals("Recharge")).toList().get(0).getTime() > 1)) {
                    return false;
                }
            }
            case 3 -> {
                if (player.getMana() < 173 || (!spellContainer.getActiveSpells().stream().filter(s -> s.getName().equals("Poison")).toList().isEmpty() && spellContainer.getActiveSpells().stream().filter(s -> s.getName().equals("Poison")).toList().get(0).getTime() > 1)) {
                    return false;
                }
            }
            case 4 -> {
                if (player.getMana() < 53) {
                    return false;
                }
            }
        }

        switch (choice) {
            case 0 -> playerAttackDrain(boss, player, spellContainer);
            case 1 -> playerAttackShield(boss, player, spellContainer);
            case 2 -> playerAttackRecharge(boss, player, spellContainer);
            case 3 -> playerAttackPoison(boss, player, spellContainer);
            case 4 -> playerAttackMagicMissile(boss, player, spellContainer);
            case 5 -> playerAttackSkipp(boss, player, spellContainer);
        }
        return true;
    }

    private void bossAttack(Boss boss, Player player, SpellContainer spellContainer) {
        attack(boss, player, spellContainer, new BossAttack());
    }

    private void playerAttackMagicMissile(Boss boss, Player player, SpellContainer spellContainer) {
        attack(boss, player, spellContainer, new MagicMissile());
    }

    private void playerAttackDrain(Boss boss, Player player, SpellContainer spellContainer) {
        attack(boss, player, spellContainer, new Drain());
    }

    private void playerAttackPoison(Boss boss, Player player, SpellContainer spellContainer) {
        attack(boss, player, spellContainer, new Poison());
    }

    private void playerAttackShield(Boss boss, Player player, SpellContainer spellContainer) {
        attack(boss, player, spellContainer, new Shield());
    }

    private void playerAttackRecharge(Boss boss, Player player, SpellContainer spellContainer) {
        attack(boss, player, spellContainer, new Recharge());
    }

    private void playerAttackSkipp(Boss boss, Player player, SpellContainer spellContainer) {
        attack(boss, player, spellContainer, new SkippAttack());
    }

    private void attack(Boss boss, Player player, SpellContainer spellContainer, Spell actualSpell) {

        // check active spells
        for (Spell spell : spellContainer.getActiveSpells()) {
            if (spell.getCast() == action.TIMED) {
                spell.effect(player, boss);
            }
        }
        if (boss.isBossDead() || player.isPlayerDead()) {
            return;
        }
        // remove wore off spells
        Collection<Spell> removeCandidates = new LinkedList<>();
        for (Spell spell : spellContainer.getActiveSpells()) {
            if (spell.getCast() == action.TIMED && spell.getTime() == 0) {
                if (spell.getClass() == Shield.class) {
                    player.setArmor(0);
                }
                removeCandidates.add(spell);
            }
        }
        removeCandidates.forEach(spellContainer.getActiveSpells()::remove);
        // cast spells (add list)
        spellContainer.addSpell(actualSpell);

        // cast spells from list
        removeCandidates.clear();
        for (Spell spell : spellContainer.getActiveSpells()) {
            if (spell.getCast() == action.INSTANT || spell.getCast() == null) {
                spell.cast(player, boss);
                if (spell.getCast() == action.INSTANT) {
                    removeCandidates.add(spell);
                }
            }
        }
        removeCandidates.forEach(spellContainer.getActiveSpells()::remove);
    }

    private static class SpellContainer {
        Set<Spell> activeSpells = new HashSet<>();

        public SpellContainer() {
        }

        public Set<Spell> getActiveSpells() {
            return activeSpells;
        }

        public void addSpell(Spell spell) {
            this.activeSpells.add(spell);
        }

    }

    private interface Spell {
        String getName();

        void cast(Player player, Boss boss);

        action getCast();

        void effect(Player player, Boss boss);

        int getTime();

    }

    private static class SkippAttack implements Spell {

        @Override
        public String getName() {
            return "Skipp";
        }

        @Override
        public void cast(Player player, Boss boss) {

        }

        @Override
        public action getCast() {
            return null;
        }

        @Override
        public void effect(Player player, Boss boss) {

        }

        @Override
        public int getTime() {
            return 0;
        }
    }

    private static class Recharge implements Spell {
        String name = "Recharge";
        action cast;
        private int time = 5;
        private final int cost = 229;

        @Override
        public action getCast() {
            return cast;
        }

        @Override
        public int getTime() {
            return time;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void cast(Player player, Boss boss) {
            player.addManaCost(cost);
            player.setMana(player.getMana() - cost);
            cast = action.TIMED;
        }

        @Override
        public void effect(Player player, Boss boss) {
            player.setMana(player.getMana() + 101);
            time--;
        }
    }

    private static class Poison implements Spell {
        String name = "Poison";
        action cast;
        private int time = 6;
        private final int cost = 173;

        public Poison() {
        }

        @Override
        public action getCast() {
            return cast;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void cast(Player player, Boss boss) {
            player.addManaCost(cost);
            player.setMana(player.getMana() - cost);
            cast = action.TIMED;
        }

        @Override
        public void effect(Player player, Boss boss) {
            boss.setHit(boss.getHit() - 3);
            time--;
        }

        @Override
        public int getTime() {
            return time;
        }
    }

    private static class Shield implements Spell {
        String name = "Shield";
        action cast;
        private final int cost = 113;
        private int time = 6;

        public Shield() {
        }

        @Override
        public action getCast() {
            return cast;
        }

        @Override
        public int getTime() {
            return time;
        }

        @Override
        public void effect(Player player, Boss boss) {
            player.setArmor(7);
            time--;
        }


        @Override
        public String getName() {
            return name;
        }

        @Override
        public void cast(Player player, Boss boss) {
            player.addManaCost(cost);
            player.setMana(player.getMana() - cost);
            cast = action.TIMED;
        }
    }

    private static class BossAttack implements Spell {
        String name = "BossAttack";
        final action cast = action.INSTANT;

        public BossAttack() {
        }

        @Override
        public action getCast() {
            return cast;
        }

        @Override
        public void effect(Player player, Boss boss) {
        }

        @Override
        public int getTime() {
            return 0;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void cast(Player player, Boss boss) {
            if (boss.getDamage() - player.getArmor() > 0) {
                player.setHit(player.getHit() - (boss.getDamage() - player.getArmor()));
            } else {
                player.setHit(player.getHit() - 1);
            }
        }
    }

    private static class Drain implements Spell {
        String name = "Drain";
        private final int cost = 73;
        final action cast = action.INSTANT;

        public Drain() {
        }

        @Override
        public action getCast() {
            return cast;
        }

        @Override
        public void effect(Player player, Boss boss) {
        }

        @Override
        public int getTime() {
            return 0;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void cast(Player player, Boss boss) {
            player.addManaCost(cost);
            player.setMana(player.getMana() - cost);
            boss.setHit(boss.getHit() - 2);
            player.setHit(player.getHit() + 2);
        }

    }

    private static class MagicMissile implements Spell {
        String name = "MagicMissile";
        private final int cost = 53;
        private final action cast = action.INSTANT;

        public MagicMissile() {
        }

        @Override
        public action getCast() {
            return cast;
        }

        @Override
        public void effect(Player player, Boss boss) {

        }

        @Override
        public int getTime() {
            return 0;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void cast(Player player, Boss boss) {
            player.addManaCost(cost);
            player.setMana(player.getMana() - cost);
            boss.setHit(boss.getHit() - 4);
        }
    }

    private static class Player {
        int hit = 50;
        int mana = 500;
        int armor = 0;
        int manaCost = 0;

        public Player() {
        }

        public int getManaCost() {
            return manaCost;
        }

        public void addManaCost(int manaCost) {
            this.manaCost += manaCost;
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

        public boolean isPlayerDead() {
            return hit <= 0;
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

        public boolean isBossDead() {
            return hit <= 0;
        }
    }

}
