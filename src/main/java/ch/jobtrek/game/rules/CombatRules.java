package ch.jobtrek.game.rules;

import ch.jobtrek.game.characters.Character;
import java.util.Random;

public final class CombatRules {
    private static final Random RANDOM = new Random();
    private static final double DAMAGE_VARIANCE = 0.15;

    private CombatRules() {
    }

    public static int calculateDamage(int attackerPower, double abilityModifier, int targetDefense) {
        return Math.max(1, (int) (attackerPower * abilityModifier) - targetDefense);
    }

    public static Character getFirstAttacker(Character c1, Character c2) {
        if (c1.getSpeed() > c2.getSpeed()) {
            return c1;
        }
        if (c2.getSpeed() > c1.getSpeed()) {
            return c2;
        }
        return RANDOM.nextBoolean() ? c1 : c2;
    }
    public static int applyDamageVariance(int baseDamage) {
        double factor = 1 + RANDOM.nextDouble(-DAMAGE_VARIANCE, DAMAGE_VARIANCE);
        return (int) Math.round(baseDamage * factor);
    }

}
