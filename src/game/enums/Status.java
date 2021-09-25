package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this capability to be hostile towards something (e.g., to be attacked by enemy)
    REST,
    SOFT_RESET,
    SPAWN_UNDEAD,
    ENTER_FIRELINK_SHRINE,
    UNATTACKABLE,
    UNARMED,
    RESURRECTABLE,
    ENEMIES_KILLED,
    NOT_WEAK_TO_STORM_RULER
}
