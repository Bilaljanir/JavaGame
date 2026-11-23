# Java game template

This repository contains a Java strater template with gradle.

## Requirements

- Java 25+
- Gradle 9+
- An IDE (like IntelliJ, Eclipse or even VSCode with Java extension)

Tested only under linux.

## Usage

Run `./gradlew run` to start the program.

If you write tests, you can run `./gradlew test` to run them.


## Class diagram

Legend: `-` private, `+` public, `#` protected, `$` static, `*` abstract (italic).

```mermaid
classDiagram
    direction TB

    class Main {
        -Random RANDOM$
        -String[] OPPONENT_NAMES$
        -int CLASS_COUNT$
        +main(String[] args)$ void
        -displayWelcome()$ void
        -selectCharacter(Scanner scanner)$ Character
        -readClassChoice(Scanner scanner)$ int
        -createOpponent(Character player)$ Character
        -createCharacter(int choice, String name)$ Character
        -describeClass(int choice)$ String
        -askPlayAgain(Scanner scanner)$ boolean
        -displayGoodbye()$ void
        -readLine(Scanner scanner)$ String
    }

    class BattleEngine {
        -Character player
        -Character opponent
        -Scanner scanner
        -int round
        -boolean playerFled
        +BattleEngine(Character player, Character opponent, Scanner scanner)
        +startBattle() void
        -isBattleOver() boolean
        -takeTurn(Character character) void
        -playerTurn() void
        -opponentTurn() void
        -readChoice() int
        -displayHpLeft(Character character) void
        -displayStatus() void
        -displayResult() void
    }

    class Character {
        <<abstract>>
        -String name
        -int hp
        -int maxHp
        -int power
        -int defense
        -int speed
        +Character(String name, int maxHp, int power, int defense, int speed)
        +getName() String
        +getHp() int
        +getMaxHp() int
        +getPower() int
        +getDefense() int
        +getSpeed() int
        +setHp(int hp) void
        +takeDamage(int amount) void
        +isAlive() boolean
        +onTurnStart() void
        +toString() String
        +executeAttack(Character target)* void
        +useSpecialAbility(Character target)* void
        +canUseSpecialAbility()* boolean
        +describeActions()* String
    }

    class Warrior {
        +Warrior(String name)
        +executeAttack(Character target) void
        +useSpecialAbility(Character target) void
        +canUseSpecialAbility() boolean
        +describeActions() String
    }

    class Mage {
        -int mana
        -int maxMana
        +Mage(String name)
        +getMana() int
        +getMaxMana() int
        -setMana(int mana) void
        +onTurnStart() void
        +executeAttack(Character target) void
        +useSpecialAbility(Character target) void
        +canUseSpecialAbility() boolean
        +describeActions() String
        +toString() String
    }

    class Rogue {
        -int energy
        -int maxEnergy
        +Rogue(String name)
        +getEnergy() int
        +getMaxEnergy() int
        -setEnergy(int energy) void
        +onTurnStart() void
        +executeAttack(Character target) void
        +useSpecialAbility(Character target) void
        +canUseSpecialAbility() boolean
        +describeActions() String
        +toString() String
    }

    class CombatRules {
        <<final>>[settings.gradle.kts](settings.gradle.kts)
        -Random RANDOM$
        -CombatRules()
        +calculateDamage(int attackerPower, double abilityModifier, int targetDefense)$ int
        +getFirstAttacker(Character c1, Character c2)$ Character
        +applyDamageVariance(int baseDamage)$ int
    }

    Character <|-- Warrior
    Character <|-- Mage
    Character <|-- Rogue

    Main ..> BattleEngine : creates
    Main ..> Warrior : creates
    Main ..> Mage : creates
    Main ..> Rogue : creates
    Main ..> Character : uses
    BattleEngine ..> Character : uses
    BattleEngine ..> CombatRules : uses
    Warrior ..> CombatRules : uses
    Mage ..> CombatRules : uses
    Rogue ..> CombatRules : uses
    CombatRules ..> Character : uses
```