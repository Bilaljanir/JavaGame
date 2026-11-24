package ch.jobtrek.game;

import ch.jobtrek.game.characters.Character;
import ch.jobtrek.game.characters.Mage;
import ch.jobtrek.game.characters.Rogue;
import ch.jobtrek.game.characters.Warrior;
import ch.jobtrek.game.engine.BattleEngine;
import ch.jobtrek.game.input.InputValidator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Random RANDOM = new Random();
    private static final String[] OPPONENT_NAMES = {"Thorin", "Morgana", "Shadow", "Elrond", "Lyra", "Grimnar"};
    private static final int CLASS_COUNT = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayWelcome();
        try {
            boolean playAgain = true;
            while (playAgain) {
                Character player = selectCharacter(scanner);
                Character opponent = createOpponent(player);
                BattleEngine engine = new BattleEngine(player, opponent, scanner);
                engine.startBattle();
                playAgain = askPlayAgain(scanner);
            }
        } catch (NoSuchElementException e) {
            System.out.println();
            System.out.println("No more input.");
        }
        displayGoodbye();
    }

    private static void displayWelcome() {
        System.out.println("========================================");
        System.out.println("            ARENA OF HEROES");
        System.out.println("  A turn-based battle in your terminal");
        System.out.println("========================================");
        System.out.println();
    }

    private static Character selectCharacter(Scanner scanner) {
        System.out.println("=== CHARACTER SELECTION ===");
        for (int choice = 1; choice <= CLASS_COUNT; choice++) {
            Character preview = createCharacter(choice, "preview");
            System.out.println();
            System.out.println(choice + ". " + preview.getClass().getSimpleName() + " - " + describeClass(choice));
            System.out.println("   HP: " + preview.getMaxHp() + " | POW: " + preview.getPower() + " | DEF: "
                    + preview.getDefense() + " | SPD: " + preview.getSpeed());
            System.out.println("   " + preview.describeActions());
        }
        System.out.println();
        System.out.println("Choose your class (1-" + CLASS_COUNT + "):");
        int choice = InputValidator.readValidInt(scanner, 1, CLASS_COUNT);

        System.out.println("Enter a name for your character:");
        String name = readLine(scanner);
        while (name.isEmpty()) {
            System.out.println("The name cannot be empty, please try again:");
            name = readLine(scanner);
        }
        return createCharacter(choice, name);
    }

    private static Character createOpponent(Character player) {
        String name = OPPONENT_NAMES[RANDOM.nextInt(OPPONENT_NAMES.length)];
        Character opponent;
        do {
            opponent = createCharacter(RANDOM.nextInt(1, CLASS_COUNT + 1), name);
        } while (opponent.getClass() == player.getClass());
        System.out.println();
        System.out.println("Your opponent is " + name + " the " + opponent.getClass().getSimpleName() + "!");
        return opponent;
    }

    private static Character createCharacter(int choice, String name) {
        return switch (choice) {
            case 1 -> new Warrior(name);
            case 2 -> new Mage(name);
            case 3 -> new Rogue(name);
            default -> throw new IllegalArgumentException("Unknown class choice: " + choice);
        };
    }

    private static String describeClass(int choice) {
        return switch (choice) {
            case 1 -> "High HP & Defense, low Speed";
            case 2 -> "High Power, has Mana for spells";
            case 3 -> "High Speed, uses Energy for tricks";
            default -> throw new IllegalArgumentException("Unknown class choice: " + choice);
        };
    }

    private static boolean askPlayAgain(Scanner scanner) {
        System.out.println();
        System.out.println("Play again? (y/n)");
        while (true) {
            String input = readLine(scanner).toLowerCase();
            if (input.equals("y")) {
                System.out.println();
                return true;
            }
            if (input.equals("n")) {
                return false;
            }
            System.out.println("Please enter y or n.");
        }
    }

    private static void displayGoodbye() {
        System.out.println();
        System.out.println("Thanks for playing ARENA OF HEROES. Goodbye!");
    }

    private static String readLine(Scanner scanner) {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }
}
