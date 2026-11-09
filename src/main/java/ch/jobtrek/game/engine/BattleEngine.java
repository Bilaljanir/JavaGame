package ch.jobtrek.game.engine;

import ch.jobtrek.game.characters.Character;
import ch.jobtrek.game.rules.CombatRules;
import java.util.Scanner;

public class BattleEngine {
    private static final String SEPARATOR = "=".repeat(40);

    private final Character player;
    private final Character opponent;
    private final Scanner scanner;
    private int round = 1;

    public BattleEngine(Character player, Character opponent, Scanner scanner) {
        this.player = player;
        this.opponent = opponent;
        this.scanner = scanner;
    }

    public void startBattle() {
        System.out.println(player.getName() + " VS " + opponent.getName() + " - Fight!");
        while (player.isAlive() && opponent.isAlive()) {
            System.out.println();
            System.out.println(SEPARATOR);
            System.out.println("ROUND " + round);
            System.out.println(SEPARATOR);

            Character first = CombatRules.getFirstAttacker(player, opponent);
            Character second = first == player ? opponent : player;
            takeTurn(first);
            if (player.isAlive() && opponent.isAlive()) {
                takeTurn(second);
            }
            round++;
        }
        displayResult();
    }

    private void takeTurn(Character character) {
        character.onTurnStart();
        if (character == player) {
            playerTurn();
        } else {
            opponentTurn();
        }
    }

    private void playerTurn() {
        displayStatus();
        System.out.println("Your turn! Choose an action:");
        System.out.println(player.describeActions());
        if (readChoice() == 1) {
            player.executeAttack(opponent);
        } else {
            player.useSpecialAbility(opponent);
        }
        System.out.println(opponent.getName() + " has " + opponent.getHp() + "/" + opponent.getMaxHp() + " HP left.");
    }

    private void opponentTurn() {
        System.out.println(opponent.getName() + "'s turn...");
        if (opponent.canUseSpecialAbility()) {
            opponent.useSpecialAbility(player);
        } else {
            opponent.executeAttack(player);
        }
        System.out.println(player.getName() + " has " + player.getHp() + "/" + player.getMaxHp() + " HP left.");
    }

    private int readChoice() {
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equals("1")) {
                return 1;
            }
            if (input.equals("2")) {
                return 2;
            }
            System.out.println("Invalid choice, please enter 1 or 2.");
        }
    }

    private void displayStatus() {
        System.out.println("You:   " + player);
        System.out.println("Enemy: " + opponent);
    }

    private void displayResult() {
        System.out.println();
        System.out.println(SEPARATOR);
        if (player.isAlive()) {
            System.out.println("VICTORY! " + player.getName() + " defeated " + opponent.getName() + "!");
        } else if (opponent.isAlive()) {
            System.out.println("DEFEAT... " + opponent.getName() + " defeated " + player.getName() + ".");
        } else {
            System.out.println("DRAW! Both fighters have fallen.");
        }
        System.out.println(SEPARATOR);
    }
}
