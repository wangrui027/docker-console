package com.nihaorz.docker.console.ws;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.ServletRequest;
import java.util.Map;

/**
 * websocket拦截器.
 * <p>主要用于获得传参，ip,containerId,width,height</p>
 *
 * @author will
 */
public class ContainerExecHandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        if (request.getHeaders().containsKey("Sec-WebSocket-Extensions")) {
            request.getHeaders().set("Sec-WebSocket-Extensions", "permessage-deflate");
        }
        ServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        String ip = servletRequest.getParameter("ip");
        Integer port = Integer.parseInt(servletRequest.getParameter("port"));
        String containerId = servletRequest.getParameter("containerId");
        String width = servletRequest.getParameter("width");
        String height = servletRequest.getParameter("height");
        attributes.put("ip", ip);
        attributes.put("port", port);
        attributes.put("containerId", containerId);
        attributes.put("width", width);
        attributes.put("height", height);
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }
}