public class BattleField {

    public void fight(Personage player, Personage monster, Main.ResultBattle resultBattle) {
        Runnable runnable = () -> {
            int step = 1;
            boolean isEnd = false;

            System.out.printf("The battle began, good luck, %s!\n\n", Main.gamer.getName());
            while (!isEnd) {
                System.out.println("\t\t\tStep: " + step);
                if (step++ % 2 != 0) {
                    isEnd = hooking(player, monster, resultBattle);
                    isEnd = hooking(monster, player, resultBattle);
                   anim('\u25c0', '\u25c1');
                } else {
                    isEnd = hooking(monster, player, resultBattle);
                   anim('\u25b6', '\u25b7');
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void anim(Character ch1,Character ch2 ){
        try {
            System.out.print("\t\t\t");
            for (int i =0; i<2; i++ ){
                Thread.sleep(300);
                System.out.print(ch1+" ");
                Thread.sleep(300);
                System.out.print(ch2 +" ");

            }
            System.out.println();
        }catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    private Boolean hooking(Personage defender, Personage attacker, Main.ResultBattle resultBattle) {
        int hit = attacker.attack();
        int defHealth = defender.getHealth() - hit;

        if (hit != 0) {
            System.out.printf("%s struck with force %d.\n", attacker.getName(), hit);
            System.out.printf("%s has %d health units left.\n", defender.getName(), defHealth);
        } else {
            System.out.printf("%s missed!\n", attacker.getName());
        }

        if (defHealth <= 0 && defender instanceof Player) {
            System.out.println(defender.getName() + " ,the song is sung for you. Game over.");
            resultBattle.fightLost();
            return true;
        } else if (defHealth <= 0) {
            System.out.println(attacker.getName() + " ,You Win!!! And get gold and experience of monster.");
            attacker.setExperience(attacker.getExperience() + defender.getExperience());
            attacker.setGold(attacker.getGold() + defender.getGold());
            resultBattle.fightWin();

            return true;
        } else {
            defender.setHealth(defHealth);
            return false;
        }
    }
}
