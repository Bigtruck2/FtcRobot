package org.firstinspires.ftc.teamcode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;

//this is a server i coded a simple client to connect to this server
//join the bot network
public class Server implements Runnable {
    private ServerSocket serverSocket;
    private final Robot robot;
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    public Server(Robot robot){
        this.robot = robot;
    }
    @Override
    public void run() {
        try {
            InetAddress locIP = InetAddress.getByName("0.0.0.0");
            serverSocket = new ServerSocket(25577, 0, locIP);
            socket = serverSocket.accept();
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        robot.setRunning(true);
        while (robot.isRunning()){
            try {
                byte[] bytes = new byte[3];
                int i = inputStream.read(bytes);
                int num1 = bytes[0];
                int num2 = bytes[1];
                if(bytes[0]<0){
                    num1+=256;
                }
                if(bytes[1]<0){
                    num2+=256;
                }
                double value = (num1*256+num2)/128D;
                if(bytes[2]<0){
                    bytes[2]+=128;
                    value*=-1;
                }
                switch (bytes[2]){
                    case 0:
                        robot.setDirection(value);
                        break;
                    case 1:
                        robot.addDirection(value);
                        break;
                    case 2:
                        robot.setBoth(value);
                        break;
                    case 3:
                        robot.setRight(value);
                        break;
                    case 4:
                        robot.setLeft(value);
                        break;
                    case 5:
                        robot.addTurnPower(value);
                        break;
                    case 6:
                        robot.setTurnPower(value);
                }
            } catch (IOException e) {
                try {
                    socket = serverSocket.accept();
                    inputStream = new DataInputStream(socket.getInputStream());
                    outputStream = new DataOutputStream(socket.getOutputStream());
                    continue;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                e.printStackTrace();
            }
        }

    }
}
