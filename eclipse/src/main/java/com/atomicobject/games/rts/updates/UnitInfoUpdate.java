package com.atomicobject.games.rts.updates;

public class UnitInfoUpdate {
    private int hp;
    private int range;
    private int cost;
    private int speed;
    private int attackDamage;
    private String attackType;
    private int attackCooldownDuration;
    private boolean canCarry;
    private int createTime;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String attackType) {
        this.attackType = attackType;
    }

    public int getAttackCooldownDuration() {
        return attackCooldownDuration;
    }

    public void setAttackCooldownDuration(int attackCooldownDuration) {
        this.attackCooldownDuration = attackCooldownDuration;
    }

    public boolean isCanCarry() {
        return canCarry;
    }

    public void setCanCarry(boolean canCarry) {
        this.canCarry = canCarry;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }
}