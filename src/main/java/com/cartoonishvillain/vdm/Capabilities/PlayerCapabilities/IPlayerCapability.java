package com.cartoonishvillain.vdm.Capabilities.PlayerCapabilities;

public interface IPlayerCapability {
    boolean getBlackEyeStatus();
    float getKineticBuildup();
    void setBlackEyeStatus(boolean set);
    void setKineticBuildup(float damage);
}
