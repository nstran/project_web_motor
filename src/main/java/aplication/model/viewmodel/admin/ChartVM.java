package aplication.model.viewmodel.admin;

import aplication.model.viewmodel.common.LayoutHeaderAdminVM;

import java.util.List;

public class ChartVM {

    private LayoutHeaderAdminVM layoutHeaderAdminVM;
    private List<ChartDataVM> chartCategoryProductVMS;
    private List<ChartDataVM> chartSumAmountPrductByCategory;
    private List<ChartDataVM1> chartSumPricePrductByCategory;
    private List<ChartDataVM1> chartSumPriceInMonth;
    private List<ChartDataVM1> chartSumPriceInMonth18;
    private List<ChartDataVM1> chartSumPriceInYear;

    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {
        return layoutHeaderAdminVM;
    }

    public void setLayoutHeaderAdminVM(LayoutHeaderAdminVM layoutHeaderAdminVM) {
        this.layoutHeaderAdminVM = layoutHeaderAdminVM;
    }

    public List<ChartDataVM> getChartCategoryProductVMS() {
        return chartCategoryProductVMS;
    }

    public void setChartCategoryProductVMS(List<ChartDataVM> chartCategoryProductVMS) {
        this.chartCategoryProductVMS = chartCategoryProductVMS;
    }

    public List<ChartDataVM> getChartSumAmountPrductByCategory() {
        return chartSumAmountPrductByCategory;
    }

    public void setChartSumAmountPrductByCategory(List<ChartDataVM> chartSumAmountPrductByCategory) {
        this.chartSumAmountPrductByCategory = chartSumAmountPrductByCategory;
    }

    public List<ChartDataVM1> getChartSumPricePrductByCategory() {
        return chartSumPricePrductByCategory;
    }

    public void setChartSumPricePrductByCategory(List<ChartDataVM1> chartSumPricePrductByCategory) {
        this.chartSumPricePrductByCategory = chartSumPricePrductByCategory;
    }

    public List<ChartDataVM1> getChartSumPriceInMonth() {
        return chartSumPriceInMonth;
    }

    public void setChartSumPriceInMonth(List<ChartDataVM1> chartSumPriceInMonth) {
        this.chartSumPriceInMonth = chartSumPriceInMonth;
    }

    public List<ChartDataVM1> getChartSumPriceInMonth18() {
        return chartSumPriceInMonth18;
    }

    public void setChartSumPriceInMonth18(List<ChartDataVM1> chartSumPriceInMonth18) {
        this.chartSumPriceInMonth18 = chartSumPriceInMonth18;
    }

    public List<ChartDataVM1> getChartSumPriceInYear() {
        return chartSumPriceInYear;
    }

    public void setChartSumPriceInYear(List<ChartDataVM1> chartSumPriceInYear) {
        this.chartSumPriceInYear = chartSumPriceInYear;
    }
}
