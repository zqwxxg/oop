package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this capability to be hostile towards something (e.g., to be attacked by enemy)
    REST,             // to perform rest action
    SOFT_RESET,       // to perform soft reset action
    SPAWN_UNDEAD,     // to spawn undead
    ENTER_FLOOR,      // to be allowed to enter floor
    UNATTACKABLE,     // to be unattackable by other actors
    UNARMED,          // to represent if an actor is not carrying any weapon
    RESURRECTABLE,    // to revive
    ENEMIES_KILLED,   // to represent if an enemy is killed
    NOT_WEAK_TO_STORM_RULER // to represent if an enemy is weak to Storm Ruler
}
