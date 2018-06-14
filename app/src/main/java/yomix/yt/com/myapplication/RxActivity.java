package yomix.yt.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;

import tao.pangu.ToastUtils;
import util.RfidConvert;

/**
 * @author YT
 */
public class RxActivity extends AppCompatActivity {

    String[] selection;

    int[] values;

    int maxValue = 3000, minValue = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        selection = RfidConvert.RfidConvertToPer(maxValue, minValue);

        Log.d("数组对象-数组转String", Arrays.toString(selection));
        String str = Arrays.toString(selection);
        String[] strs = str.split(",");
        for (String str1 : strs) {
            Log.d("数组对象-String转数组", str1);
        }

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

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return selection.length;
        }

        @Override
        public Object getItem(int position) {
            return selection[position];
        }

        @Override
        public long getItemId(int position) {
            return selection[position].length();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(RxActivity.this).inflate(R.layout.list_view, null);

            TextView textView = view.findViewById(R.id.tv_content);

            textView.setText(selection[position]);

            return view;
        }
    }
}
