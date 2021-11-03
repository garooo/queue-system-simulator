package frame;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class FrameSimulatore extends JFrame {
    public FrameSimulatore(){
        super("Queue System Simulator");
    }

    public void setHP(){
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new GridLayout(1, 1));

        this.add(this.setInputParams());

        this.add(this.createChart());

        this.setVisible(true);
    }

    private JPanel setInputParams(){
        JPanel inputParamsPanel = new JPanel();
        FlowLayout panelLayout = new FlowLayout();

        inputParamsPanel.setLayout(panelLayout);

        inputParamsPanel.add(this.inputNumberPanel("Arrivals Occur", 0));
        inputParamsPanel.add(this.inputNumberPanel("Service times", 0));
        inputParamsPanel.add(this.inputNumberPanel("Packages number", 0));

        return inputParamsPanel;
    }

    private JPanel inputNumberPanel(String inputLabel, float placeholder){
        JPanel inputNumberPanel = new JPanel();
        inputNumberPanel.setLayout(new GridLayout(2, 1));

        inputNumberPanel.add(new JLabel(inputLabel));
        inputNumberPanel.add(new JTextField(Float.toString(placeholder)));

        return inputNumberPanel;
    }

    private JPanel createChart(){
        DefaultPieDataset dataSet = new DefaultPieDataset();
        dataSet.setValue("Chrome", 29);
        dataSet.setValue("InternetExplorer", 36);
        dataSet.setValue("Firefox", 35);
       
        // based on the dataset we create the chart
        JFreeChart pieChart = ChartFactory.createPieChart3D("test", dataSet, true, true, false);
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setStartAngle(290);
        plot.setForegroundAlpha(0.5f);

        // Adding chart into a chart panel
        ChartPanel chartPanel = new ChartPanel(pieChart);

        return chartPanel;
    }
}