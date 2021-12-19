package com.cartoonishvillain.vdm.capabilities.worldcapabilities;

public interface IWorldCapability {
    boolean getCelebrationStatus();
    void setCelebrationStatus(boolean status);
    boolean isNight();
    void setisNight(boolean status);
}
