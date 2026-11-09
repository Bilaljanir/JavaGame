package ch.jobtrek.game.engine;

import static org.junit.jupiter.api.Assertions.assertFalse;

import ch.jobtrek.game.characters.Character;
import ch.jobtrek.game.characters.Mage;
import ch.jobtrek.game.characters.Rogue;
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
    void invalidChoicesAreAskedAgain() {
        Character player = new Rogue("Shade");
        Character opponent = new Warrior("Conan");

        new BattleEngine(player, opponent, new Scanner("abc\n5\n" + "2\n".repeat(50))).startBattle();

        assertFalse(player.isAlive() && opponent.isAlive());
    }
}
