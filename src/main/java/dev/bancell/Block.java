package dev.bancell;

public class Block {
    private float velocity;
    private final float mass;
    private int x, y;
    private int height, width;

    public Block(float velocity, float mass, int x, int y, int height, int width) {
        this.velocity = velocity;
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public void updateVelocity(float velocity) {
        this.velocity = velocity;
    }

    public void updatePos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getVelocity() {
        return this.velocity;
    }

    public float getMass() {
        return this.mass;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

}
