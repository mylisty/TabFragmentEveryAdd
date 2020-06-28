package gov.anzong.lunzi.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import gov.anzong.lunzi.R;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2017-07-03 21:21<br>
 * //   这玩意的用处：<br>
 */
public class DashedLineView extends View {
    private Paint mPaint;
    private Path mPath;
    private int color;

    public DashedLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.DashedLineView);
        this.color = mTypedArray.getColor(R.styleable.DashedLineView_color, 0xFFFFFFFF);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        // 需要加上这句，否则画不出东西
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mTypedArray.getDimensionPixelSize(R.styleable.DashedLineView_height, 4));
        mPaint.setPathEffect(new DashPathEffect(
                new float[]{mTypedArray.getDimensionPixelSize(R.styleable.DashedLineView_width, 20),
                        mTypedArray.getDimensionPixelSize(R.styleable.DashedLineView_divider_width, 20)}, 0)
        );

        mTypedArray.recycle();
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int centerY = getHeight() / 2;
        mPath.reset();
        mPath.moveTo(0, centerY);
        mPath.lineTo(getWidth(), centerY);
        canvas.drawPath(mPath, mPaint);
    }
}