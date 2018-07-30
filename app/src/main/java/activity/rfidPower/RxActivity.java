package activity.rfidPower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import activity.rfidPower.contract.RxContract;
import activity.rfidPower.presenter.RxPresenter;
import toast.ToastUtils;
import util.RfidConvert;
import yomix.yt.com.myapplication.R;

/**
 * @author YT
 */
public class RxActivity extends AppCompatActivity implements RxContract.View {

    String[] mSelection;

    int maxValue = 3000, minValue = 500;

    private RxContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        presenter = new RxPresenter(this);

        presenter.init(maxValue, minValue);

        ListView listView = findViewById(R.id.listView);

        listView.setAdapter(new MyAdapter());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int[] value = RfidConvert.PerConvertToRfid(maxValue, minValue);
                ToastUtils.success(String.valueOf(value[position]));
            }
        });
    }

    @Override
    public void change(String[] selection) {
        mSelection = selection;
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mSelection.length;
        }

        @Override
        public Object getItem(int position) {
            return mSelection[position];
        }

        @Override
        public long getItemId(int position) {
            return mSelection[position].length();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(RxActivity.this).inflate(R.layout.list_view, null);

            TextView textView = view.findViewById(R.id.tv_content);

            textView.setText(mSelection[position]);

            return view;
        }
    }
}
