package im.r_c.android.clearweather.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import im.r_c.android.clearweather.R;
import im.r_c.android.clearweather.adapter.CardPagerAdapter;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.vp_main)
    ViewPager mVpMain;

    @Bind(R.id.ci_indicator)
    CircleIndicator mCiIndicator;

    private String[] mCounties = {"常州", "武进", "白云", "钟楼", "天宁"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mVpMain.setAdapter(new CardPagerAdapter(getSupportFragmentManager(), Arrays.asList(mCounties)));
        mCiIndicator.setViewPager(mVpMain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
