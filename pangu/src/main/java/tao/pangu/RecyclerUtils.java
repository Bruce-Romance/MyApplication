package tao.pangu;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author YT
 * @date 2018/2/5
 */

public class RecyclerUtils {
    private static RecyclerView.LayoutManager mLayoutManager;

    /**
     * 此方法要在初始化Adapter之后调用
     * 适合Activity调用
     *
     * @param activity
     * @param adapter
     * @param recyclerId
     */
    public static void initRecycler(Activity activity, RecyclerView.Adapter adapter, int recyclerId) {
        RecyclerView recyclerView = activity.findViewById(recyclerId);
        mLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 此方法要在初始化Adapter之后调用
     * 适合fragment调用
     *
     * @param activity
     * @param adapter
     * @param recyclerView
     */
    public static void initRecyclerWithFragment(Activity activity, RecyclerView.Adapter adapter, RecyclerView recyclerView,int orientation) {
        mLayoutManager = new LinearLayoutManager(activity, orientation, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public static RecyclerView.LayoutManager getLayoutManager() {
        return mLayoutManager;
    }
}
