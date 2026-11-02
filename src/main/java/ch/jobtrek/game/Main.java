package ch.jobtrek.game;

import ch.jobtrek.game.characters.Character;

public class Main {
    static void main() {
        Character warrior = new Character("Warrior", 100, 15, 10, 8);
        System.out.println(warrior);

        for (int i = 0; i < 5; i++) {
            warrior.takeDamage(30);
            System.out.println(warrior + " alive: " + warrior.isAlive());
        }

        warrior.setHp(500);
        System.out.println(warrior);
    }
}
