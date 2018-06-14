package fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import tao.pangu.IntentUtils;
import yomix.yt.com.myapplication.DialogActivity;
import yomix.yt.com.myapplication.PrintTableActivity;
import yomix.yt.com.myapplication.R;
import yomix.yt.com.myapplication.RecyclerActivity;
import yomix.yt.com.myapplication.RfidActivity;
import yomix.yt.com.myapplication.RxActivity;

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
        }
        return view;
    }

    @Override
    void loadData() {

    }

}
