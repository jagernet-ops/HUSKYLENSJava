package huskylens.java;

public enum ProtocolEnum {
    I2C(false),
    SERIALPORT(true);
    public final boolean value;
    private ProtocolEnum(boolean value){
        this.value = value;
    }
}
