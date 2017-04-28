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
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 */
public class WeatherCardView extends CardView {

    private static final String TAG = WeatherCardView.class.getSimpleName();
    private Context mContext;
    private ImageView mIvIcon;
    private TextView mTvDateTime, mTvTemperature;
    private int mWeatherCardType;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CLOUDY, HAIL, LIGHTNING, NIGHT, PARTLYCLOUDY, POURING, RAINY, SNOWY, WINDY})
    public @interface WeatherCardType {
    }

    public static final int CLOUDY = 1;
    public static final int HAIL = 2;
    public static final int LIGHTNING = 3;
    public static final int NIGHT = 4;
    public static final int PARTLYCLOUDY = 5;
    public static final int POURING = 6;
    public static final int RAINY = 7;
    public static final int SNOWY = 8;
    public static final int WINDY = 9;


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

        this.mContext = context;

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

        initUI();
    }

    private void initUI() {
        switch (mWeatherCardType) {
            case CLOUDY:
                mIvIcon.setImageResource(R.drawable.ic_weather_cloudy_grey600_48dp);
                mTvTemperature.setText("15");
                break;
            case HAIL:
                mIvIcon.setImageResource(R.drawable.ic_weather_hail_grey600_48dp);
                mTvTemperature.setText("12");
                break;
            case LIGHTNING:
                mIvIcon.setImageResource(R.drawable.ic_weather_lightning_grey600_48dp);
                mTvTemperature.setText("10");
                break;
            case NIGHT:
                mIvIcon.setImageResource(R.drawable.ic_weather_night_grey600_48dp);
                mTvTemperature.setText("8");
                break;
            case PARTLYCLOUDY:
                mIvIcon.setImageResource(R.drawable.ic_weather_partlycloudy_grey600_48dp);
                mTvTemperature.setText("17");
                break;
            case POURING:
                mIvIcon.setImageResource(R.drawable.ic_weather_pouring_grey600_48dp);
                mTvTemperature.setText("15");
                break;
            case RAINY:
                mIvIcon.setImageResource(R.drawable.ic_weather_rainy_grey600_48dp);
                mTvTemperature.setText("13");
                break;
            case SNOWY:
                mIvIcon.setImageResource(R.drawable.ic_weather_snowy_grey600_48dp);
                mTvTemperature.setText("-2");
                break;
            case WINDY:
                mIvIcon.setImageResource(R.drawable.ic_weather_windy_grey600_48dp);
                mTvTemperature.setText("4");
                break;
            default:
                break;
        }

        mTvDateTime.setText(String.format(Locale.ITALY, mContext.getString(R.string.weather_tvdate), getTimeNow()));
    }

    private long getTimeNow() {
        return Calendar.getInstance().getTimeInMillis();
    }

    // GETTER & SETTER

    @WeatherCardType
    public int getWeatherCardType() {
        return mWeatherCardType;
    }

    public void setWeatherCardType(@WeatherCardType int weatherCardType) {
        this.mWeatherCardType = weatherCardType;
        invalidate();
    }
}
