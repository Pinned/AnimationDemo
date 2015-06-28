package cn.knero.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by luozhaocheng on 6/28/15.
 */
public class WaterWaveView extends View {

    private Path mWaterWavePath = new Path();

    private int mWaterHeight = 50;
    private int mDistance = 500;
    private int mAdjustAmplitude = 30;
    private int xExc=0;

    public WaterWaveView(Context context) {
        super(context);
    }

    public WaterWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getWaterHeight() {
        return mWaterHeight;
    }

    public int getDistance() {
        return mDistance;
    }

    public int getAdjustAmplitude() {
        return mAdjustAmplitude;
    }

    public void setWaterHeight(int waterHeight) {
        mWaterHeight = waterHeight;
    }

    public void setDistance(int distance) {
        mDistance = distance;
    }

    public void setAdjustAmplitude(int adjustAmplitude) {
        mAdjustAmplitude = adjustAmplitude;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWaterWavePath.reset();
        int wh = getHeight() - 1;
        mWaterWavePath.moveTo(0, wh + 1);
        for (int i = 0; i < getWidth(); i++) {
            double tempSinx = Math.sin((i+ xExc) * 2 * Math.PI / mDistance);
            float y = (float) (mWaterHeight + mAdjustAmplitude*tempSinx);
            mWaterWavePath.lineTo(i, y);
        }
        mWaterWavePath.lineTo(getWidth(),  wh + 1);
        mWaterWavePath.lineTo(0, wh + 1);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.GRAY);
        canvas.drawPath(mWaterWavePath,paint );
        xExc += 20;
        postInvalidateDelayed(100);
    }
}
