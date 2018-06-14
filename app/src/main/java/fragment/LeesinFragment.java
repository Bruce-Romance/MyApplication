package fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import yomix.yt.com.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeesinFragment extends BaseFragment {

    View view;

    private static final LeesinFragment LEESIN_FRAGMENT = new LeesinFragment();

    public static LeesinFragment newInstance() {
        return LEESIN_FRAGMENT;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_leesin, container, false);
        }
        return view;
    }

    @Override
    void loadData() {

    }
}
