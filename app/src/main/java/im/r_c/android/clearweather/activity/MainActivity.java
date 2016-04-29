package im.r_c.android.clearweather.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import im.r_c.android.clearweather.R;
import im.r_c.android.clearweather.adapter.CardPagerAdapter;
import im.r_c.android.clearweather.util.UIUtils;
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add:
                UIUtils.dialog(this, "Add", "This should lead users to an activity for searching for a county.");
                break;
            case R.id.menu_item_remove:
                UIUtils.dialog(this, "Remove", "This should remove the current county. (after an alert)");
                break;
            case R.id.menu_item_settings:
                UIUtils.dialog(this, "Settings", "Settings activity");
                break;
        }
        return true;
    }
}
