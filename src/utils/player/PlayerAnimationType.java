package utils.player;

import java.util.HashMap;
import java.util.Map;

public enum PlayerAnimationType {
    IDLE(0),
    RUNNING(1),
    JUMPING(2),
    FALLING(3),
    GROUND(4),
    HIT(5),
    ATTACK_1(6),
    ATTACK_JUMP_1(7),
    ATTACK_JUMP_2(8);

    private static Map map = new HashMap<>();

    static {
        for (PlayerAnimationType animationType : PlayerAnimationType.values()) {
            map.put(animationType.value, animationType);
        }
    }

    private int value;

    private PlayerAnimationType(int value) {
        this.value = value;
    }

    public static PlayerAnimationType valueOf(int animationType) {
        return (PlayerAnimationType) map.get(animationType);
    }

    public int getValue() {
        return value;
    }
}
