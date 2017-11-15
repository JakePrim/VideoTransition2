package videotransition.linksu.com.videotransition;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.linksu.video_manager_library.ui.LVideoView;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：linksus
 * 版    本：1.0
 * 创建日期：11/15 0015
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class FragmentA extends Fragment implements ItemAdapter.OnPlaceClickListener {

    private RecyclerView rv;

    private List<String> list = new ArrayList<>();

    private ItemAdapter adapter;

    public static final String TAG = FragmentA.class.getSimpleName();

    private LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main, container, false);
            rv = (RecyclerView) view.findViewById(R.id.rv);
            linearLayoutManager = new LinearLayoutManager(getActivity());
            rv.setLayoutManager(linearLayoutManager);
            for (int i = 0; i < 30; i++) {
                list.add("" + i);
            }
            adapter = new ItemAdapter(this, getActivity());
            rv.setAdapter(adapter);
            adapter.setPlacesList(list);
        }
        if (lVideoView != null && lVideoView.isPlayer()) {
            addVideo();
        }
        return view;
    }

    private static LVideoView lVideoView;

    public static FragmentA newInstance(LVideoView lVideoView) {
        FragmentA.lVideoView = lVideoView;
        return new FragmentA();
    }

    private int position = 0;

    @Override
    public void onPlaceClicked(View sharedView, int position) {
        this.position = position;
        if (sharedView.getId() == R.id.iv_img) {
            ItemAdapter.BaliViewHolder viewHolderForAdapterPosition = (ItemAdapter.BaliViewHolder) rv.findViewHolderForAdapterPosition(position);
            View itemView = viewHolderForAdapterPosition.itemView;
            FrameLayout itemFlImg = (FrameLayout) itemView.findViewById(R.id.itemFlImg);
            itemFlImg.removeAllViews();
            ViewGroup last = (ViewGroup) lVideoView.getParent();//找到videoitemview的父类，然后remove
            if (last != null && last.getChildCount() > 0) {
                last.removeAllViews();
            }
            itemFlImg.addView(lVideoView);
            lVideoView.startLive("http://rmrbtest-image.peopleapp.com/upload/video/201707/1499914158feea8c512f348b4a.mp4");
        } else {
            Fragment fragmentB = getFragmentManager().findFragmentByTag(TAG);
            if (fragmentB == null) fragmentB = FragmentB.newInstance(lVideoView);
            Bundle bundle = new Bundle();
            bundle.putString("transitionName", "transition" + position);
            fragmentB.setArguments(bundle);
            ((MainActivity) getActivity()).showFragmentWithTransition(this, fragmentB, "movieDetail", sharedView, "transition" + position);
        }
    }

    public void addVideo() {
        View view = linearLayoutManager.findViewByPosition(position);
        FrameLayout itemFlImg = (FrameLayout) view.findViewById(R.id.itemFlImg);
        itemFlImg.removeAllViews();
        ViewGroup last = (ViewGroup) lVideoView.getParent();//找到videoitemview的父类，然后remove
        if (last != null && last.getChildCount() > 0) {
            last.removeAllViews();
        }
        itemFlImg.addView(lVideoView);
    }
}
