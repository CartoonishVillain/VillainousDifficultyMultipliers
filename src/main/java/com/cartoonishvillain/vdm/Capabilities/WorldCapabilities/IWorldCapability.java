package com.cartoonishvillain.vdm.Capabilities.WorldCapabilities;

public interface IWorldCapability {
    boolean getCelebrationStatus();
    void setCelebrationStatus(boolean status);
    boolean isNight();
    void setisNight(boolean status);
}
