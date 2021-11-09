package frame;
import javax.swing.*;

import java.awt.Container;
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

    private void initMMC(int lambda, int mu, int c){
        this.dispositivoMMC = new MMC(lambda, mu, c);
    }

    public void setHP(){
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new GridLayout(2, 2));

        this.add(this.setInputParams(), 0);

        JButton send = new JButton("Calcola");

        this.add(send, 1);

        send.addActionListener((e) -> {
            this.initMMC(7, 2, 4);

            Container currentPanel = this.getContentPane();

            if(currentPanel.getComponents().length > 2){
                currentPanel.remove(3);
                currentPanel.remove(2);
            }

            try{
                this.add(this.createChart(), 2);
                this.add(this.getDispositivoDescription(2), 3);
            } catch (Exception ex){}

            this.revalidate();
            this.repaint();
        });

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

    private JPanel createChart() throws Exception{
        if(this.dispositivoMMC == null)
            throw new Exception("dispositivoMMC dev'essere inizializzato");

        // Chart dataset
        final XYSeries mmcValues = new XYSeries( "MMC" );

        // Dati simulazione
        List<Pacchetto> pacchetti = this.dispositivoMMC.simulazione(5000);

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

    private JPanel getDispositivoDescription(int k) throws Exception{
        if(this.dispositivoMMC == null)
            throw new Exception("dispositivoMMC dev'essere inizializzato");

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(10, 1));

        panel.add(new JLabel("** M/M/C Description **"));

        panel.add(new JLabel("rho = " + this.dispositivoMMC.rho()));
        panel.add(new JLabel("Probabilita che il sistema sia vuoto = " +this.dispositivoMMC.P0()));
        panel.add(new JLabel("Probabilita che ci siano 10 pkt in coda = " +this.dispositivoMMC.PK(k)));
        panel.add(new JLabel("Numero medio di pkt nel sistema = " +this.dispositivoMMC.LS()));
        panel.add(new JLabel("Numero medio di pkt in coda = " +this.dispositivoMMC.LQ()));
        panel.add(new JLabel("Tempo medio in attesa nel sistema = " +this.dispositivoMMC.WS()));
        panel.add(new JLabel("Tempo medio in coda = " +this.dispositivoMMC.WQ()));
        panel.add(new JLabel("Numero medio di utenti serviti = " +this.dispositivoMMC.LX()));
        panel.add(new JLabel("Probabilita che il pkt entra in coda = " +this.dispositivoMMC.Coda()));

        return panel;
    }
}