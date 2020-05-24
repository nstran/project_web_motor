package aplication.model.viewmodel.admin;

public class ChartDataVM1 {

    private String label;
    private double value;

    public ChartDataVM1() {
    }

    public ChartDataVM1(String label, double value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
