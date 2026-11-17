package ch.jobtrek.game.engine;

import ch.jobtrek.game.characters.Character;

/** Fighter that deals no damage and only counts the actions it performs. */
class RecordingFighter extends Character {
    private int attacks;
    private int specialAbilities;

    RecordingFighter(String name, int speed) {
        super(name, 100, 0, 0, speed);
    }

    int attacks() {
        return attacks;
    }

    int specialAbilities() {
        return specialAbilities;
    }

    @Override
    public void executeAttack(Character target) {
        attacks++;
    }

    @Override
    public void useSpecialAbility(Character target) {
        specialAbilities++;
    }

    @Override
    public boolean canUseSpecialAbility() {
        return false;
    }

    @Override
    public String describeActions() {
        return "1. Attack  2. Special ability";
    }
}
