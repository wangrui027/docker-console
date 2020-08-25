package com.nihaorz.docker.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.io.IOException;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class DockerConsoleApplication {

    public static void main(String[] args) {
        int port = 8080;
        String portPrefix = "--server.port=";
        for (String arg : args) {
            if (arg.startsWith(portPrefix)) {
                port = Integer.parseInt(arg.substring(portPrefix.length()));
            }
        }
        SpringApplication.run(DockerConsoleApplication.class, args);
        try {
            Runtime.getRuntime().exec("cmd /c start http://localhost:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
