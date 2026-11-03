package ch.jobtrek.game;

import ch.jobtrek.game.characters.Character;
import ch.jobtrek.game.characters.Mage;
import ch.jobtrek.game.characters.Rogue;
import ch.jobtrek.game.characters.Warrior;

public class Main {
    static void main() {
        Character player = new Warrior("Aragon");
        Character enemy = new Mage("Gandalf");
        Character rogue = new Rogue("Shadow");

        player.executeAttack(enemy);
        enemy.executeAttack(player);
        rogue.executeAttack(player);
        System.out.println();

        player.useSpecialAbility(enemy);
        for (int i = 0; i < 4; i++) {
            enemy.useSpecialAbility(player);
        }
        for (int i = 0; i < 3; i++) {
            rogue.useSpecialAbility(enemy);
        }
        System.out.println();

        Character[] characters = {player, enemy, rogue};
        for (Character c : characters) {
            c.onTurnStart();
            System.out.println(c);
        }
    }
}
