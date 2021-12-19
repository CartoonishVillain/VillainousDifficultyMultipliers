package com.cartoonishvillain.vdm.capabilities.entitycapabilities;

public interface IEntityCapability {
    boolean getRetaliationStatus();
    void setRetaliationStatus(boolean set);
    int getAge();
    void setAge(int age);
    boolean getWrongStatus();
    void setWrongStatus(boolean set);
}
