package adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import bean.MainBean;
import yomix.yt.com.myapplication.R;

/**
 * @author YT
 * @date 2018/2/5
 */

public class MainAdapter extends BaseQuickAdapter<MainBean, BaseViewHolder> {
    private int currentPosition;

    private int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void refreshView() {
        notifyDataSetChanged();
    }

    public MainAdapter(int layoutResId, @Nullable List<MainBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainBean item) {
        helper.setText(R.id.title, item.getTitle());
        helper.setText(R.id.message, item.getMessage());
        int position = helper.getAdapterPosition();
        if (position == getCurrentPosition()) {
            helper.setBackgroundColor(R.id.layout, Color.GRAY);
        } else {
            helper.setBackgroundColor(R.id.layout, Color.WHITE);
        }
    }

}
