package videotransition.linksu.com.videotransition;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.TabLayout;

import com.kogitune.activity_transition.fragment.FragmentTransitionLauncher;

public class MainActivity extends BaseActivity {

    private HomePage fragment;

    private TabLayout tablayout;

    private ViewPager viewpager;

    private String[] tabs = new String[]{"首页", "财经", "娱乐", "游戏"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        initViewPager();
        initTab();
    }

    private void initTab() {
        for (int i = 0; i < tabs.length; i++) {
            tablayout.addTab(tablayout.newTab().setText(tabs[i]));
        }
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initViewPager() {
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(), tabs));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tablayout.getTabAt(position).select();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void oneStepBack() {
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() >= 1) {
            fragmentManager.popBackStackImmediate();
            fts.commit();
        } else {
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        oneStepBack();
    }

    public void showFragmentWithTransition(Fragment current, Fragment newFragment, String tag, View sharedView, String sharedElementName) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // check if the fragment is in back stack
        boolean fragmentPopped = fragmentManager.popBackStackImmediate(tag, 0);
        if (fragmentPopped) {
            // fragment is pop from backStack
        } else {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.intermediary, newFragment, tag);
            fragmentTransaction.addToBackStack(null);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                fragmentTransaction.addSharedElement(sharedView, sharedElementName);
//            } else {
            FragmentTransitionLauncher
                    .with(this)
                    .from(sharedView).prepare(newFragment);
//            }
            fragmentTransaction.commit();
        }
    }

    public void showActivityTransition(Fragment current, String tag, View sharedView, String sharedElementName) {

    }
}
