package com.jalurkhusus.jkonbarding;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JkOnBoarding {

    private Context context;
    private ArrayList<OnBoard> items;
    private RecyclerView recyclerView;
    private ArrayList<Integer> colorList;

    public JkOnBoarding(Context context, ArrayList<OnBoard> items, RecyclerView recyclerView,
                        ArrayList<Integer> colorList) {
        this.context = context;
        this.items = items;
        this.recyclerView = recyclerView;
        this.colorList = colorList;
    }

    public void injectView() {
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        LinearLayoutManager lm = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(lm);
        PagerSnapHelper helper = new PagerSnapHelper();
        helper.attachToRecyclerView(recyclerView);
        recyclerView.addItemDecoration(new InDecoration());
        OnBoardingAdapter adapter = new OnBoardingAdapter(context, items);
        recyclerView.setAdapter(adapter);

        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView rv, int dx, int dy) {
                super.onScrolled(rv, dx, dy);

                int visibleItem = lm.getChildCount();
                int firstItem = lm.findFirstVisibleItemPosition();
                int lastItem = lm.findLastVisibleItemPosition();

                int pColor;
                int width = recyclerView.getWidth();
                if (visibleItem > 1){
                    float offset;
                    if (firstItem == 0){
                        offset = rv.computeHorizontalScrollOffset() / (float) width;
                    } else {
                        offset = (rv.computeHorizontalScrollOffset() - (width * firstItem)) / (float) width;
                    }

                    pColor = (int) argbEvaluator.evaluate(offset,
                            colorList.get(firstItem % colorList.size()),
                            colorList.get(lastItem % colorList.size()));
                } else {
                    pColor = colorList.get(firstItem % colorList.size());
                }
                rv.setBackgroundColor(pColor);
            }
        });
    }
}
