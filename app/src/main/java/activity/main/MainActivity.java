package activity.main;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import adapter.ViewPagerAdapter;
import fragment.KhaZixFragment;
import fragment.LeesinFragment;
import fragment.YasuoFragment;
import fragment.ZedFragment;
import util.BottomNavigationViewHelper;
import yomix.yt.com.myapplication.R;

/**
 * @author YT
 */
public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BottomNavigationView bv = findViewById(R.id.bottomView);
        //去掉底部导航栏动画效果
        BottomNavigationViewHelper.disableShiftMode(bv);
        viewPager = findViewById(R.id.viewPager);
        bv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bnv_function:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.bnv_query:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.bnv_forms:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.bnv_setting:
                        viewPager.setCurrentItem(3);
                        break;
                    default:
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bv.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bv.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //设置adapter
        setViewPager(viewPager);
    }

    private void setViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(ZedFragment.newInstance());
        adapter.addFragment(YasuoFragment.newInstance());
        adapter.addFragment(KhaZixFragment.newInstance());
        adapter.addFragment(LeesinFragment.newInstance());

        viewPager.setAdapter(adapter);
    }
}
