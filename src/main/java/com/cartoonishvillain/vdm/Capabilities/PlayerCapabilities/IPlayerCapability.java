package com.cartoonishvillain.vdm.Capabilities.PlayerCapabilities;

public interface IPlayerCapability {
    boolean getBlackEyeStatus();
    float getKineticBuildup();
    void setBlackEyeStatus(boolean set);
    void setKineticBuildup(float damage);
    int getShoutTicks();
    void setShoutTicks(int ticks);
}
