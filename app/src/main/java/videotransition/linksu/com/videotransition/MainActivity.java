package videotransition.linksu.com.videotransition;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.kogitune.activity_transition.fragment.FragmentTransitionLauncher;

public class MainActivity extends BaseActivity {

    private FragmentA fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                fragmentTransaction.addSharedElement(sharedView, sharedElementName);
            } else {
                FragmentTransitionLauncher
                        .with(this)
                        .from(sharedView).prepare(newFragment);
            }
            fragmentTransaction.commit();
        }
    }

    public void showActivityTransition(Fragment current, String tag, View sharedView, String sharedElementName) {

    }
}
