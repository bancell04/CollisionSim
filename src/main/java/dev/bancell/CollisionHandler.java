package dev.bancell;

public class CollisionHandler {
    public void handleCollision(Block b1, Block b2) {
        float nV1 = ((b1.getMass() - b2.getMass())*b1.getVelocity() + 2*b2.getMass()*b2.getVelocity()) / (b1.getMass() + b2.getMass());
        float nV2 = ((b2.getMass() - b1.getMass())*b2.getVelocity() + 2*b1.getMass()*b1.getVelocity()) / (b1.getMass() + b2.getMass());
        b1.updateVelocity(nV1);
        b2.updateVelocity(nV2);
    }
}
