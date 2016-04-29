package im.r_c.android.clearweather.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import im.r_c.android.clearweather.fragment.CardFragment;

/**
 * ClearWeather
 * Created by richard on 16/4/29.
 */
public class CardPagerAdapter extends FragmentStatePagerAdapter {
    public CardPagerAdapter(FragmentManager fm, List<String> dataList) {
        super(fm);
        mDataList = dataList;
    }

    private List<String> mDataList;

    @Override
    public Fragment getItem(int position) {
        return CardFragment.newInstance(mDataList.get(position));
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }
}
