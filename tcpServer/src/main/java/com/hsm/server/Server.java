package com.hsm.server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hsm.controllers.StudentController;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

public class Server {
    public ServerSocket socket;
    public Scanner scanner;

    @Autowired
    public StudentController studentController;
    private final static Logger logger= LogManager.getLogger(Server.class);
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
        logger.info("\r\nNew connection from " + clientAddress);
        DataInputStream dataInputStream=new DataInputStream(client.getInputStream());
        DataOutputStream outputStream=new DataOutputStream((client.getOutputStream()));
        PrintWriter out = new PrintWriter(outputStream);
        BufferedReader in = new BufferedReader(new InputStreamReader(dataInputStream));
        //getjson data
        while ( (data = in.readLine()) != null ) {
            JsonParser jsonParser = new JsonParser();
            JsonObject studentJson = jsonParser.parse(data).getAsJsonObject();

            try {
                boolean test=studentController.addStudent(studentJson);
                System.out.println(test);
                if(test){
                    //print in server
                    out.println("--------------------student added successfully  ---------------------");
                    out.flush();
                }else{
                    out.println("--------------------oops something wrong! ---------------------");
                    out.flush();                }
            } catch (SQLException e) {
                logger.error(e);

            } catch (ClassNotFoundException e) {
                logger.trace(e);

            }
        }
    }

    public static void main(String args[]) throws IOException {

        Server app=new Server("");
        logger.info("\r\nServer running ...: " +
                "Host=" + app.socket.getInetAddress().getHostAddress() + //get host that running
                " Port=" + app.socket.getLocalPort());//get port

        app.listen();



    }

}
