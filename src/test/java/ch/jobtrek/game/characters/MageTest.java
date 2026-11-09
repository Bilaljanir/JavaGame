package ch.jobtrek.game.characters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MageTest {
    private Mage mage;
    private TestDummy target;

    @BeforeEach
    void setUp() {
        mage = new Mage("Merlin");
        target = new TestDummy();
    }

    @Test
    void magicBoltCostsManaAndDealsDamage() {
        mage.executeAttack(target);

        assertEquals(90, mage.getMana());
        assertEquals(20, target.damageTaken());
    }

    @Test
    void magicBoltWorksWithExactlyEnoughMana() {
        mage.useSpecialAbility(target);
        mage.useSpecialAbility(target);
        mage.useSpecialAbility(target); // 100 -> 10
        int hpBefore = target.getHp();

        mage.executeAttack(target);

        assertEquals(0, mage.getMana());
        assertEquals(20, hpBefore - target.getHp());
    }

    @Test
    void magicBoltFallsBackToStaffWithoutMana() {
        drainToZero();
        int hpBefore = target.getHp();

        mage.executeAttack(target);

        assertEquals(0, mage.getMana());
        assertEquals(10, hpBefore - target.getHp());
    }

    @Test
    void fireballCostsManaAndDealsDoubleDamage() {
        mage.useSpecialAbility(target);

        assertEquals(70, mage.getMana());
        assertEquals(40, target.damageTaken());
    }

    @Test
    void fireballWorksWithExactlyEnoughMana() {
        mage.useSpecialAbility(target);
        mage.useSpecialAbility(target);
        mage.executeAttack(target); // 100 -> 30
        int hpBefore = target.getHp();

        mage.useSpecialAbility(target);

        assertEquals(0, mage.getMana());
        assertEquals(40, hpBefore - target.getHp());
    }

    @Test
    void fireballFallsBackToStaffBelowCostWithoutSpendingMana() {
        mage.useSpecialAbility(target);
        mage.useSpecialAbility(target);
        mage.useSpecialAbility(target);
        mage.onTurnStart(); // 100 -> 10 -> 20
        int hpBefore = target.getHp();

        mage.useSpecialAbility(target);

        assertEquals(20, mage.getMana());
        assertEquals(10, hpBefore - target.getHp());
    }

    @Test
    void manaRegeneratesEachTurn() {
        mage.useSpecialAbility(target); // 70

        mage.onTurnStart();

        assertEquals(80, mage.getMana());
    }

    @Test
    void manaRegenIsCappedAtMax() {
        mage.executeAttack(target); // 90
        mage.onTurnStart();
        mage.onTurnStart();

        assertEquals(mage.getMaxMana(), mage.getMana());
    }

    private void drainToZero() {
        mage.useSpecialAbility(target);
        mage.useSpecialAbility(target);
        mage.useSpecialAbility(target);
        mage.executeAttack(target);
        assertEquals(0, mage.getMana());
    }
}
