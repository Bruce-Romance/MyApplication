package activity.printTable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yomix.yt.com.myapplication.R;

/**
 * @author YT
 */
public class PrintTableActivity extends AppCompatActivity {
    private List<Bean> list;
    private StringBuffer sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_table);
        TextView tv = findViewById(R.id.tv);
        initData();
        println();
        tv.setText(sb.toString());
    }

    private void initData() {
        list = new ArrayList<>();
        Bean bean1 = new Bean("1234561", "红色", "26", "1");
        Bean bean2 = new Bean("1234562", "黄色", "27", "4");
        Bean bean3 = new Bean("1234563", "空蓝", "28", "6");
        Bean bean4 = new Bean("1234564", "绿色", "29", "2");
        Bean bean5 = new Bean("1234565", "紫色", "30", "5");
        Bean bean6 = new Bean("1234566", "粉色", "31", "12");
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        list.add(bean6);
    }

    class Bean {

        private String number, color, size, count;

        Bean(String number, String color, String size, String count) {
            this.number = number;
            this.color = color;
            this.size = size;
            this.count = count;
        }

        public String getNumber() {
            return number;
        }

        public String getColor() {
            return color;
        }

        public String getSize() {
            return size;
        }

        public String getCount() {
            return count;
        }
    }

    private void println() {
        sb = new StringBuffer();
        //默认size
        String[] s1 = new String[list.size()];
        //默认code
        String[] g1 = new String[list.size()];
        //默认color
        String[] c1 = new String[list.size()];
        //默认位置
        String[] l1 = new String[list.size()];
        sb.append("\n┏━━━━┳━━┳━┳━┳━┳━┳━┳━┓\n");
        sb.append("┃商品编号┃颜色┃");
        //第一个循环 打印size
        for (int i = 0; i < list.size(); i++) {
            s1[i] = list.get(i).getSize();
            g1[i] = list.get(i).getNumber();
            c1[i] = list.get(i).getColor();
            l1[i] = "0";
            //打印第一行
            if (i == list.size() - 1) {
                //如果是最后一个就换行，后面就是下一行内容
                sb.append(s1[i] + "┃\n");
                sb.append("┣━━━━╋━━╋━╋━╋━╋━╋━╋━┫\n┃");
            } else {
                sb.append(s1[i] + "┃");
            }
        }
        for (int i = 0; i < list.size(); i++) {
            //打印编号和颜色,可以获取到这条数据的size,然后比较，放到对应位置
            String size = list.get(i).getSize();
            sb.append(g1[i] + " ┃" + c1[i] + "┃");
            for (int j = 0; j < list.size(); j++) {
                //如果这条数据数据款色码,就将数量赋值到对应位置.
                if (size.equals(list.get(j).getSize())) {
                    //打印到最后一个就换行
                    if (j == list.size() - 1) {
                        sb.append(list.get(i).getCount().length() < 2 ? list.get(i).getCount() + " ┃" : list.get(i).getCount() + "┃\n");
                        sb.append("┣━━━━╋━━╋━╋━╋━╋━╋━╋━┫\n┃");
                    } else {
                        sb.append(list.get(i).getCount().length() < 2 ? list.get(i).getCount() + " ┃" : list.get(i).getCount() + "┃");
                    }
                } else {
                    if (j == list.size() - 1) {
                        sb.append("  ┃\n");
                        sb.append("┣━━━━╋━━╋━╋━╋━╋━╋━╋━┫\n┃");
                    } else {
                        sb.append("  ┃");
                    }
                }
            }
        }
        sb.append("合  计  ┃                         n  ┃\n");
        sb.append("┗━━━━┻━━━━━━━━━━━━━━┛\n\n\n");
    }
}
