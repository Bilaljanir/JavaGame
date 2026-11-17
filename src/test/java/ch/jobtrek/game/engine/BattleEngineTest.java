package ch.jobtrek.game.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ch.jobtrek.game.characters.Character;
import ch.jobtrek.game.characters.Mage;
import ch.jobtrek.game.characters.Warrior;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class BattleEngineTest {

    @Test
    void battleEndsWhenOneCharacterIsDefeated() {
        Character player = new Warrior("Conan");
        Character opponent = new Mage("Merlin");

        new BattleEngine(player, opponent, new Scanner("1\n".repeat(50))).startBattle();

        assertFalse(player.isAlive() && opponent.isAlive());
    }

    @Test
    void invalidChoicesAreAskedAgainWithinTheSameTurn() {
        RecordingFighter player = new RecordingFighter("Player", 10);
        RecordingFighter opponent = new RecordingFighter("Opponent", 1);

        // round 1: "abc" and "5" are rejected, then "2" runs; round 2: input is closed, player flees
        new BattleEngine(player, opponent, new Scanner("abc\n5\n2\n")).startBattle();

        assertEquals(0, player.attacks());
        assertEquals(1, player.specialAbilities());
        assertEquals(1, opponent.attacks());
    }

    @Test
    void battleStopsWithoutCrashingWhenInputIsClosed() {
        Character player = new Warrior("Conan");
        Character opponent = new Mage("Merlin");

        new BattleEngine(player, opponent, new Scanner("1\n")).startBattle();

        assertTrue(player.isAlive() && opponent.isAlive());
    }
}
