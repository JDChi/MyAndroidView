package com.ugiant.myandroidview.model;

/**
 * 叶子对象，用于记录叶子的主要数据
 */
public class Leaf {
    private float x;
    private float y;
    /**
     * 旋转角度
     */
    private int rotateAngle;

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setRotateAngle(int rotateAngle) {
        this.rotateAngle = rotateAngle;
    }

    public void setRotateDirection(int rotateDirection) {
        this.rotateDirection = rotateDirection;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setStartType(StartType startType) {
        this.startType = startType;
    }

    /**
     * 选择方向 0 顺时针 1 逆时针
     */
    private int rotateDirection;
    /**
     * 起始时间
     */
    private long startTime;
    /**
     * 叶子的飘动幅度
     */
    private StartType startType;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getRotateAngle() {
        return rotateAngle;
    }

    public int getRotateDirection() {
        return rotateDirection;
    }

    public long getStartTime() {
        return startTime;
    }

    public StartType getStartType() {
        return startType;
    }
}
