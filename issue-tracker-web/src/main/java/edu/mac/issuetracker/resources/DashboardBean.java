/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.mac.issuetracker.resources;

import edu.mac.issuetracker.issue.dto.IssueDTO;
import edu.mac.issuetracker.issue.manager.IssueManager;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

/**
 *
 * @author tohyu
 */
@Named("dashboardBean")
@RequestScoped
public class DashboardBean {
    
    @Inject
    private IssueManager issueManager;

    private List<IssueDTO> openIssue;

    private List<IssueDTO> inProgressIssue;

    private List<IssueDTO> resolvedIssue;
    
    private int numberOfOpen;
    
    private int numberOfInProgress;
    
    private int numberOfResolved;
    
    private BarChartModel barModel;
    
    @PostConstruct
    public void init() {
        openIssue = issueManager.getOpenIssues();
        inProgressIssue = issueManager.getInProgressIssues();
        resolvedIssue = issueManager.getResolvedIssues();
        
        numberOfOpen = openIssue.size();
        numberOfInProgress = inProgressIssue.size();
        numberOfResolved = resolvedIssue.size();
        
        createBarModel();
    }
    
    private void createBarModel(){
        
         barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Number of Issues In Each Month");

        List<Number> values = new ArrayList<>();
        values.add(65);
        values.add(59);
        values.add(80);
        values.add(81);
        values.add(56);
        values.add(55);
        values.add(40);
        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        barModel.setData(data);
        
    }

    public List<IssueDTO> getOpenIssue() {
        return openIssue;
    }

    public void setOpenIssue(List<IssueDTO> openIssue) {
        this.openIssue = openIssue;
    }

    public List<IssueDTO> getInProgressIssue() {
        return inProgressIssue;
    }

    public void setInProgressIssue(List<IssueDTO> inProgressIssue) {
        this.inProgressIssue = inProgressIssue;
    }

    public List<IssueDTO> getResolvedIssue() {
        return resolvedIssue;
    }

    public void setResolvedIssue(List<IssueDTO> resolvedIssue) {
        this.resolvedIssue = resolvedIssue;
    }

    public int getNumberOfOpen() {
        return numberOfOpen;
    }

    public void setNumberOfOpen(int numberOfOpen) {
        this.numberOfOpen = numberOfOpen;
    }

    public int getNumberOfInProgress() {
        return numberOfInProgress;
    }

    public void setNumberOfInProgress(int numberOfInProgress) {
        this.numberOfInProgress = numberOfInProgress;
    }

    public int getNumberOfResolved() {
        return numberOfResolved;
    }

    public void setNumberOfResolved(int numberOfResolved) {
        this.numberOfResolved = numberOfResolved;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    
}
