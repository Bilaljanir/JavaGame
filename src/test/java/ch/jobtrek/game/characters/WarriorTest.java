package ch.jobtrek.game.characters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WarriorTest {
    private Warrior warrior;
    private TestDummy target;

    @BeforeEach
    void setUp() {
        warrior = new Warrior("Conan");
        target = new TestDummy();
    }

    @Test
    void swordAttackDealsDamageWithoutHpCost() {
        warrior.executeAttack(target);

        assertEquals(12, target.damageTaken());
        assertEquals(warrior.getMaxHp(), warrior.getHp());
    }

    @Test
    void powerStrikeDealsDoubleDamageAndCostsHp() {
        warrior.useSpecialAbility(target);

        assertEquals(24, target.damageTaken());
        assertEquals(warrior.getMaxHp() - 10, warrior.getHp());
    }

    @Test
    void powerStrikeHpCostNeverGoesBelowZero() {
        warrior.setHp(5);

        warrior.useSpecialAbility(target);

        assertEquals(0, warrior.getHp());
        assertFalse(warrior.isAlive());
        assertEquals(24, target.damageTaken());
    }

    @Test
    void warriorHasNoTurnStartRecovery() {
        warrior.useSpecialAbility(target);

        warrior.onTurnStart();

        assertEquals(warrior.getMaxHp() - 10, warrior.getHp());
    }
}
