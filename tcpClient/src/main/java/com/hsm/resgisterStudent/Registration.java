package com.hsm.resgisterStudent;

import com.google.gson.JsonObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Registration {
    private Socket socket;
    public Scanner scanner;
    private final static Logger logger= LogManager.getLogger(Registration.class);


    public Registration(InetAddress serverAddress, int serverPort) throws IOException {
        //creating new socket to server
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner= new Scanner(System.in);
    }

    private void start(){
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
            DataOutputStream dataOutputStream= null;
            try {
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                logger.fatal("Caught IOException", e);
            }
            PrintWriter out = new PrintWriter(dataOutputStream);

            //print in server
            out.println(student);
            out.flush();
            //get msg from server
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                System.out.println("Server says :"+in.readLine());
            } catch (IOException e) {
                logger.fatal("Caught IOException", e);

            }
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
