package im.r_c.android.clearweather.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import im.r_c.android.clearweather.R;

/**
 * ClearWeather
 * Created by richard on 16/4/29.
 */
public class CardFragment extends Fragment {
    public static CardFragment newInstance(String text) {
        Bundle args = new Bundle();
        args.putString("text", text);
        CardFragment fragment = new CardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.tv_county_name)
    TextView mTvCountyName;

    @Bind(R.id.tv_county_weather)
    TextView mTvCountyWeather;

    @Bind(R.id.tv_now_temperature)
    TextView mTvNowTemperature;

    @Bind(R.id.tv_today_day_weather)
    TextView mTvTodayDayWeather;

    @Bind(R.id.tv_today_night_weather)
    TextView mTvTodayNightWeather;

    @Bind(R.id.tv_today_temperature)
    TextView mTvTodayTemperature;

    @Bind(R.id.tv_today_humidity)
    TextView mTvTodayHumidity;

    @Bind(R.id.tv_today_rain_probability)
    TextView mTvTodayRainProbability;

    @Bind(R.id.tv_today_distance)
    TextView mTvDistance;

    @Bind(R.id.tv_tomorrow_day_weather)
    TextView mTvTomorrowDayWeather;

    @Bind(R.id.tv_tomorrow_temperature)
    TextView mTvTomorrowTemperature;

    @Bind(R.id.tv_day_after_tomorrow_day_weather)
    TextView mTvDayAfterTomorrowDayWeather;

    @Bind(R.id.tv_day_after_tomorrow_temperature)
    TextView mTvDayAfterTomorrowTemperature;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        ButterKnife.bind(this, view);

        mTvCountyName.setText(getArguments().getString("text", "Hello!"));

        return view;
    }
}
