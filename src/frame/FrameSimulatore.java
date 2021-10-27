package frame;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;

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
}