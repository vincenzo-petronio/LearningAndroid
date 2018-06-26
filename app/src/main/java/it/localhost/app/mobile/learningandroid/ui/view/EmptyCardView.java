package it.localhost.app.mobile.learningandroid.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import it.localhost.app.mobile.learningandroid.R;

/**
 * @author vincenzo.petronio on 20/06/2018.
 */
public class EmptyCardView extends View {

    private static final String TAG = EmptyCardView.class.getSimpleName();
    private Paint mPaint;
    private Rect mRect;
    private RectF mRectF;

    public EmptyCardView(Context context) {
        super(context);
        init();
    }

    public EmptyCardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmptyCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EmptyCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.v(TAG, "onDraw");
        super.onDraw(canvas);

        mRect.right = canvas.getWidth();
        mRect.bottom = canvas.getHeight();

        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.green_1));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        canvas.drawRoundRect(mRectF, 10f, 10f, mPaint);
    }

    private void init() {
        mPaint = new Paint();
        mRect = new Rect();
        mRectF = new RectF(mRect);
    }
}
