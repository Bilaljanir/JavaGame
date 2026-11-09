package ch.jobtrek.game.characters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RogueTest {
    private Rogue rogue;
    private TestDummy target;

    @BeforeEach
    void setUp() {
        rogue = new Rogue("Shade");
        target = new TestDummy();
    }

    @Test
    void daggerAttackGetsSpeedBonusAndCostsNoEnergy() {
        rogue.executeAttack(target);

        assertEquals(100, rogue.getEnergy());
        assertEquals(21, target.damageTaken());
    }

    @Test
    void daggerAttackHasNoBonusAgainstEquallyFastTarget() {
        Rogue other = new Rogue("Other");
        Rogue rogueB = new Rogue("RogueB");
        // equal speed: no bonus, power 14 - defense 5 = 9
        rogueB.executeAttack(other);

        assertEquals(other.getMaxHp() - 9, other.getHp());
    }

    @Test
    void backstabCostsEnergyAndDealsCriticalDamage() {
        rogue.useSpecialAbility(target);

        assertEquals(60, rogue.getEnergy());
        assertEquals(35, target.damageTaken());
    }

    @Test
    void backstabWorksWithExactlyEnoughEnergy() {
        rogue.useSpecialAbility(target);
        rogue.useSpecialAbility(target); // 20
        rogue.onTurnStart();
        rogue.onTurnStart(); // 50
        rogue.useSpecialAbility(target); // 10
        rogue.onTurnStart();
        rogue.onTurnStart(); // 40
        assertEquals(40, rogue.getEnergy());
        int hpBefore = target.getHp();

        rogue.useSpecialAbility(target);

        assertEquals(0, rogue.getEnergy());
        assertEquals(35, hpBefore - target.getHp());
    }

    @Test
    void backstabFallsBackToDaggerBelowCostWithoutSpendingEnergy() {
        rogue.useSpecialAbility(target);
        rogue.useSpecialAbility(target); // 20
        int hpBefore = target.getHp();

        rogue.useSpecialAbility(target);

        assertEquals(20, rogue.getEnergy());
        assertEquals(21, hpBefore - target.getHp());
    }

    @Test
    void energyRegeneratesEachTurn() {
        rogue.useSpecialAbility(target); // 60

        rogue.onTurnStart();

        assertEquals(75, rogue.getEnergy());
    }

    @Test
    void energyRegenIsCappedAtMax() {
        rogue.useSpecialAbility(target); // 60
        rogue.onTurnStart();
        rogue.onTurnStart();
        rogue.onTurnStart(); // 105 -> capped

        assertEquals(rogue.getMaxEnergy(), rogue.getEnergy());
    }
}
