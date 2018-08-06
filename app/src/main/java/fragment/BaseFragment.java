package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/3/29.
 */

abstract class BaseFragment extends Fragment {
    /*标志位 判断数据是否初始化*/
    private boolean isInitData = false;
    /*标志位 判断fragment是否可见*/
    private boolean isVisibleToUser = false;
    /*标志位 判断view已经加载完成 避免空指针操作*/
    private boolean isPrepareView = false;


    /*当fragment由可见变为不可见和不可见变为可见时回调*/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisibleToUser = isVisibleToUser;
        lazyLoadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //此时View已经准备好了
        isPrepareView = true;
    }

    /*fragment生命周期中onViewCreated之后的方法 在这里调用一次懒加载 避免第一次可见不加载数据*/
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lazyLoadData();
    }

    private void lazyLoadData() {
        if (!isInitData && isVisibleToUser && isPrepareView) {
            isInitData = true;
            loadData();
        }
    }

    /*加载数据的方法,由子类实现*/
    abstract void loadData();
}
