package fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import io.reactivex.schedulers.Schedulers;
import tao.pangu.DialogUtils;
import tao.pangu.IntentUtils;
import yomix.yt.com.myapplication.R;
import yomix.yt.com.myapplication.RfidActivity;
import yomix.yt.com.myapplication.RxActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class YasuoFragment extends BaseFragment {
    View view;

    private static final YasuoFragment YASUO_FRAGMENT = new YasuoFragment();

    public static YasuoFragment newInstance() {
        return YASUO_FRAGMENT;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_yasuo, container, false);
        }

        return view;
    }

    @Override
    void loadData() {
    }

}
