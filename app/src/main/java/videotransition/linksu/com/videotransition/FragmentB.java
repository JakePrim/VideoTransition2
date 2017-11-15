package videotransition.linksu.com.videotransition;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.kogitune.activity_transition.fragment.ExitFragmentTransition;
import com.kogitune.activity_transition.fragment.FragmentTransition;
import com.kogitune.activity_transition.fragment.FragmentTransitionLauncher;
import com.linksu.video_manager_library.ui.LVideoView;

/**
 * ================================================
 * 作    者：linksus
 * 版    本：1.0
 * 创建日期：11/15 0015
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class FragmentB extends Fragment {

    ImageView imageView;

    private static LVideoView lVideoView;

    FrameLayout intermediary;

    public static FragmentB newInstance(LVideoView lVideoView) {
        FragmentB.lVideoView = lVideoView;
        return new FragmentB();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(new DetailsTransition());
            setEnterTransition(new Fade());
            setSharedElementReturnTransition(new DetailsTransition());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detail, container, false);
        imageView = (ImageView) view.findViewById(R.id.iv_img);
        intermediary = (FrameLayout) view.findViewById(R.id.intermediary);
        Bundle b = getArguments();
        if (b != null) {
            String transitionName = b.getString("transitionName");
            intermediary.removeAllViews();
            ViewGroup last = (ViewGroup) lVideoView.getParent();//找到videoitemview的父类，然后remove
            if (last != null && last.getChildCount() > 0) {
                last.removeAllViews();
            }
            intermediary.addView(lVideoView);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                intermediary.setTransitionName(transitionName);
            } else {
                ExitFragmentTransition exitFragmentTransition = FragmentTransition.with(this)
                        .to(intermediary).start(savedInstanceState);
                exitFragmentTransition.startExitListening();
            }
        }
        return view;
    }
}
