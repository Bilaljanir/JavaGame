package ch.jobtrek.game.characters;

public abstract class Character {
    private String name;
    private int hp;
    private int maxHp;
    private int power;
    private int defense;
    private int speed;

    public Character(String name, int maxHp, int power, int defense, int speed) {
        if (maxHp < 0) {
            throw new IllegalArgumentException("maxHp must not be negative");
        }
        this.name = name;
        this.maxHp = maxHp;
        this.power = power;
        this.defense = defense;
        this.speed = speed;
        this.hp = maxHp;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getPower() {
        return power;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHp(int hp) {
        if (hp < 0) {
            this.hp = 0;
        } else if (hp > maxHp) {
            this.hp = maxHp;
        } else {
            this.hp = hp;
        }
    }

    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must not be negative");
        }
        setHp(hp - amount);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void onTurnStart() {
    }

    public abstract void executeAttack(Character target);

    public abstract void useSpecialAbility(Character target);

    public abstract boolean canUseSpecialAbility();

    public abstract String describeActions();

    @Override
    public String toString() {
        return name + " [HP: " + hp + "/" + maxHp + ", POW: " + power + ", DEF: " + defense + ", SPD: " + speed + "]";
    }
}
