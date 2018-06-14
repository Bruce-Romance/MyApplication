package fragment;


import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yomix.yt.com.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KhaZixFragment extends BaseFragment {

    View view;

    private static final KhaZixFragment KHA_ZIX_FRAGMENT = new KhaZixFragment();

    public static KhaZixFragment newInstance() {
        return KHA_ZIX_FRAGMENT;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_kha_zix, container, false);
        }
        return view;
    }

    @Override
    void loadData() {

    }

}
