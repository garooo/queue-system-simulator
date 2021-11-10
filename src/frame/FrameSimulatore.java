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
    private JTextField lambdaTextField, muTextField, cTextField, durataTextField;
    private MMC dispositivoMMC;

    public FrameSimulatore(){
        super("Queue System Simulator");

        this.lambdaTextField = new JTextField();
        this.muTextField = new JTextField();
        this.cTextField = new JTextField();
        this.durataTextField = new JTextField();

        this.dispositivoMMC = null;
    }

    private void initMMC(float lambda, float mu, int c){
        this.dispositivoMMC = new MMC(lambda, mu, c);
    }

    public void setHP(){
        this.setSize(800,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLayout(new FlowLayout());

        this.add(this.setInputParams(), 0);

        this.setVisible(true);
    }

    private JPanel setInputParams(){
        JPanel inputParamsPanel = new JPanel();
        FlowLayout panelLayout = new FlowLayout(1, 20, 20);

        inputParamsPanel.setLayout(panelLayout);

        inputParamsPanel.add(this.inputNumberPanelLambda());
        inputParamsPanel.add(this.inputNumberPanelMu());
        inputParamsPanel.add(this.inputNumberPanelC());
        inputParamsPanel.add(this.inputNumberPanelTime());

        JButton send = new JButton("Calcola");

        send.addActionListener((e) -> {
            float lambda, mu, durata;
            int c;

            try{
                String lambdaStr = this.lambdaTextField.getText(), muStr = this.muTextField.getText(),
                  cStr = this.cTextField.getText(), durataStr = this.durataTextField.getText();

                lambdaStr = lambdaStr.replace(",", ".");
                muStr = muStr.replace(",", ".");
                cStr = cStr.replace(",", ".");
                durataStr = durataStr.replace(",", ".");

                lambda = Float.parseFloat(lambdaStr);
                mu = Float.parseFloat(muStr);
                c = Integer.parseInt(cStr);
                durata = Float.parseFloat(durataStr);
            } catch (Exception ex){
                lambda = 0;
                mu = 0;
                c = 0;
                durata = 0;
            }

            if(lambda != 0 && mu != 0 && c != 0 && durata != 0){
                this.initMMC(lambda, mu, c);

                Container currentPanel = this.getContentPane();

                if(currentPanel.getComponents().length > 1){
                    currentPanel.remove(2);
                    currentPanel.remove(1);
                }

                try{
                    this.add(this.createChart((int) (durata*1000)), 1);
                    this.add(this.getDispositivoDescription(2), 2);
                } catch (Exception ex){}

                this.revalidate();
                this.repaint();
            }
        });

        inputParamsPanel.add(send);

        return inputParamsPanel;
    }

    private JPanel inputNumberPanelLambda(){
        JPanel inputNumberPanel = new JPanel();
        inputNumberPanel.setLayout(new GridLayout(2, 1));

        inputNumberPanel.add(new JLabel("Lambda"));
        inputNumberPanel.add(this.lambdaTextField);

        return inputNumberPanel;
    }

    private JPanel inputNumberPanelMu(){
        JPanel inputNumberPanel = new JPanel();
        inputNumberPanel.setLayout(new GridLayout(2, 1));

        inputNumberPanel.add(new JLabel("Mu"));
        inputNumberPanel.add(this.muTextField);

        return inputNumberPanel;
    }

    private JPanel inputNumberPanelC(){
        JPanel inputNumberPanel = new JPanel();
        inputNumberPanel.setLayout(new GridLayout(2, 1));

        inputNumberPanel.add(new JLabel("Numero Servitori"));
        inputNumberPanel.add(this.cTextField);

        return inputNumberPanel;
    }

    private JPanel inputNumberPanelTime(){
        JPanel inputNumberPanel = new JPanel();
        inputNumberPanel.setLayout(new GridLayout(2, 1));

        inputNumberPanel.add(new JLabel("Durata (s)"));
        inputNumberPanel.add(this.durataTextField);

        return inputNumberPanel;
    }

    private JPanel createChart(int durata) throws Exception{
        if(this.dispositivoMMC == null)
            throw new Exception("dispositivoMMC dev'essere inizializzato");

        // Chart dataset
        final XYSeries mmcValues = new XYSeries( "MMC" );

        // Dati simulazione
        List<Pacchetto> pacchetti = this.dispositivoMMC.simulazione(durata);

        Map<Float, Integer> chartData = Pacchetto.getDataValuesChart(pacchetti);

        // Mapping da dati simulazione a dataset per il chart
        chartData.forEach((k, v) -> {
            mmcValues.add(k, v);
        });
        
        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries( mmcValues );

        // Chart
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
            "MMC Values",
            "Secondi",
            "Pacchetti nel sistema" ,
            dataset,
            PlotOrientation.VERTICAL,
            false, true, false
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