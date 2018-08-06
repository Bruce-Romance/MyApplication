package activity.printTable.model;

import java.util.ArrayList;
import java.util.List;

import activity.printTable.PrintTableBean;
import activity.printTable.contract.PrintTableContract;

public class PrintTableModel implements PrintTableContract.Model {

    private PrintTableContract.Presenter presenter;

    public PrintTableModel(PrintTableContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initData() {
        List<PrintTableBean> list = new ArrayList<>();
        PrintTableBean bean1 = new PrintTableBean("1234561", "红色", "26", "1");
        PrintTableBean bean2 = new PrintTableBean("1234562", "黄色", "27", "4");
        PrintTableBean bean3 = new PrintTableBean("1234563", "空蓝", "28", "6");
        PrintTableBean bean4 = new PrintTableBean("1234564", "绿色", "29", "2");
        PrintTableBean bean5 = new PrintTableBean("1234565", "紫色", "30", "5");
        PrintTableBean bean6 = new PrintTableBean("1234566", "粉色", "31", "12");
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        list.add(bean6);

        presenter.complete(list);
    }

    @Override
    public void split(List<PrintTableBean> list) {
        StringBuilder sb = new StringBuilder();
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
                sb.append(s1[i]).append("┃\n");
                sb.append("┣━━━━╋━━╋━╋━╋━╋━╋━╋━┫\n┃");
            } else {
                sb.append(s1[i]).append("┃");
            }
        }
        for (int i = 0; i < list.size(); i++) {
            //打印编号和颜色,可以获取到这条数据的size,然后比较，放到对应位置
            String size = list.get(i).getSize();
            sb.append(g1[i]).append(" ┃").append(c1[i]).append("┃");
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
        presenter.print(sb.toString());
    }
}
