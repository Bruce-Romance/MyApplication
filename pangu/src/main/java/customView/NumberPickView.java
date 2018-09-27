package customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class NumberPickView extends View {

    Paint paint = new Paint();

    int offsetX, offsetY, X, Y;

    int lastX, lastY;

    public NumberPickView(Context context) {
        super(context);
    }

    public NumberPickView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberPickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setAntiAlias(true);
        canvas.drawCircle(100,100,80,paint);
        offsetLeftAndRight(offsetX);
        offsetTopAndBottom(offsetY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        X = (int) event.getX();
        Y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                offsetX = X - lastX;
                offsetY = Y - lastY;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
        }
        return true;
    }
}
