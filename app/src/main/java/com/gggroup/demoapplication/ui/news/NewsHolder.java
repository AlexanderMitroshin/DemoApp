package com.gggroup.demoapplication.ui.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gggroup.demoapplication.R;
import com.gggroup.demoapplication.model.News;
import com.squareup.picasso.Picasso;


public class NewsHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private ImageView preview;

    public NewsHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        preview = itemView.findViewById(R.id.preview);
    }

    public void bind(News news) {
        title.setText(news.getTitle());
        Picasso.get().load(news.getPicture()).into(preview);
    }
}
