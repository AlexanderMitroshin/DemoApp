package com.gggroup.demoapplication.ui.news;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gggroup.demoapplication.R;
import com.gggroup.demoapplication.model.News;
import com.gggroup.demoapplication.tasks.GetNewsTask;

import java.util.List;


public class NewsFragment extends Fragment {

    public static final String CATEGORY = "category";

    public interface OnNewsClickListener {
        void onNewsClick(News news);
    }

    private String category;
    private GetNewsTask mGetNewsTask;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private NewsAdapter mNewsAdapter;
    private OnNewsClickListener mOnNewsClickListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            category = getArguments().getString(CATEGORY);
        }
        mNewsAdapter = new NewsAdapter();
        mOnNewsClickListener = (OnNewsClickListener) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        mRecyclerView = root.findViewById(R.id.news);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNewsAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemPosition = mRecyclerView.getChildLayoutPosition(v);
                News news = mNewsAdapter.getNews(itemPosition);
                mOnNewsClickListener.onNewsClick(news);
            }
        });
        mRecyclerView.setAdapter(mNewsAdapter);
        mSwipeRefreshLayout = root.findViewById(R.id.refresh);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        loadData();
        return root;
    }

    private void loadData() {
        Log.i("NewsFragment", "loadData");
        mGetNewsTask = new GetNewsTask();
        mGetNewsTask.setCallback(new GetNewsTask.Callback() {
            @Override
            public void onSuccess(List<News> news) {
                mNewsAdapter.updateNews(news);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError() {
                Snackbar.make(getView(), getString(R.string.loading_error), Snackbar.LENGTH_SHORT);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mGetNewsTask.execute(category);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mGetNewsTask.setCallback(null);
        mGetNewsTask.cancel(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnNewsClickListener = null;
    }
}
