package com.cartoonishvillain.vdm.capabilities.playercapabilities;

public interface IPlayerCapability {
    boolean getBlackEyeStatus();
    float getKineticBuildup();
    void setBlackEyeStatus(boolean set);
    void setKineticBuildup(float damage);
    void setShoutTicks(int ticks);
    int getShoutTicks();
}
