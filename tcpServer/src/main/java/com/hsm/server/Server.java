package com.hsm.server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hsm.controllers.StudentController;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Server {
    public ServerSocket socket;
    public Scanner scanner;
    private final static Logger logger = Logger.getLogger(Server.class.getName());

    public Server(String ipAddress) throws IOException {
        //initializing port
        final int port = 8078;
        if(ipAddress!=null){
            this.socket = new ServerSocket(port, 1, InetAddress.getByName(ipAddress));
        }else{
            this.socket=new ServerSocket(port,1,InetAddress.getLocalHost());

        }
        //initializing scanner to get input
        this.scanner= new Scanner(System.in);

    }

    private void listen() throws IOException {
        String data = null;
        Socket client = this.socket.accept();
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from " + clientAddress);
        DataInputStream dataInputStream=new DataInputStream(client.getInputStream());
        BufferedReader in = new BufferedReader(
                new InputStreamReader(dataInputStream));

        //getjson data
        while ( (data = in.readLine()) != null ) {
            JsonParser jsonParser = new JsonParser();
            JsonObject studentJson = jsonParser.parse(data).getAsJsonObject();
            try {
                if(StudentController.addStudent(studentJson)){
                    System.out.println("added");
                }else{
                    System.out.println("try again");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("server obj from"+studentJson.toString());        }
    }

    public static void main(String args[]) throws IOException {
        Server app=new Server("");
        logger.info("\r\nServer running ...: " +
                "Host=" + app.socket.getInetAddress().getHostAddress() + //get host that running
                " Port=" + app.socket.getLocalPort());//get port

        app.listen();

    }
}
