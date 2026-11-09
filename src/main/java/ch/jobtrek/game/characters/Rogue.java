package ch.jobtrek.game.characters;

import ch.jobtrek.game.rules.CombatRules;

public class Rogue extends Character {
    private static final int BACKSTAB_ENERGY_COST = 40;
    private static final int ENERGY_REGEN = 15;
    private static final double SPEED_BONUS_MODIFIER = 1.5;
    private static final double BACKSTAB_MODIFIER = 2.5;

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
    public void onTurnStart() {
        setEnergy(energy + ENERGY_REGEN);
    }

    @Override
    public void executeAttack(Character target) {
        double modifier = getSpeed() > target.getSpeed() ? SPEED_BONUS_MODIFIER : 1.0;
        int damage = CombatRules.calculateDamage(getPower(), modifier, target.getDefense());
        target.takeDamage(damage);
        System.out.println(getName() + " slashes " + target.getName() + " with daggers for " + damage + " damage!");
    }

    @Override
    public void useSpecialAbility(Character target) {
        if (energy < BACKSTAB_ENERGY_COST) {
            System.out.println("Not enough energy! Using basic attack instead.");
            executeAttack(target);
            return;
        }
        setEnergy(energy - BACKSTAB_ENERGY_COST);
        int damage = CombatRules.calculateDamage(getPower(), BACKSTAB_MODIFIER, target.getDefense());
        target.takeDamage(damage);
        System.out.println(getName() + " backstabs " + target.getName() + " for " + damage + " critical damage!");
    }

    @Override
    public String describeActions() {
        return "1. Dagger attack  2. Backstab (costs " + BACKSTAB_ENERGY_COST + " energy)";
    }

    @Override
    public String toString() {
        return super.toString() + " [ENERGY: " + energy + "/" + maxEnergy + "]";
    }
}
