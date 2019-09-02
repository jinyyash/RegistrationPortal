package com.hsm.resgisterStudent;

import com.google.gson.JsonObject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

public class Registration {
    private Socket socket;
    public Scanner scanner;
    private final static Logger logger = Logger.getLogger("ClientApp.class");


    public Registration(InetAddress serverAddress, int serverPort) throws IOException {
        //creating new socket to server
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner= new Scanner(System.in);
    }

    private void start() throws IOException {
        String nic;
        String name;
        String address;
        String tel;
        while (true) {
            logger.info("Enter New Student details");
            logger.info("Enter Student National Identity card Number");
            nic = scanner.nextLine();
            logger.info("Enter Student Name");
            name = scanner.nextLine();
            logger.info("Enter Student Address");
            address = scanner.nextLine();
            logger.info("Enter Student Telephone Number");
            tel = scanner.nextLine();
            //creating student json object
            JsonObject student = new JsonObject();
            student.addProperty("nic", nic);
            student.addProperty("name", name);
            student.addProperty("address",address);
            student.addProperty("tel",tel);
            System.out.println(student.toString());
            DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
            PrintWriter out = new PrintWriter(dataOutputStream);
            //print in server
            out.println(student);
            out.flush();
        }

    }

    public static void main(String[] args) throws IOException {

       // Registration app=new Registration(InetAddress.getByName(args[0]), 8078);
        Registration  app=new Registration (InetAddress.getByName("127.0.0.1") ,8078);
        logger.info("Start of setUp");

        logger.info("\r\nConnected to Server: " + app.socket.getInetAddress());
        app.start();
    }
}
