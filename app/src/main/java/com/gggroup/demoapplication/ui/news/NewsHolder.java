package com.gggroup.demoapplication.ui.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gggroup.demoapplication.R;
import com.gggroup.demoapplication.model.News;


public class NewsHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView shortDesc;

    public NewsHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        shortDesc = itemView.findViewById(R.id.short_desc);
    }

    public void bind(News news) {
        title.setText(news.getTitle());
        shortDesc.setText(news.getShort_text());
    }
}
