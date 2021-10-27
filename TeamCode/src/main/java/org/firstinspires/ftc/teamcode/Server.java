package org.firstinspires.ftc.teamcode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

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
        while (true){
            try {
                robot.addDirection(inputStream.readInt());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
