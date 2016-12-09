package com.example.haitran.chatchat;

import android.app.Application;
import android.content.Context;

import com.example.haitran.chatchat.manager.StringManager;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

/**
 * Created by Hai Tran on 9/27/2016.
 */

public class App extends Application {
    private static Context context;
    private static Socket socket;

    /**
     * Vì cả ứng dụng phải tương tác với server nên hàm kết nối nối socket đến server em để ở App kế thừa Application
     * StringManager.LOCAL_HOST=192.168.56.1:3000
     */
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        try {
            socket = IO.socket(StringManager.LOCAL_HOST);
            socket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static Context getContext() {
        return context;
    }

    public static Socket getSocket() {
        return socket;
    }
}
