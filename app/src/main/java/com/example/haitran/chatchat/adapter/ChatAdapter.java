package com.example.haitran.chatchat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haitran.chatchat.R;
import com.example.haitran.chatchat.holder.ViewHolderReceive;
import com.example.haitran.chatchat.holder.ViewHolderSend;
import com.example.haitran.chatchat.model.ChatContentRec;
import com.example.haitran.chatchat.model.ChatContentSend;

import java.util.List;

/**
 * Created by Hai Tran on 9/28/2016.
 */

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int RECEIVE_VIEW = 0;
    private static final int SEND_VIEW = 1;
    private List<Object> items;

    /**
     * Class ChatAdapter giúp em chuyển dữ liệu từ file layout.xml lên cái list dạng tin nhắn.
     * Đầu tiên em xây dựng 2 đối tượng là Người gửi và người nhận như ở package model
     * Ngươi gửi và Người nhận đó sẽ có thuộc tính số phone, nôi dung tin nhắn,giờ gửi
     * Nói chung đây là Class em xử lý ra list tin nhắn người gửi ở bên trái tin nhắn người nhận ở bên phải dựa vào 3 class ở package holder
     * để tương tác ánh xa id của package layout đổ lên list.
     * Mỗi một đối tượng gửi nhận em cũng phải xây dựng giao diện riêng như ở package layout
     * @param items
     */
    public ChatAdapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case RECEIVE_VIEW:
                View rec = inflater.inflate(R.layout.item_chat_rec, parent, false);
                viewHolder = new ViewHolderReceive(rec);
                break;
            case SEND_VIEW:
                View send = inflater.inflate(R.layout.item_chat_send, parent, false);
                viewHolder = new ViewHolderSend(send);
                break;
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof ChatContentRec) {
            return RECEIVE_VIEW;
        } else if (items.get(position) instanceof ChatContentSend) {
            return SEND_VIEW;
        }
        return -1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case RECEIVE_VIEW:
                ChatContentRec chatContentRec=(ChatContentRec)items.get(position);
                ViewHolderReceive holderReceive = (ViewHolderReceive) holder;
                holderReceive.getContentRec().setText(chatContentRec.getContentChatRec());
                holderReceive.getTxtTimeRec().setText(chatContentRec.getTimeRec());
                break;
            case SEND_VIEW:
                ChatContentSend chatContentSend=(ChatContentSend)items.get(position);
                ViewHolderSend holderSend = (ViewHolderSend) holder;
                holderSend.getContentSend().setText(chatContentSend.getContentChatSend());
                holderSend.getTxtTimeSend().setText(chatContentSend.getTimeSend());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
