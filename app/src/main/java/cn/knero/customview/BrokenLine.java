package cn.knero.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luozhaocheng on 6/28/15.
 */
public class BrokenLine extends View {

    private List<Point> mPoints = new ArrayList<>();
    private Paint mLinePint;
    private final int R = 13;

    public BrokenLine(Context context) {
        super(context);
    }

    public BrokenLine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BrokenLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPoints(List<Point> points) {
        this.mPoints = points;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mLinePint == null) {
            mLinePint = new Paint();
            mLinePint.setColor(Color.GRAY);
        }
        int width = getWidth();
        int height = getHeight();
        canvas.drawLine(0, 0, 0, getHeight(), mLinePint);
        for (int i = 0; i < 5; i++) {
            int y = height - (int) (height * 1.0 / 5 * i) - 2;
            canvas.drawLine(0, y, width, y, mLinePint);
        }
        Path brokenLinePath = new Path();
        Paint brokenLinePaint = new Paint();
        brokenLinePaint.setColor(Color.BLUE);
        Paint pointPaint = new Paint();
        pointPaint.setColor(Color.RED);
        for (int i = 0; i < 5; i++) {
            Point point = new Point(i * width / 5 + R, (int) (Math.random() * height));
            mPoints.add(point);
        }

        Point point;
        Path bgPath = new Path();
        Point maxPoint ;
        maxPoint = point = mPoints.get(0);
        bgPath.moveTo(point.x, height);
        bgPath.lineTo(point.x, point.y);
        for (int i = 1; i < 5; i++) {
            point = mPoints.get(i);
            bgPath.lineTo(point.x, point.y);
            if (point.y < maxPoint.y) {
                maxPoint = point;
            }
        }
        bgPath.lineTo(point.x, height);
        bgPath.close();

        Shader shader = new Shader();
        int[] colors = new int[] {
                Color.argb(180,0,0,255),
                Color.argb(0,0,0,0)
        };
        LinearGradient linearGradient = new LinearGradient(maxPoint.x, maxPoint.y, maxPoint.x, height, colors, null,  Shader.TileMode.REPEAT);
        Paint bgPaint = new Paint();
        bgPaint.setShader(linearGradient);
        canvas.drawPath(bgPath, bgPaint);

        point = mPoints.get(0);
        brokenLinePath.moveTo(point.x, point.y);
        for (int i = 1; i < 5; i++) {
            point = mPoints.get(i);
            brokenLinePath.lineTo(point.x, point.y);
        }
//        brokenLinePath.close();
        brokenLinePaint.setStyle(Paint.Style.STROKE);
        brokenLinePaint.setStrokeWidth(4);
        brokenLinePaint.setAntiAlias(true);
        brokenLinePaint.setDither(true);
        canvas.drawPath(brokenLinePath, brokenLinePaint);
        pointPaint.setAntiAlias(true);
        pointPaint.setDither(true);
        for (int i = 0 ; i < 5; i++) {
            point = mPoints.get(i);
            pointPaint.setColor(Color.WHITE);
            canvas.drawCircle(point.x, point.y, 13, pointPaint);
            pointPaint.setColor(Color.BLUE);
            canvas.drawCircle(point.x, point.y, 10, pointPaint);
        }
    }
}
