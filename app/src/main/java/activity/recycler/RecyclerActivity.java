package activity.recycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import Bean.MainBean;
import activity.recycler.contract.RecyclerContract;
import activity.recycler.presenter.RecyclerPresenter;
import adapter.MainAdapter;
import recyclerView.RecyclerUtils;
import yomix.yt.com.myapplication.R;

/**
 * @author YT
 */
public class RecyclerActivity extends AppCompatActivity implements RecyclerContract.View {

    private RecyclerContract.Presenter presenter;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        presenter = new RecyclerPresenter(this);

        presenter.initData();
        
        //此方法要在初始化adapter之后调用
        RecyclerUtils.initRecycler(this, mAdapter, R.id.recyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mAdapter.setCurrentPosition(position);
                mAdapter.refreshView();
            }
        });
    }

    @Override
    public void complete(List<MainBean> list) {
        mAdapter = new MainAdapter(R.layout.rv_recycler_activity, list);
    }
}
