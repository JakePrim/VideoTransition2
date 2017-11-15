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
@SuppressLint("NewApi")
public class FragmentB extends Fragment implements Transition.TransitionListener {

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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                intermediary.setTransitionName(transitionName);
                intermediary.removeAllViews();
                ViewGroup last = (ViewGroup) lVideoView.getParent();//找到videoitemview的父类，然后remove
                if (last != null && last.getChildCount() > 0) {
                    last.removeAllViews();
                }
                intermediary.addView(lVideoView);
            }
        }
        return view;
    }

    @Override
    public void onTransitionStart(Transition transition) {
        intermediary.removeAllViews();
        ViewGroup last = (ViewGroup) lVideoView.getParent();//找到videoitemview的父类，然后remove
        if (last != null && last.getChildCount() > 0) {
            last.removeAllViews();
        }
        intermediary.addView(lVideoView);
    }

    @SuppressLint("NewApi")
    @Override
    public void onTransitionEnd(Transition transition) {
        transition.removeListener(this);
    }

    @Override
    public void onTransitionCancel(Transition transition) {

    }

    @Override
    public void onTransitionPause(Transition transition) {

    }

    @Override
    public void onTransitionResume(Transition transition) {

    }
}
