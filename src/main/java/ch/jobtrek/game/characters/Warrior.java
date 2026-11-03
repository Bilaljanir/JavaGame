package ch.jobtrek.game.characters;

public class Warrior extends Character {

    public Warrior(String name) {
        super(name, 120, 12, 10, 4);
    }

    @Override
    public void executeAttack(Character target) {
        System.out.println(getName() + " attacks " + target.getName() + ".");
    }

    @Override
    public void useSpecialAbility(Character target) {
        System.out.println(getName() + " uses Shield Bash on " + target.getName() + ".");
    }

    @Override
    public String describeActions() {
        return "1. Attack  2. Shield Bash";
    }
}
