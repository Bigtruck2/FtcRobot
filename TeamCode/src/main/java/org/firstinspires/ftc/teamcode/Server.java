package org.firstinspires.ftc.teamcode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
                String command = inputStream.readUTF();
                String nums = command.substring(command.indexOf(' '));
                double value = 0;
                for(int i=0;i<command.length();i++){
                    if(!Character.isDigit(nums.charAt(i))){
                        value=0;
                        break;
                    }
                    value = Double.parseDouble(nums);
                }

                if(command.toLowerCase().startsWith("setdirection")){
                    robot.setDirection(value);
                }else if(command.toLowerCase().startsWith("adddirection")){
                    robot.addDirection(value);
                }else if(command.toLowerCase().startsWith("setboth")){
                    robot.setBoth(value);
                }else if(command.toLowerCase().startsWith("setright")){
                    robot.setRight(value);
                }else if(command.toLowerCase().startsWith("setleft")){
                    robot.setLeft(value);
                }else if(command.toLowerCase().startsWith("addturnpower")){
                    robot.addTurnPower(value);
                }else if(command.toLowerCase().startsWith("setturnpower")){
                    robot.setTurnPower(value);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
