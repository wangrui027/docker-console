package com.nihaorz.docker.console.action;

import com.spotify.docker.client.DefaultDockerClient;
import com.spotify.docker.client.DockerClient;
import com.spotify.docker.client.exceptions.DockerException;
import com.spotify.docker.client.messages.Container;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class RestAction {

    @GetMapping("/listContainers")
    public List<Container> listContainers(String ip, Integer port) throws DockerException, InterruptedException {
        DockerClient docker = DefaultDockerClient.builder()
                .uri(URI.create("http://" + ip + ":" + port))
                .build();
        List<Container> containers = docker.listContainers();
        return containers;
    }

}
