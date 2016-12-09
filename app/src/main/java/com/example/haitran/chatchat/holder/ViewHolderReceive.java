package com.example.haitran.chatchat.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.haitran.chatchat.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Hai Tran on 9/29/2016.
 */

public class ViewHolderReceive extends RecyclerView.ViewHolder {
    private CircleImageView imageRec;
    private TextView contentRec;
    private TextView txtTimeRec;

    public ViewHolderReceive(View itemView) {
        super(itemView);
        imageRec = (CircleImageView) itemView.findViewById(R.id.img_rec);
        contentRec = (TextView) itemView.findViewById(R.id.txt_content_rec);
        txtTimeRec=(TextView)itemView.findViewById(R.id.txt_time_rec);
    }

    public CircleImageView getImageRec() {
        return imageRec;
    }

    public TextView getContentRec() {
        return contentRec;
    }

    public TextView getTxtTimeRec(){
        return txtTimeRec;
    }
}
