package ch.jobtrek.game;

import ch.jobtrek.game.characters.Character;
import ch.jobtrek.game.characters.Mage;
import ch.jobtrek.game.characters.Rogue;
import ch.jobtrek.game.characters.Warrior;

public class Main {
    static void main() {
        Character warrior = new Warrior("Conan");
        Character mage = new Mage("Merlin");
        Character rogue = new Rogue("Shadow");

        System.out.println(warrior);
        System.out.println(mage);
        System.out.println(rogue);

        warrior.executeAttack(mage);
        mage.useSpecialAbility(rogue);
        System.out.println(rogue.describeActions());
    }
}
