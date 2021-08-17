package com.cartoonishvillain.vdm.Capabilities.EntityCapabilities;

public interface IEntityCapability {
    boolean getRetaliationStatus();
    void setRetaliationStatus(boolean set);
    int getAge();
    void setAge(int age);
    boolean getWrong();
    void setWrong(boolean set);
}
