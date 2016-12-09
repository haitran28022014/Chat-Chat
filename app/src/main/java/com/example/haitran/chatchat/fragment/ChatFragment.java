package com.example.haitran.chatchat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.haitran.chatchat.App;
import com.example.haitran.chatchat.R;
import com.example.haitran.chatchat.adapter.ChatAdapter;
import com.example.haitran.chatchat.manager.ChatManager;
import com.example.haitran.chatchat.manager.StringManager;
import com.example.haitran.chatchat.model.ChatContentRec;
import com.example.haitran.chatchat.model.ChatContentSend;
import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hai Tran on 9/26/2016.
 */

public class ChatFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "ChatFragment";

    private RecyclerView listChat;
    private ImageView imgSend;
    private EditText edtContext;
    private View view;
    private String phoneUser;
    private ChatAdapter chatAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        initializeComponent();
        listenerChat();
        return view;
    }

    private void initializeComponent() {
        listChat = (RecyclerView) view.findViewById(R.id.list_chat);
        imgSend = (ImageView) view.findViewById(R.id.img_send);
        edtContext = (EditText) view.findViewById(R.id.edt_input_chat);
        imgSend.setOnClickListener(this);
        if (getArguments() != null) {
            phoneUser = getArguments().getString(StringManager.PHONE);
            Log.d(TAG, phoneUser);
        } else {
            Log.d(TAG, StringManager.FALSE);
        }
        listChat.setLayoutManager(new LinearLayoutManager(App.getContext()));
        chatAdapter = new ChatAdapter(ChatManager.getInstance().getItems());
        listChat.setAdapter(chatAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_send: {
                sendContextChatToServer();
                break;
            }
            default: {
                break;
            }
        }
    }

    /**
     * Đây là phương thức gửi nội dung tin nhắn và phone,thời gian gửi.Em sẽ đóng gói 3 dữ liệu trên vào đối tượng JSONObject
     * Khi Người dùng ấn gửi toàn bộ sẽ được gửi đi(phone,time,content) lên server thông qua câu lệnh
     *   App.getSocket().emit(StringManager.INFO_CHAT,jsonObject);
     *   Server sẽ xử lý tiếp
     */

    private void sendContextChatToServer() {
        String content = edtContext.getText().toString();
        String time=getTime();
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put(StringManager.KEY_PHONE, phoneUser);
            jsonObject.put(StringManager.KEY_CONTENT,content);
            jsonObject.put(StringManager.KEY_TIME,time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (content.equals("") || content.length() > 1000) {
            edtContext.requestFocus();
        } else {
            ChatManager.getInstance().getItems().add(new ChatContentSend(content,"hai",getTime()));
            chatAdapter.notifyDataSetChanged();
            App.getSocket().emit(StringManager.INFO_CHAT,jsonObject);
        }
        edtContext.setText("");
    }

    /**
     * Lấy ra thời gian của hệ thống để gửi đi
     * @return
     */

    private String getTime(){
        long currentTime=System.currentTimeMillis();
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
        String dateConverted[]=dateFormat.format(new Date(currentTime)).split(":");
        return dateConverted[0]+":"+dateConverted[1];
    }


    private void listenerChat() {
        App.getSocket().on(StringManager.RECEIVE,onRecChat);
    }


    /**
     * Đây là phương thức nhận tin nhắn khi bên server và cập nhật vào list tin nhắn gửi lại cho mình
     */
    private Emitter.Listener onRecChat=new Emitter.Listener() {
        @Override
        public void call(Object... args) {
           JSONObject object= (JSONObject)  args[0];
            try {
                String phone= object.getString(StringManager.KEY_PHONE);
                String content=object.getString(StringManager.KEY_CONTENT);
                String time=object.getString(StringManager.KEY_TIME);
                Log.d(TAG,content);
                if (phoneUser.equals(phone)){
                    return;
                }else {
                    ChatManager.getInstance().getItems().add(new ChatContentRec(content,"hai",time));
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chatAdapter.notifyDataSetChanged();
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
