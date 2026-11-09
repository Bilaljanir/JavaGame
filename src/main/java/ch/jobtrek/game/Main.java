package ch.jobtrek.game;

import ch.jobtrek.game.characters.Character;
import ch.jobtrek.game.characters.Mage;
import ch.jobtrek.game.characters.Warrior;
import ch.jobtrek.game.engine.BattleEngine;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        Character player = new Warrior("Aragon");
        Character opponent = new Mage("Gandalf");

        new BattleEngine(player, opponent, scanner).startBattle();
    }
}
