package com.example.haitran.chatchat.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.haitran.chatchat.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Hai Tran on 9/29/2016.
 */

public class ViewHolderSend extends RecyclerView.ViewHolder {
    private CircleImageView imageSend;
    private TextView contentSend;
    private TextView txtTimeSend;

    public ViewHolderSend(View itemView) {
        super(itemView);
        imageSend = (CircleImageView) itemView.findViewById(R.id.img_send);
        contentSend = (TextView) itemView.findViewById(R.id.txt_content_send);
        txtTimeSend=(TextView)itemView.findViewById(R.id.txt_time_send);
    }

    public CircleImageView getImageSend() {
        return imageSend;
    }

    public TextView getContentSend() {
        return contentSend;
    }

    public TextView getTxtTimeSend() {
        return txtTimeSend;
    }
}
