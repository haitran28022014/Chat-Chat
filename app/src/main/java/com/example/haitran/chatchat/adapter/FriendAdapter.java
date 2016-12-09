package com.example.haitran.chatchat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haitran.chatchat.App;
import com.example.haitran.chatchat.R;
import com.example.haitran.chatchat.holder.ViewHolderFriend;
import com.example.haitran.chatchat.manager.ChatManager;
import com.example.haitran.chatchat.model.Friend;

/**
 * Created by Hai Tran on 9/30/2016.
 */

public class FriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * Giúp em hiện thi danh sách bạn bè đang online khi người dùng đăng nhập vào em sẽ update dữ liệu là online
     * Sau đó sẽ đổ UserName ảnh đại diện và biểu tượng dang chat vào recyclerview
     * Vì không gửi cho thầy được cơ sở dữ liệu nên em phải add cững dữ liệu để hiện thị
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_chat_online, parent, false);
        ViewHolderFriend viewHolderFriend = new ViewHolderFriend(view);
        return viewHolderFriend;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Friend friend = ChatManager.getInstance().getArrFriend().get(position);
        ViewHolderFriend holderFriend = (ViewHolderFriend) holder;
        holderFriend.getCirAvatar().setImageResource(getImageId(friend.getImageAvatar()));
        holderFriend.getTxtUsername().setText(friend.getUserName());
        holderFriend.getImgOnl().setImageResource(getImageId(friend.getImageOnl()));
    }


    public int getImageId(String nameImage) {
        String pkgName = App.getContext().getPackageName();
        int resID = App.getContext().getResources().getIdentifier(nameImage, "drawable", pkgName);
        return resID;
    }



    @Override
    public int getItemCount() {
        return ChatManager.getInstance().countFriend();
    }
}
