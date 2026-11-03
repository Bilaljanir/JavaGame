package ch.jobtrek.game.characters;

public class Mage extends Character {
    private int mana;
    private int maxMana;

    public Mage(String name) {
        super(name, 70, 20, 4, 7);
        this.maxMana = 100;
        this.mana = maxMana;
    }

    public int getMana() {
        return mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    private void setMana(int mana) {
        if (mana < 0) {
            this.mana = 0;
        } else if (mana > maxMana) {
            this.mana = maxMana;
        } else {
            this.mana = mana;
        }
    }

    @Override
    public void executeAttack(Character target) {
        System.out.println(getName() + " attacks " + target.getName() + ".");
    }

    @Override
    public void useSpecialAbility(Character target) {
        System.out.println(getName() + " casts Fireball on " + target.getName() + ".");
    }

    @Override
    public String describeActions() {
        return "1. Attack  2. Fireball";
    }

    @Override
    public String toString() {
        return super.toString() + " [MANA: " + mana + "/" + maxMana + "]";
    }
}
