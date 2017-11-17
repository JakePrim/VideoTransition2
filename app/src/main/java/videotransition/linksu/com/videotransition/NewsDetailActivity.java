package videotransition.linksu.com.videotransition;

import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * ================================================
 * 作    者：linksus
 * 版    本：1.0
 * 创建日期：11/15 0015
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class NewsDetailActivity extends BaseActivity {
    private FrameLayout intermediary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        String transitionName = getIntent().getStringExtra("transitionName");
        intermediary = (FrameLayout) findViewById(R.id.intermediary);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intermediary.setTransitionName(transitionName);
        }
        intermediary.removeAllViews();
        ViewGroup last = (ViewGroup) lVideoView.getParent();//找到videoitemview的父类，然后remove
        if (last != null && last.getChildCount() > 0) {
            last.removeAllViews();
        }
        intermediary.addView(lVideoView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
    }
}
