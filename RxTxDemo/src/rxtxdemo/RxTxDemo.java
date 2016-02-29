/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rxtxdemo;

/**
 *
 * @author pi
 */
import gnu.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RxTxDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchPortException, PortInUseException, UnsupportedCommOperationException, IOException {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("/dev/ttyUSB0"); //on unix based system
        SerialPort serialPort = (SerialPort) portIdentifier.open("NameOfConnection-whatever", 0);
        serialPort.setSerialPortParams(115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

        boolean active = true;
        InputStream inputStream = serialPort.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String s = br.readLine();
        while (s != null) {
            try {
                if (s.charAt(0) != '#') {
                    System.out.println(System.currentTimeMillis() + ": " + s  );
                }
                s = br.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
