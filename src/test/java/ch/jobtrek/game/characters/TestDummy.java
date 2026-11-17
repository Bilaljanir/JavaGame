package ch.jobtrek.game.characters;

/** Target with no defense and lots of HP, so damage values are easy to check. */
class TestDummy extends Character {
    TestDummy() {
        super("Dummy", 1000, 0, 0, 0);
    }

    int damageTaken() {
        return getMaxHp() - getHp();
    }

    @Override
    public void executeAttack(Character target) {
    }

    @Override
    public void useSpecialAbility(Character target) {
    }

    @Override
    public boolean canUseSpecialAbility() {
        return false;
    }

    @Override
    public String describeActions() {
        return "";
    }
}
