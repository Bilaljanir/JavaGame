package ch.jobtrek.game.engine;

import ch.jobtrek.game.characters.Character;
import ch.jobtrek.game.input.InputValidator;
import ch.jobtrek.game.rules.CombatRules;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class BattleEngine {
    private static final String SEPARATOR = "=".repeat(40);

    private final Character player;
    private final Character opponent;
    private final Scanner scanner;
    private int round = 1;
    private boolean playerFled = false;

    public BattleEngine(Character player, Character opponent, Scanner scanner) {
        this.player = player;
        this.opponent = opponent;
        this.scanner = scanner;
    }

    public void startBattle() {
        System.out.println(player.getName() + " VS " + opponent.getName() + " - Fight!");
        while (!isBattleOver()) {
            System.out.println();
            System.out.println(SEPARATOR);
            System.out.println("ROUND " + round);
            System.out.println(SEPARATOR);

            Character first = CombatRules.getFirstAttacker(player, opponent);
            Character second = first == player ? opponent : player;
            takeTurn(first);
            if (!isBattleOver()) {
                takeTurn(second);
            }
            round++;
        }
        displayResult();
    }

    private boolean isBattleOver() {
        return !player.isAlive() || !opponent.isAlive() || playerFled;
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
        int choice;
        try {
            choice = InputValidator.readValidInt(scanner, 1, 2);
        } catch (NoSuchElementException e) {
            System.out.println();
            System.out.println("No more input: " + player.getName() + " flees the battle!");
            playerFled = true;
            return;
        }
        if (choice == 1) {
            player.executeAttack(opponent);
        } else {
            player.useSpecialAbility(opponent);
        }
        displayHpLeft(opponent);
    }

    private void opponentTurn() {
        System.out.println(opponent.getName() + "'s turn...");
        if (opponent.canUseSpecialAbility()) {
            opponent.useSpecialAbility(player);
        } else {
            opponent.executeAttack(player);
        }
        displayHpLeft(player);
    }

    private void displayHpLeft(Character character) {
        System.out.println(
                character.getName() + " has " + character.getHp() + "/" + character.getMaxHp() + " HP left.");
    }

    private void displayStatus() {
        System.out.println("You:   " + player);
        System.out.println("Enemy: " + opponent);
    }

    private void displayResult() {
        System.out.println();
        System.out.println(SEPARATOR);
        if (playerFled) {
            System.out.println("FLED... " + player.getName() + " left the battle.");
        } else if (player.isAlive()) {
            System.out.println("VICTORY! " + player.getName() + " defeated " + opponent.getName() + "!");
        } else if (opponent.isAlive()) {
            System.out.println("DEFEAT... " + opponent.getName() + " defeated " + player.getName() + ".");
        } else {
            System.out.println("DRAW! Both fighters have fallen.");
        }
        System.out.println("Rounds played: " + (round - 1));
        displayHpLeft(player);
        displayHpLeft(opponent);
        System.out.println(SEPARATOR);
    }
}
