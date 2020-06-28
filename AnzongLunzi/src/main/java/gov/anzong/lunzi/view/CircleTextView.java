package gov.anzong.lunzi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2016-12-07 11:48<br>
 * //   这玩意的用处：<br>
 */
public class CircleTextView extends TextView {
    PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    private Paint mBgPaint = new Paint();

    public CircleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mBgPaint.setColor(Color.WHITE);
        mBgPaint.setAntiAlias(true);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBgPaint.setColor(Color.WHITE);
        mBgPaint.setAntiAlias(true);
    }

    public CircleTextView(Context context) {
        super(context);
        mBgPaint.setColor(Color.WHITE);
        mBgPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int max = Math.max(measuredWidth, measuredHeight);
        setMeasuredDimension(max, max);
    }

    @Override
    public void setBackgroundColor(int color) {
        mBgPaint.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.setDrawFilter(pfd);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.max(getWidth(), getHeight()) / 2, mBgPaint);
        super.draw(canvas);
    }
}
