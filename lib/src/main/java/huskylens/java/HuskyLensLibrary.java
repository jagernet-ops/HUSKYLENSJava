package huskylens.java;

import java.util.ArrayList;

import com.fazecast.jSerialComm.SerialPort;


public class HuskyLensLibrary {
    private final String COMMAND_HEADER_AND_ADDRESS = "55AA11";
    private final int DATABIT_LENGTH = 8;
    private final int SERIAL_TIMEOUT = 500;
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
        this.comPort.setComPortParameters(this.baudRate, this.DATABIT_LENGTH, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
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
            Thread.sleep(2000);
        }catch (Exception e) {
            System.out.println(e);
        }
        this.pingConnection();
        try {
            Thread.sleep(500);
        }catch (Exception e) {
            System.out.println(e);
        }
        this.pingConnection();
        try {
            Thread.sleep(500);
        }catch (Exception e) {
            System.out.println(e);
        }
        this.pingConnection();
        this.comPort.flushIOBuffers();
        this.comPort.flushDataListener();
    }

    public HuskyLensLibrary(int channel, int address){
        this.protocol = ProtocolEnum.I2C.value;
        this.channel = channel;
        this.address = address;
    }

    private byte[] commandToBytes(String command){
        return command.getBytes();
    }

    private void writeToHuskyLens(byte[] command){
        this.comPort.flushIOBuffers();
        this.comPort.flushDataListener();
        this.comPort.writeBytes(command, command.length);
    }

    private byte[] getDataHeaders(byte[] byteArray){
        byte[] dataHeaders = new byte[5];
        return dataHeaders;
    }

    public void processHuskyLensData() {
        ArrayList<Byte> rtn = new ArrayList<Byte>();
        
        readBytesToArrayList(rtn, 5);
        readBytesToArrayList(rtn, rtn.get(3).intValue() + 1);

        for(byte index : rtn){
            System.out.println(index);
        }
    }

    private void readBytesToArrayList(ArrayList<Byte> arr, int bytesToRead) {
        byte[] buff = new byte[bytesToRead];

        this.comPort.readBytes(buff, bytesToRead);

        for (byte b : buff) {
            arr.add(b);
        }
    } 

    public void pingConnection(){
        byte[] command = this.commandToBytes(COMMAND_HEADER_AND_ADDRESS+"002c3c");
        this.writeToHuskyLens(command);
    }
}
