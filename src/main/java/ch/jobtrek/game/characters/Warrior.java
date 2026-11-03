package ch.jobtrek.game.characters;

public class Warrior extends Character {
    private static final int POWER_STRIKE_HP_COST = 10;

    public Warrior(String name) {
        super(name, 120, 12, 10, 4);
    }

    @Override
    public void executeAttack(Character target) {
        int damage = Math.max(1, getPower() - target.getDefense());
        target.takeDamage(damage);
        System.out.println(getName() + " strikes " + target.getName() + " with a sword for " + damage + " damage!");
    }

    @Override
    public void useSpecialAbility(Character target) {
        int damage = Math.max(1, getPower() - target.getDefense()) * 2;
        target.takeDamage(damage);
        takeDamage(POWER_STRIKE_HP_COST);
        System.out.println(getName() + " uses Power Strike on " + target.getName() + " for " + damage + " damage! (-"
                + POWER_STRIKE_HP_COST + " HP)");
    }

    @Override
    public String describeActions() {
        return "1. Sword attack  2. Power Strike (costs " + POWER_STRIKE_HP_COST + " HP)";
    }
}
