package fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import intent.IntentUtils;
import activity.customView.CustomActivity;
import activity.dialog.DialogActivity;
import activity.dataBaseTest.DataBaseTestActivity;
import activity.permission.PermissionActivity;
import activity.printTable.PrintTableActivity;
import yomix.yt.com.myapplication.R;
import activity.recycler.RecyclerActivity;
import activity.reverse.ReverseActivity;
import activity.rfidState.RfidActivity;
import activity.rfidPower.RxActivity;
import activity.songCi.SongCiActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZedFragment extends BaseFragment {

    View view;

    private static final ZedFragment ZED_FRAGMENT = new ZedFragment();

    public static ZedFragment newInstance() {
        return ZED_FRAGMENT;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_zed, container, false);
            view.findViewById(R.id.btn_print).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //打印
                    IntentUtils.skipIntent(getActivity(), PrintTableActivity.class, false);
                }
            });
            view.findViewById(R.id.btn_rv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Rv
                    IntentUtils.skipIntent(getActivity(), RecyclerActivity.class, false);
                }
            });
            view.findViewById(R.id.btn_broadcast).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Rfid状态广播
                    IntentUtils.skipIntent(getActivity(), RfidActivity.class, false);
                }
            });
            view.findViewById(R.id.btn_percent).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Rfid功率百分比
                    IntentUtils.skipIntent(getActivity(), RxActivity.class, false);
                }
            });
            view.findViewById(R.id.btn_dialog).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //dialog展示
                    IntentUtils.skipIntent(getActivity(), DialogActivity.class, false);
                }
            });
            view.findViewById(R.id.btn_permission).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //权限申请
                    IntentUtils.skipIntent(getActivity(), PermissionActivity.class, false);
                }
            });
            view.findViewById(R.id.btn_change_str).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //逆转字符串
                    IntentUtils.skipIntent(getActivity(), ReverseActivity.class, false);
                }
            });
            view.findViewById(R.id.btn_idCard).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //数据库测试
                    IntentUtils.skipIntent(getActivity(), DataBaseTestActivity.class, false);
                }
            });
            view.findViewById(R.id.btn_songci).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //宋词
                    IntentUtils.skipIntent(getActivity(), SongCiActivity.class, false);
                }
            });
            view.findViewById(R.id.button10).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //自定义View
                    IntentUtils.skipIntent(getActivity(), CustomActivity.class, false);
                }
            });
        }
        return view;
    }

    @Override
    void loadData() {

    }

}
