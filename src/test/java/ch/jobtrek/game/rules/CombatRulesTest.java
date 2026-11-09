package ch.jobtrek.game.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.jobtrek.game.characters.Character;
import ch.jobtrek.game.characters.Mage;
import ch.jobtrek.game.characters.Rogue;
import ch.jobtrek.game.characters.Warrior;
import org.junit.jupiter.api.Test;

class CombatRulesTest {

    @Test
    void calculateDamageAppliesModifierThenDefense() {
        assertEquals(16, CombatRules.calculateDamage(14, 1.5, 5));
    }

    @Test
    void calculateDamageIsAtLeastOne() {
        assertEquals(1, CombatRules.calculateDamage(5, 1.0, 50));
    }

    @Test
    void fasterCharacterAttacksFirst() {
        Character rogue = new Rogue("Shade");
        Character warrior = new Warrior("Conan");
        assertSame(rogue, CombatRules.getFirstAttacker(rogue, warrior));
        assertSame(rogue, CombatRules.getFirstAttacker(warrior, rogue));
    }

    @Test
    void equalSpeedPicksOneOfTheTwo() {
        Character mage1 = new Mage("Merlin");
        Character mage2 = new Mage("Gandalf");

        Character first = CombatRules.getFirstAttacker(mage1, mage2);

        assertTrue(first == mage1 || first == mage2);
    }

    @Test
    void damageVarianceStaysWithinFifteenPercent() {
        for (int i = 0; i < 1000; i++) {
            int damage = CombatRules.applyDamageVariance(100);
            assertTrue(damage >= 85 && damage <= 115, "damage out of range: " + damage);
        }
    }
}
