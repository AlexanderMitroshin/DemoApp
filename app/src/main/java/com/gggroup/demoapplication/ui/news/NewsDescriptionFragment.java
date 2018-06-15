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
import com.squareup.picasso.Picasso;


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
        TextView coeff = root.findViewById(R.id.coeff);
        TextView shortDesc = root.findViewById(R.id.short_desc);
        TextView desc = root.findViewById(R.id.full_desc);
        ImageView preview = root.findViewById(R.id.preview);
        if (mNews != null) {
            Picasso.get().load(mNews.getPicture()).into(preview);
            shortDesc.setText(mNews.getShort_text());
            desc.setText(mNews.getFull_text());
            publicated.setText(mNews.getPublicated_at());
            coeff.setText("Coefficient " + mNews.getCoefficient());
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
