package huskylens.java;
import java.lang.Thread;
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

    }

    public HuskyLensLibrary(int channel, int address){
        this.protocol = ProtocolEnum.I2C.value;
        this.channel = channel;
        this.address = address;
    }


    public boolean getProtocol(){
        return this.protocol;
    }
}
