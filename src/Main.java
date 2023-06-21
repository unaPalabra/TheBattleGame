import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static Personage gamer = null;
    private static BufferedReader buff;
    private static BattleField battleField = null;

    public static void main(String[] args) {

        buff = new BufferedReader(new InputStreamReader(System.in));
        battleField = new BattleField();

        System.out.println("\t\t\tTHE BATTLE GAME");
        System.out.println("Enter the player's name: ");

        try {
            menuGame(buff.readLine());

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static void menuGame(String str) throws IOException {
        if (gamer == null) {
            gamer = new Player(str, 100, 20, 15, 0, 5);
            menuList();
        }

        switch (str) {
            case "1" -> {
                System.out.println("Sorry " + gamer.getName() + " ,the merchant hasn't come to work yet\n");
                menuList();
                menuGame(buff.readLine());
            }
            case "2" -> {
                System.out.println("The bad news is that monsters live in the dark forest..\n" +
                        " but if you defeat them, you can get a reward, a some gold.\n");
                fighting();
            }
            case "3" -> {
                System.out.println("Game finished.");
                System.exit(1);
            }
            case "4" -> {
                gamer.info();
                menuList();
            }
            case "y" -> menuGame("2");
            case "n" -> {
                menuList();
                menuGame(buff.readLine());
            }
        }
        menuGame(buff.readLine());
    }

    static void buy(String str) throws IOException {
        System.out.println("I have a potion to improve your health. Do you want to buy a product or " +
                "will you go further on business? (y/n)");
        switch (str) {
            case "y" -> System.out.println();
        }
        buy(buff.readLine());
    }

    static void menuList() {
        System.out.println(gamer.getName() + ", where do you want to go?\n" +
                "\t1. To the merchant\n" +
                "\t2. Into the dark forest\n" +
                "\t3. To the exit\n" +
                "\t4. Your condition\n" +
                "\t(enter the number)");
    }

    static void fighting() {
        battleField.fight(gamer, randomMonster(), new ResultBattle() {

            @Override
            public void fightWin() {
                gamer.info();
                System.out.println("Do you want to continue hiking or no? (y/n)");

                try {
                    menuGame(buff.readLine());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void fightLost() {
                System.exit(1);
            }
        });
    }

    static Personage randomMonster() {
        if (Math.random() > 0.5) return new Goblin("Goblin", 100, 15, 20, 10, 5);
        else return new Skeleton("Skeleton", 110, 20, 15, 10, 10);
    }

    interface ResultBattle {
        void fightWin();

        void fightLost();
    }

}


