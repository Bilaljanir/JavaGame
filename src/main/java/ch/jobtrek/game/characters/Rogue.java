package ch.jobtrek.game.characters;

public class Rogue extends Character {
    private int energy;
    private int maxEnergy;

    public Rogue(String name) {
        super(name, 90, 14, 5, 12);
        this.maxEnergy = 100;
        this.energy = maxEnergy;
    }

    public int getEnergy() {
        return energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    private void setEnergy(int energy) {
        if (energy < 0) {
            this.energy = 0;
        } else if (energy > maxEnergy) {
            this.energy = maxEnergy;
        } else {
            this.energy = energy;
        }
    }

    @Override
    public void executeAttack(Character target) {
        System.out.println(getName() + " attacks " + target.getName() + ".");
    }

    public void useSpecialAbility(Character target) {
        System.out.println(getName() + " uses Backstab on " + target.getName() + ".");
    }

    @Override
    public String describeActions() {
        return "1. Attack  2. Backstab";
    }

    @Override
    public String toString() {
        return super.toString() + " [ENERGY: " + energy + "/" + maxEnergy + "]";
    }
}
