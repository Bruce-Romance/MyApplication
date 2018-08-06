package activity.printTable;

public class PrintTableBean {
    private String number, color, size, count;

    public PrintTableBean(String number, String color, String size, String count) {
        this.number = number;
        this.color = color;
        this.size = size;
        this.count = count;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
