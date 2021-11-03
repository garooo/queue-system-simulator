package frame;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 

import sistemiACoda.MMC;
import pacchetto.Pacchetto;

public class FrameSimulatore extends JFrame {
    private MMC dispositivoMMC;

    public FrameSimulatore(){
        super("Queue System Simulator");

        this.dispositivoMMC = null;
    }

    private void initMMC(){
        int lambda = 7;
        int mu = 2;
        int k = 10;

        this.dispositivoMMC = new MMC(lambda, mu, 4);

        System.out.println("** M/M/C Description ** \n");
        System.out.println("rho = " +this.dispositivoMMC.rho());
        System.out.println("Probabilita che il sistema sia vuoto = " +this.dispositivoMMC.P0());
        System.out.println("Probabilita che ci siano 10 pkt in coda = " +this.dispositivoMMC.PK(k));
        System.out.println("Numero medio di pkt nel sistema = " +this.dispositivoMMC.LS());
        System.out.println("Numero medio di pkt in coda = " +this.dispositivoMMC.LQ());
        System.out.println("Tempo medio in attesa nel sistema = " +this.dispositivoMMC.WS());
        System.out.println("Tempo medio in coda = " +this.dispositivoMMC.WQ());
        System.out.println("Numero medio di utenti serviti = " +this.dispositivoMMC.LX());
        System.out.println("Probabilita che il pkt entra in coda = " +this.dispositivoMMC.Coda());
    }

    public void setHP(){
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new GridLayout(1, 1));

        // this.add(this.setInputParams());

        this.initMMC();

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
        // Chart dataset
        final XYSeries mmcValues = new XYSeries( "MMC" );

        // Dati simulazione
        List<Pacchetto> pacchetti = this.dispositivoMMC.simulazione(100000);

        Map<Integer, Integer> chartData = Pacchetto.getDataValuesChart(pacchetti);

        // Mapping da dati simulazione a dataset per il chart
        chartData.forEach((k, v) -> {
            mmcValues.add(k, v);
        });
        
        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries( mmcValues );

        // Chart
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
            "MMC Values",
            "Dispositivi",
            "" ,
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );

        ChartPanel chartPanel = new ChartPanel(xylineChart);

        return chartPanel;
    }
}