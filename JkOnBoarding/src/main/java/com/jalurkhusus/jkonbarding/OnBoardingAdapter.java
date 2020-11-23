package com.jalurkhusus.jkonbarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OnBoardingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<OnBoard> mData;
    private LayoutInflater mInflater;
    private final static int Layout_Type_A = 0;
    private final static int Layout_Type_B = 1;

    public OnBoardingAdapter(Context context, ArrayList<OnBoard> items) {
        mContext = context;
        mData = items;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == Layout_Type_A){
            view = mInflater.inflate(R.layout.item_onboarding, parent, false);
            return new OnBoard_A(view);
        } else if (viewType == Layout_Type_B){
            view = mInflater.inflate(R.layout.item_onboarding, parent, false);
            return new OnBoard_B(view);
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        return Layout_Type_A;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OnBoard onBoard = mData.get(position);
        int drawable = onBoard.getDrawable();
        String title = onBoard.getTitle();
        String body = onBoard.getBody();

        if (holder instanceof OnBoard_A){
            ((OnBoard_A) holder).ivImage.setImageResource(drawable);
            ((OnBoard_A) holder).tvTitle.setText(title);
            ((OnBoard_A) holder).tvBody.setText(body);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class OnBoard_A extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvTitle, tvBody;
        public OnBoard_A(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvBody = itemView.findViewById(R.id.tvBody);
        }
    }

    public static class OnBoard_B extends RecyclerView.ViewHolder {

        public OnBoard_B(@NonNull View itemView) {
            super(itemView);
        }
    }
}
