package videotransition.linksu.com.videotransition;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.linksu.video_manager_library.ui.LVideoView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnPlaceClickListener {

    private RecyclerView rv;

    private FrameLayout intermediary;

    private List<String> list = new ArrayList<>();

    private ItemAdapter adapter;

    private LVideoView lVideoView;

    private FragmentA fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lVideoView = new LVideoView(this);
//        FragmentA fragment = getSupportFragmentManager().findFragmentByTag(FragmentA.class.getName());
        if (fragment == null) {
            fragment = FragmentA.newInstance(lVideoView);
            getSupportFragmentManager().beginTransaction().add(R.id.intermediary,
                    fragment,
                    FragmentA.class.getName())
                    .commit();
        }

    }
    private void oneStepBack() {
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() >= 1) {
            fragmentManager.popBackStackImmediate();
            fts.commit();
        } else {
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
            fragmentTransaction.addSharedElement(sharedView, sharedElementName);
            fragmentTransaction.commit();
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onPlaceClicked(View sharedView, int position) {
// Android 5.0 使用转场动画
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(this, sharedView, "photos");

            Intent intent = new Intent(this, NewsDetailActivity.class);
            startActivity(intent, options.toBundle());
        } else {
            //让新的Activity从一个小的范围扩大到全屏
            ActivityOptionsCompat options = ActivityOptionsCompat
                    .makeScaleUpAnimation(sharedView, sharedView.getWidth() / 2,
                            sharedView.getHeight() / 2, 0, 0);
            Intent intent = new Intent(this, NewsDetailActivity.class);
            startActivity(intent, options.toBundle());
        }
    }
}
