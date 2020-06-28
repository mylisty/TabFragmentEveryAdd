package gov.anzong.lunzi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import gov.anzong.lunzi.R;
import gov.anzong.lunzi.util.AnzongDensityUtils;

/**
 * <br>
 * //   When I wrote this, only God and I understood what I was doing<br>
 * //   Now, God only knows<br>
 * //   作者： Anh Lai/竹井詩織里<br>
 * //   邮箱：ymback@sayyoulove.me<br>
 * //   创建时间：2017-07-06 15:33<br>
 * //   这玩意的用处：<br>
 */
public class CircleView extends View {
    private int color;
    private Paint paint;

    public CircleView(Context context) {
        this(context, null);
    }


    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.paint = new Paint();
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int verticalCenter = getHeight() / 2;
        int horizontalCenter = getWidth() / 2;
        int circleRadius = verticalCenter - AnzongDensityUtils.dp2px(getContext(), 1F);
        paint.setAntiAlias(true); //消除锯齿
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawCircle(horizontalCenter, verticalCenter, circleRadius, paint);
        paint.setAntiAlias(true); //消除锯齿
        paint.setStyle(Paint.Style.STROKE);
        if (color == getResources().getColor(android.R.color.white)) {
            paint.setColor(getResources().getColor(R.color.dddddd));
        }
        circleRadius = verticalCenter - AnzongDensityUtils.dp2px(getContext(), 1F);
        paint.setStrokeWidth(AnzongDensityUtils.dp2px(getContext(), 1F));
        canvas.drawCircle(horizontalCenter, verticalCenter, circleRadius, paint);
    }
}
