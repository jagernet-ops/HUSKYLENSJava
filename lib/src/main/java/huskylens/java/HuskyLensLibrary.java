package huskylens.java;
import java.lang.Thread;
import java.lang.Byte;
import com.fazecast.jSerialComm.SerialPort;


public class HuskyLensLibrary {
    private static final String COMMAND_HEADER_AND_ADDRESS = "55AA11";
    private static final int DATABIT_LENGTH = 8;
    private static final int SERIAL_TIMEOUT = 500;
    private boolean protocol;
    private SerialPort comPort;
    private int baudRate;
    private int channel;
    private int address;
    
    public HuskyLensLibrary(ProtocolEnum protocol, SerialPort comPort, int baudRate, int channel, int address){
        this.protocol = protocol.value;
        this.comPort = comPort;
        this.baudRate = baudRate;
        this.channel = channel;
        this.address = address;

    }

    public HuskyLensLibrary(SerialPort comPort, int baudRate){
        this.protocol = ProtocolEnum.SERIALPORT.value;
        this.comPort = comPort;
        this.baudRate = baudRate;
        this.comPort.setComPortParameters(this.baudRate, this.DATABIT_LENGTH, comPort.ONE_STOP_BIT, comPort.NO_PARITY);
        this.comPort.setComPortTimeouts(SERIAL_TIMEOUT, SERIAL_TIMEOUT, SERIAL_TIMEOUT);
        this.comPort.clearDTR();
        this.comPort.clearRTS();
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            System.out.println(e);
        }
        this.comPort.openPort();
        try {
            Thread.sleep(200)
        }catch (Exception e) {
            System.out.println(e);
        }
        this.comPort.flushIOBuffers();
        this.comPort.flushDataListener();
    }

    public HuskyLensLibrary(int channel, int address){
        this.protocol = ProtocolEnum.I2C.value;
        this.channel = channel;
        this.address = address;
    }

    private byte commandToBytes(String command){
        return Byte.decode(command);
    }

    private void writeToHuskyLens(Byte command){
        if(this.protocol){
            this.comPort.flushIOBuffers();
            this.comPort.flushDataListener();
            this.comPort.writeBytes(this.comPort.getDeviceWriteBufferSize(), command.byteValue());
        }else{
            //TODO I2C WRITE
            System.out.println("Argle Bargle");
        }
    }

    public processHuskyLensData

    public void pingConnection(){

    }

    public int setI2CChannel(int channel){
        this.channel = channel;
    }

    public int setI2CAddress(int address) {
        this.address = address;
    }

    public void setBaudRate(int baudRate){
        this.baudRate = baudRate;
        this.comPort.setBaudRate(this.baudRate);
    }

    public void setComPort(SerialPort comPort, int baudRate){
        this.comPort = comPort;
        this.comPort.setBaudRate(baudRate);
    }

    public SerialPort getComPort(){
        return this.comPort;
    }

    public int getBaudRate(){
        return this.baudRate;
    }

    public int getI2CChannel(){
        return this.channel;
    }

    public int getI2CAddress(){
        return this.address;
    }
}
