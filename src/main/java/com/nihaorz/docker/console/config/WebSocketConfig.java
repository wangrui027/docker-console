package com.nihaorz.docker.console.config;

import com.nihaorz.docker.console.ws.ContainerExecHandshakeInterceptor;
import com.nihaorz.docker.console.ws.ContainerExecWSHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.annotation.Resource;

/**
 * 加载websocket.
 *
 * @author will
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    private ContainerExecWSHandler containerExecWSHandler;

    @Bean
    public ServerEndpointExporter serverEndpointExporter(ApplicationContext context) {
        return new ServerEndpointExporter();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(containerExecWSHandler, "/ws/container/exec").addInterceptors(new ContainerExecHandshakeInterceptor()).setAllowedOrigins("*");
    }
}