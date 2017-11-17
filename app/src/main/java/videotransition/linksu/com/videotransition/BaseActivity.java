package videotransition.linksu.com.videotransition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
public class BaseActivity extends AppCompatActivity {
    public static LVideoView lVideoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (lVideoView == null) {
            Log.e("linksu", "onCreate(BaseActivity.java:26)");
            lVideoView = new LVideoView(this);
        }
    }
}
