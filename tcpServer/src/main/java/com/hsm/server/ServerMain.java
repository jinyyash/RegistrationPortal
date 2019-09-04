package com.hsm.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tanukisoftware.wrapper.WrapperListener;
import org.tanukisoftware.wrapper.WrapperManager;

import java.io.IOException;
import java.util.concurrent.Executors;
@SpringBootApplication
public class ServerMain implements WrapperListener {

    private Server serverApp;
    @Override
    public Integer start(final String[] strings) {
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Server.main(strings);
                        SpringApplication.run(ServerMain.class, strings);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });
        return null;
    }

    @Override
    public int stop(int i) {
        return 0;
    }

    @Override
    public void controlEvent(int i) {

    }

    public static void main(String[] args) {

        WrapperManager.start(new ServerMain(),args);

    }
}
