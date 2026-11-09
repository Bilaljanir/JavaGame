package ch.jobtrek.game.characters;

import ch.jobtrek.game.rules.CombatRules;

public class Mage extends Character {
    private static final int ATTACK_MANA_COST = 10;
    private static final int FIREBALL_MANA_COST = 30;
    private static final int MANA_REGEN = 10;
    private static final double FIREBALL_MODIFIER = 2.0;
    private static final double STAFF_MODIFIER = 0.5;

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
    public void onTurnStart() {
        setMana(mana + MANA_REGEN);
    }

    @Override
    public void executeAttack(Character target) {
        if (mana < ATTACK_MANA_COST) {
            System.out.println("Not enough mana! Using basic attack instead.");
            basicAttack(target);
            return;
        }
        setMana(mana - ATTACK_MANA_COST);
        int damage = CombatRules.calculateDamage(getPower(), 1.0, target.getDefense());
        target.takeDamage(damage);
        System.out.println(getName() + " hits " + target.getName() + " with a magic bolt for " + damage + " damage!");
    }

    @Override
    public void useSpecialAbility(Character target) {
        if (mana < FIREBALL_MANA_COST) {
            System.out.println("Not enough mana! Using basic attack instead.");
            basicAttack(target);
            return;
        }
        setMana(mana - FIREBALL_MANA_COST);
        int damage = CombatRules.calculateDamage(getPower(), FIREBALL_MODIFIER, target.getDefense());
        target.takeDamage(damage);
        System.out.println(getName() + " casts Fireball on " + target.getName() + " for " + damage + " damage!");
    }

    private void basicAttack(Character target) {
        int damage = CombatRules.calculateDamage(getPower(), STAFF_MODIFIER, target.getDefense());
        target.takeDamage(damage);
        System.out.println(getName() + " hits " + target.getName() + " with a staff for " + damage + " damage!");
    }

    @Override
    public String describeActions() {
        return "1. Magic bolt (costs " + ATTACK_MANA_COST + " mana)  2. Fireball (costs " + FIREBALL_MANA_COST + " mana)";
    }

    @Override
    public String toString() {
        return super.toString() + " [MANA: " + mana + "/" + maxMana + "]";
    }
}
