package com.jalurkhusus.jkonbarding;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InDecoration extends RecyclerView.ItemDecoration {

    private int cActive = 0xDE000000;
    private int cInactive = 0x33000000;

    private static final float DP = Resources.getSystem().getDisplayMetrics().density;
    private final int mHeight = (int) (DP * 16);
    private final float mStrokeWidth = DP * 4;
    private final float mLength = DP * 4;
    private final float mPadding = DP * 8;
    private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();
    private final Paint mPaint = new Paint();

    public InDecoration(){
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        int itemCount = parent.getAdapter().getItemCount();
        float totalLength = mLength * itemCount;
        float paddingBetweenItems = Math.max(0, itemCount - 1) * mPadding;
        float indicatorTotalWidth = totalLength + paddingBetweenItems;
        float indicatorStartX = (parent.getWidth() - indicatorTotalWidth) / 2F;
        float indicatorPosY = parent.getHeight() - mHeight * 2F;

        drawInactive(c, indicatorStartX, indicatorPosY, itemCount);
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int activePosition = layoutManager.findFirstVisibleItemPosition();
        if (activePosition == RecyclerView.NO_POSITION) {
            return;
        }

        final View activeChild = layoutManager.findViewByPosition(activePosition);
        int left = activeChild.getLeft();
        int width = activeChild.getWidth();
        int right = activeChild.getRight();
        float progress = mInterpolator.getInterpolation(left * -1 / (float) width);

        drawHighlights(c, indicatorStartX, indicatorPosY, activePosition, progress);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = mHeight;
    }

    private void drawHighlights(Canvas c, float indicatorStartX, float indicatorPosY,
                                int activePosition, float progress) {
        mPaint.setColor(cActive);
        final float itemWidth = mLength + mPadding;
        float highlightStart = indicatorStartX + itemWidth * activePosition;
        if (progress == 0F) {
            c.drawCircle(highlightStart, indicatorPosY, mLength / 2F, mPaint);

        } else {
            float partialLength = mLength * progress + mPadding *progress;
            c.drawCircle(highlightStart + partialLength, indicatorPosY,
                    mLength / 2F, mPaint);
        }
    }

    private void drawInactive(Canvas c, float indicatorStartX, float indicatorPosY,
                              int itemCount) {
        mPaint.setColor(cInactive);
        final float itemWidth = mLength + mPadding;
        float start = indicatorStartX;
        for (int i = 0; i < itemCount; i++) {
            c.drawCircle(start, indicatorPosY, mLength / 2F, mPaint);
            start += itemWidth;
        }
    }
}
