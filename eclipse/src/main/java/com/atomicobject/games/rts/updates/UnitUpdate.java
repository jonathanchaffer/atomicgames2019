package com.atomicobject.games.rts.updates;

public class UnitUpdate {
    public static final String TYPE_TANK = "tank";
    public static final String TYPE_SCOUT = "scout";
    public static final String TYPE_WORKER = "worker";
    public static final String TYPE_BASE = "base";

    public static final String STATUS_IDLE = "idle";
    public static final String STATUS_MOVING = "moving";
    public static final String STATUS_DEAD = "dead";
    public static final String STATUS_UKNOWN = "unknown";

    private int id;
    private int x;
    private int y;
    private int resource;
    private int health;
    private String playerId;
    private String type;
    private boolean canAttack;
    private int attackDamage;
    private int attackCooldownDuration;
    private int attackCooldown;
    private String attackType;
    private String status;
    private int range;
    private int speed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean canAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getAttackCooldownDuration() {
        return attackCooldownDuration;
    }

    public void setAttackCooldownDuration(int attackCooldownDuration) {
        this.attackCooldownDuration = attackCooldownDuration;
    }

    public int getAttackCooldown() {
        return attackCooldown;
    }

    public void setAttackCooldown(int attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Integer getIntegerId() {
        return getId();
    }

    public boolean isBase() {
        return getType().equals(TYPE_BASE);
    }

    public boolean isTank() {
        return getType().equals(TYPE_TANK);
    }

    public boolean isScout() {
        return getType().equals(TYPE_SCOUT);
    }

    public boolean isWorker() {
        return getType().equals(TYPE_WORKER);
    }

    public boolean isIdle() {
        return STATUS_IDLE.equals(getStatus());
    }

    public boolean isAlive() {
        return !STATUS_DEAD.equals(getStatus());
    }

    public boolean isCarryingResource() {
        return getResource() > 0;
    }
}
