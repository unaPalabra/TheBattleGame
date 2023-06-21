public abstract class Personage implements Fight {
    private String name;
    private int health;
    private int force;
    private int knack;
    private int experience;
    private int gold;

    public Personage(String name, int health, int force, int knack, int experience, int gold) {
        this.name = name;
        this.health = health;
        this.force = force;
        this.knack = knack;
        this.experience = experience;
        this.gold = gold;
    }

    @Override
    public int attack() {
       if (getKnack() * 3 > Math.random() * 100){
           return getForce();
       }else return 0;
    }

   public void info() {
        System.out.println(getName() + ", your awards and condition\n" +
                "Health: " + getHealth() + "\n" +
                "Experience: " + getExperience() + "\n" +
                "Gold: " + getGold() + "\n" +
                "Force : " + getForce() + "\n" +
                "Knack : " + getKnack() + "\n");
    }

    public String getName() { return name;
    }

    public int getHealth() {
        return health;
    }

    public int getForce() {
        return force;
    }

    public int getKnack() {
        return knack;
    }

    public int getExperience() {
        return experience;
    }

    public int getGold() {
        return gold;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setExperience(int experience) {
        this.experience += experience;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

}
