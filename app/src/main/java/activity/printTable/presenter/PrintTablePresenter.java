package activity.printTable.presenter;

import java.util.List;

import activity.printTable.PrintTableBean;
import activity.printTable.contract.PrintTableContract;
import activity.printTable.model.PrintTableModel;

public class PrintTablePresenter implements PrintTableContract.Presenter {

    private PrintTableContract.View view;
    private PrintTableContract.Model model;

    public PrintTablePresenter(PrintTableContract.View view) {
        this.view = view;
        model = new PrintTableModel(this);
    }

    @Override
    public void initData() {
        model.initData();
    }

    @Override
    public void complete(List<PrintTableBean> list) {
        view.complete(list);
    }

    @Override
    public void split(List<PrintTableBean> list) {
        model.split(list);
    }

    @Override
    public void print(String string) {
        view.print(string);
    }
}
