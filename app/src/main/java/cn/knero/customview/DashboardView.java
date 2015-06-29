package cn.knero.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
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
    private int mPointAngle = 235;

    private Paint mCirclePaint;

    private Paint mLinearCirclePaint;
    private int mSplit = -1;

    private Paint mTextLinePaint;
    private Paint mTextPaint;

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
        this.setMeasuredDimension(600, 600);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.drawBg(canvas);
        this.drawPoint(canvas);
        this.drawCircle(canvas);
        this.drawLinearCircle(canvas);
        this.drawTextAndLine(canvas);
        mPointAngle += mSplit;
        if (mPointAngle < -45) {
            mSplit = 1;
        } else if (mPointAngle > 225) {
            mSplit = -1;
        }
        postInvalidateDelayed(30);
    }

    private void drawTextAndLine(Canvas canvas) {

        if (mTextPaint == null) {
            mTextPaint = new Paint();
            mTextPaint.setAntiAlias(true);
            mTextPaint.setDither(true);
            mTextPaint.setStrokeWidth(2);
            mTextPaint.setColor(0xFFC3C8CA);
            mTextPaint.setTextSize(20);
        }
        if (mTextLinePaint == null) {
            mTextLinePaint = new Paint();
            mTextLinePaint.setAntiAlias(true);
            mTextLinePaint.setDither(true);
            mTextLinePaint.setStrokeWidth(2);
            mTextLinePaint.setColor(0xFFE4E4E4);
        }
        drawLine(canvas, -45, "");
        drawLine(canvas, -25, "10M");
        drawLine(canvas, 5, "5M");
        drawLine(canvas, 45, "1M");
        drawLine(canvas, 90, "512K");
        drawLine(canvas, 135, "256K");
        drawLine(canvas, 180, "128K");
        drawLine(canvas, 225, "0K");
    }

    private void drawLine(Canvas canvas, int angle, String text) {
        double sinx = Math.sin(angle * Math.PI / 180);
        double cosx = Math.cos(angle * Math.PI / 180);
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        float circleRadius = mPointRadius + 40;
        float longCircleRadius = circleRadius + 15;
        canvas.drawLine((float) (centerX + circleRadius * cosx),
                (float) (centerY - circleRadius * sinx),
                (float) (centerX + longCircleRadius * cosx),
                (float) (centerY - longCircleRadius * sinx), mTextLinePaint);

        Rect bounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), bounds);
        float startX;
        float startY;
        float textRadius = longCircleRadius + 5;
        if (angle == 90) {
            startX = (float) (centerX + textRadius * cosx) - bounds.width() / 2;
        } else if (cosx / Math.abs(cosx) == 1) {
            startX = (float) (centerX + textRadius * cosx);
        } else {
            startX = (float) (centerX + textRadius * cosx) - bounds.width();
        }

        if (angle == 90) {
            startY = (float) (centerY - textRadius * sinx) - bounds.height() / 2;
        } else if (angle == 0 || sinx / Math.abs(sinx) == 1) {
            startY = (float) (centerY - textRadius * sinx + bounds.height() / 2);
        } else {
            startY = (float) (centerY - textRadius * sinx) + bounds.height();
        }
        canvas.drawText(text, 0, text.length(), startX, startY, mTextPaint);
    }


    private void drawLinearCircle(Canvas canvas) {
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        float circleRadius = mPointRadius + 20;
        if (mLinearCirclePaint == null) {
            mLinearCirclePaint = new Paint();
            mLinearCirclePaint.setAntiAlias(true);
            mLinearCirclePaint.setDither(true);
            mLinearCirclePaint.setStrokeWidth(20);
            mLinearCirclePaint.setStyle(Paint.Style.STROKE);
            int[] colors = new int[]{
                    0xFF88D845,
                    0xFFE76F38
            };
            LinearGradient linearGradient = new LinearGradient(centerX - circleRadius - 20, centerY,
                    centerX + circleRadius + 20, centerY, colors, null, Shader.TileMode.CLAMP);
            mLinearCirclePaint.setShader(linearGradient);
        }
        final RectF oval = new RectF();
        oval.set(centerX - circleRadius, centerY - circleRadius, centerX + circleRadius, centerY + circleRadius);
        canvas.drawArc(oval, 135, 270, false, mLinearCirclePaint);
    }

    private void drawCircle(Canvas canvas) {
        if (mCirclePaint == null) {
            mCirclePaint = new Paint();
            mCirclePaint.setColor(0xFFCCCCCC);
            mCirclePaint.setDither(true);
            mCirclePaint.setAntiAlias(true);
            mCirclePaint.setStrokeWidth(2);
            mCirclePaint.setStyle(Paint.Style.STROKE);
        }
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;
        float circleRadius = mPointRadius + 40;
        final RectF oval = new RectF();
        oval.set(centerX - circleRadius, centerY - circleRadius, centerX + circleRadius, centerY + circleRadius);
        canvas.drawArc(oval, 135, 270, false, mCirclePaint);
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
            mPointCirclePaint.setColor(0xFFF7F7F7);
            mPointCirclePaint.setAntiAlias(true);
            mPointCirclePaint.setDither(true);
        }
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
