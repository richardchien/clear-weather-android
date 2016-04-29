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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        ButterKnife.bind(this, view);

        mTvCountyName.setText(getArguments().getString("text", "Hello!"));

        return view;
    }
}
