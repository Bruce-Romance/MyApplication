package activity.reverse.model;

import android.text.TextUtils;
import activity.reverse.contract.ReverseContract;

public class ReverseModel implements ReverseContract.Model {

    private ReverseContract.Presenter presenter;

    public ReverseModel(ReverseContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 判断回文
     * @param str
     */
    @Override
    public void method1(String str) {
        if (!TextUtils.isEmpty(str)) {
            StringBuilder stringBuilder = new StringBuilder(str);
            if (stringBuilder.reverse().toString().equals(str)) {
                presenter.result1("是回文");
            } else {
                presenter.result1("不是回文");
            }
        } else {
            presenter.result1("请输入内容");
        }
    }

    /**
     * 反转字符串
     * @param str
     */
    @Override
    public void method2(String str) {
        char[] c = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = c.length - 1; i >= 0; i--) {
            sb.append(c[i]);
        }
        presenter.result2(sb.toString());
    }

    /**
     * 反转字符串
     * @param str
     */
    @Override
    public void method3(String str) {
        StringBuilder stringBuffer = new StringBuilder(str);
        presenter.result3(stringBuffer.reverse().toString());
    }
}
