package com.example.haitran.chatchat.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.haitran.chatchat.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Hai Tran on 9/30/2016.
 */

public class ViewHolderFriend extends RecyclerView.ViewHolder {
    private CircleImageView cirAvatar;
    private TextView txtUsername;
    private ImageView imgOnl;

    public ViewHolderFriend(View itemView) {
        super(itemView);
        cirAvatar=(CircleImageView)itemView.findViewById(R.id.cir_avatar);
        txtUsername=(TextView)itemView.findViewById(R.id.txt_username);
        imgOnl=(ImageView)itemView.findViewById(R.id.img_onl);
    }

    public CircleImageView getCirAvatar() {
        return cirAvatar;
    }

    public TextView getTxtUsername() {
        return txtUsername;
    }

    public ImageView getImgOnl() {
        return imgOnl;
    }
}
