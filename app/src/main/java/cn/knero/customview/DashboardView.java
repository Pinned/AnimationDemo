package cn.knero.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by luozhaocheng on 6/28/15.
 */
public class DashboardView extends View {

    private Paint mBgPaint;

    private Paint mPointPaint;
    private Paint mPointCirclePaint;
    private Path mPointPath;
    private int mPointRadius = 150;
    private int mPointAngle = 90;

    public DashboardView(Context context) {
        super(context);
    }

    public DashboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DashboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.setMeasuredDimension(500, 400);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.drawBg(canvas);
        this.drawPoint(canvas);
    }

    private void drawPoint(Canvas canvas) {
        if (mPointPaint == null) {
            mPointPaint = new Paint();
            mPointPaint.setColor(Color.BLACK);
            mPointPaint.setAntiAlias(true);
            mPointPaint.setDither(true);
            mPointPaint.setStyle(Paint.Style.FILL);
        }

        if (mPointPath == null) {
            mPointPath = new Path();
        } else {
            mPointPath.reset();
        }
        float radius = 10;
        float pointCenterX = getWidth() / 2;
        float pointCenterY = getHeight() / 2;

        double sinAngle = Math.sin(mPointAngle * Math.PI / 180);
        double cosAngle = Math.cos(mPointAngle * Math.PI / 180);

        float x = (float) (pointCenterX + mPointRadius * cosAngle);
        float y = (float) (pointCenterY - mPointRadius * sinAngle);
        mPointPath.moveTo(x, y);

        float rx = (float) (radius * sinAngle);
        float ry = (float) (radius * cosAngle);

        mPointPath.lineTo(pointCenterX - rx, pointCenterY - ry);
        mPointPath.lineTo(pointCenterX, pointCenterY);
        final RectF oval = new RectF();
        oval.set(pointCenterX - radius, pointCenterY - radius, pointCenterX + radius, pointCenterY + radius);
        mPointPath.arcTo(oval, 90 - mPointAngle, 180);
        mPointPath.lineTo(pointCenterX + rx, pointCenterY + ry);
        mPointPath.lineTo(x, y);
        canvas.drawPath(mPointPath, mPointPaint);

        if (mPointCirclePaint == null) {
            mPointCirclePaint = new Paint();
        }
        mPointCirclePaint.setColor(0xFFF7F7F7);
        mPointCirclePaint.setAntiAlias(true);
        mPointCirclePaint.setDither(true);
        canvas.drawCircle(pointCenterX, pointCenterY, radius - 4, mPointCirclePaint);
    }

    private void drawBg(Canvas canvas) {
        if (mBgPaint == null) {
            mBgPaint = new Paint();
            mBgPaint.setColor(0xFFF7F7F7);
            mBgPaint.setAntiAlias(true);
            mBgPaint.setDither(true);
        }
        canvas.drawRect(0, 0, getWidth(), getHeight(), mBgPaint);
    }
}
