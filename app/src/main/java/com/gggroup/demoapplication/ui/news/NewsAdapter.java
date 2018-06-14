package com.gggroup.demoapplication.ui.news;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.gggroup.demoapplication.R;
import com.gggroup.demoapplication.model.News;

import java.util.Collections;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsHolder> {

    private View.OnClickListener mOnClickListener;

    private List<News> data = Collections.emptyList();

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewsHolder newsHolder = new NewsHolder(View.inflate(parent.getContext(), R.layout.holder_news, null));
        newsHolder.itemView.setOnClickListener(mOnClickListener);
        return newsHolder;
    }

    @Override
    public void onBindViewHolder(NewsHolder newsHolder, int position) {
        newsHolder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateNews(List<News> news){
        this.data = news;
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public News getNews(int pos){
        return data.get(pos);
    }

}
