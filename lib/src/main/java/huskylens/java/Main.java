package huskylens.java;
import com.fazecast.jSerialComm.SerialPort;

public class Main {
    public static HuskyLensLibrary hLibrary;
    public static void main(String[] args){
        SerialPort[] serialDevices = SerialPort.getCommPorts();
        for(int i = 0; i < serialDevices.length; i++){
            if(serialDevices[i].getDescriptivePortName().contains("CP210x")){
                hLibrary = new HuskyLensLibrary(serialDevices[i], 3000000);
                hLibrary.processHuskyLensData();
            }
        }
    }
}