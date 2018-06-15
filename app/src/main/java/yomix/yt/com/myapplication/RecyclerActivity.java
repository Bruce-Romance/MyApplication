package yomix.yt.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import java.util.ArrayList;
import java.util.List;
import Bean.MainBean;
import adapter.MainAdapter;
import recyclerView.RecyclerUtils;

/**
 * @author YT
 */
public class RecyclerActivity extends AppCompatActivity {
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        List<MainBean> list = initData();
        mAdapter = new MainAdapter(R.layout.rv_recycler_activity, list);

        //此方法要在初始化adapter和RecyclerView之后调用
        RecyclerUtils.initRecycler(this, mAdapter,R.id.recyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mAdapter.setCurrentPosition(position);
                mAdapter.refreshView();
            }
        });
    }

    private List<MainBean> initData() {
        List<MainBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            MainBean bean = new MainBean();
            bean.setTitle("标题" + i);
            bean.setMessage("内容" + i);
            list.add(bean);
        }
        return list;
    }
}
