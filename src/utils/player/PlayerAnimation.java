package utils.player;

public class PlayerAnimation {
    public static int getSpriteAnimationCount(PlayerAnimationType animationType) {
        return switch (animationType) {
            case IDLE -> 5;
            case RUNNING -> 6;
            case FALLING -> 1;
            case GROUND -> 2;
            case HIT -> 4;
            case JUMPING, ATTACK_1, ATTACK_JUMP_1, ATTACK_JUMP_2 -> 3;
        };
    }
}
