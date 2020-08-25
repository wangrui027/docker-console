package com.nihaorz.docker.console.ws;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.InputStream;
import java.util.Date;

/**
 * 输出线程.
 * <p>使用线程输出，方式流等待卡住</p>
 *
 * @author will
 */
public class OutPutThread extends Thread {
    private InputStream inputStream;
    private WebSocketSession session;

    public OutPutThread(InputStream inputStream, WebSocketSession session) {
        super("OutPut" + new Date().getTime());
        this.session = session;
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        try {
            byte[] bytes = new byte[1024];
            while (!this.isInterrupted()) {
                int n = inputStream.read(bytes);
                String msg = new String(bytes, 0, n);
                session.sendMessage(new TextMessage(msg));
                bytes = new byte[1024];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
