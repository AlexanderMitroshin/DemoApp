package com.gggroup.demoapplication.ui.news;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gggroup.demoapplication.R;
import com.gggroup.demoapplication.model.News;
import com.nostra13.universalimageloader.core.ImageLoader;


public class NewsDescriptionFragment extends Fragment {

    public static final String NEWS_CONTENT = "news_content";

    private News mNews;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNews = getArguments().getParcelable(NEWS_CONTENT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news_desc, container, false);
        setRetainInstance(true);
        TextView publicated = root.findViewById(R.id.publicated);
        TextView desc = root.findViewById(R.id.full_desc);
        ImageView preview = root.findViewById(R.id.preview);
        if (mNews != null) {
            ImageLoader.getInstance().displayImage(mNews.getPicture(), preview);
            desc.setText(mNews.getFull_text());
            publicated.setText(mNews.getPublicated_at());
        }
        View back = root.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        TextView toolbarTitle = root.findViewById(R.id.title);
        toolbarTitle.setText(mNews.getTitle());
        return root;
    }
}
