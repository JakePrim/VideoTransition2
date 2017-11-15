package videotransition.linksu.com.videotransition;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.BaliViewHolder> {

    private List<String> placeList = new ArrayList<>();

    private Context context;
    private final OnPlaceClickListener listener;

    ItemAdapter(OnPlaceClickListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    public BaliViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaliViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final BaliViewHolder holder, final int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.itemFlImg.setTransitionName("transition" + position);
        }
        holder.tv_title.setVisibility(View.VISIBLE);
        holder.tv_title.setText("sdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        holder.itemFlImg.setVisibility(View.GONE);
        holder.itemFlImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPlaceClicked(holder.itemFlImg, position);
            }
        });
        holder.iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemFlImg.setVisibility(View.VISIBLE);
                listener.onPlaceClicked(v, position);
            }
        });
    }

    void setPlacesList(List<String> placesList) {
        placeList = placesList;
        for (int i = 0; i < placeList.size(); i++) {
            notifyItemInserted(i);
        }
    }


    interface OnPlaceClickListener {
        void onPlaceClicked(View sharedView, final int position);
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public static class BaliViewHolder extends RecyclerView.ViewHolder {
        public FrameLayout itemFlImg;

        public ImageView iv_img;

        public TextView tv_title;

        public View itemView;

        public BaliViewHolder(final View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.itemFlImg = (FrameLayout) itemView.findViewById(R.id.itemFlImg);
            this.iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
