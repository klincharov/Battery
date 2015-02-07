import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class BatteryGUI extends JFrame {

    private JButton MODERATEButton;
    private JButton ENERGYSAVINGButton;
    private JButton DEFAULTSButton;
    private JPanel gui;
    private JButton quitButton;

    public BatteryGUI() {
        initUI();
    }

    public final void initUI() {

        JPanel gui = new JPanel();
        getContentPane().add(gui);
        //gui.setLayout(BorderLayout);
        setSize(150, 170);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setLayout(new BoxLayout(gui,BoxLayout.LINE_AXIS));
        //pack();

        gui.add(DEFAULTSButton);
        gui.add(ENERGYSAVINGButton);
        gui.add(MODERATEButton);
        gui.add(quitButton);

        DEFAULTSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    runCommand(0);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        ENERGYSAVINGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    runCommand(1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        MODERATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    runCommand(2);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                }
        });
    }

    private static void runCommand(int n) throws IOException {
        String dir = System.getProperty("user.dir"); //current working directory

        final int[] args = {0, 12, 8, 16}; //16 currently not used

        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", dir+"\\nvidiaInspector.exe -forcePstate:0," + args[n]);
                //"cmd.exe", "/c", "C:\\nvidiaInspector\\nvidiaInspector.exe -forcePstate:0," + args[n]);
        builder.start();
        }

    public static void main(String[] args) {
        BatteryGUI gui = new BatteryGUI();
        gui.setVisible(true);
    }
}

