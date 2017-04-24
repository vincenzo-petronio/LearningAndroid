package it.localhost.app.mobile.weathercard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 */
public class WeatherCardView extends CardView {

    private static final String TAG = WeatherCardView.class.getSimpleName();
    private ImageView mIvIcon;
    private TextView mTvDateTime, mTvTemperature;
    private View mContainer;
    private int mWeatherCardType;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CLOUDY, HAIL})
    public @interface WeatherCardType {
    }

    public static final int CLOUDY = 1;
    public static final int HAIL = 2;


    /**
     * Used programmatically
     *
     * @param context Context
     */
    public WeatherCardView(@NonNull Context context) {
        super(context);

        init(context, null, 0);
    }

    /**
     * Used in XML layout
     *
     * @param context Context
     * @param attrs   AttributeSet
     */
    public WeatherCardView(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs, 0);
    }

    /**
     * Used programmatically
     *
     * @param context      Context
     * @param attrs        AttributeSet
     * @param defStyleAttr int
     */
    public WeatherCardView(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr);
    }

    /**
     * @param context Context
     * @param attrs   AttributeSet
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        Log.v(TAG, "init");

        ((LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_weathercard, this, true);

        mTvDateTime = (TextView) findViewById(R.id.tv_datetime);
        mTvTemperature = (TextView) findViewById(R.id.tv_temperature);
        mIvIcon = (ImageView) findViewById(R.id.iv_icon);


        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WeatherCardView, defStyleAttr, 0);
        try {
            mWeatherCardType = a.getInt(R.styleable.WeatherCardView_cardType, CLOUDY); // FIXME change default state

        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        } finally {
            a.recycle();
        }

        switch (mWeatherCardType) {
            case CLOUDY:
                mIvIcon.setImageResource(R.drawable.ic_weather_cloudy_grey600_48dp);
                break;
            case HAIL:
                mIvIcon.setImageResource(R.drawable.ic_weather_hail_grey600_48dp);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.v(TAG, "onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.v(TAG, "onLayout");
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.v(TAG, "onDraw");
        super.onDraw(canvas);
    }


    // GETTER & SETTER

    @WeatherCardType
    public int getWeatherCardType() {
        return mWeatherCardType;
    }

    public void setWeatherCardType(@WeatherCardType int weatherCardType) {
        this.mWeatherCardType = weatherCardType;
    }
}
