import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class BatteryGUI extends JFrame {

    private JButton MODERATEButton;
    private JButton ENERGYSAVINGButton;
    private JButton DEFAULTSButton;
    private JPanel gui;
    private JButton quitButton;

    public BatteryGUI()  {
        initUI();
    }
    public final void initUI()  {

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
        //key pressed listeners
        gui.setFocusable(true);
        gui.setFocusTraversalKeysEnabled(false); //traversal keys are TAB and SHIFT
        gui.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_D) try {
                    runCommand(0);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                else if(keyCode == KeyEvent.VK_E) try {
                    runCommand(1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                else if(keyCode == KeyEvent.VK_M) try {
                    runCommand(2);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                else if(keyCode == KeyEvent.VK_Q)    System.exit(0);
                else if(keyCode == KeyEvent.VK_ESCAPE)    System.exit(0);
            }
            @Override
            public void keyReleased(KeyEvent e) {}
            @Override
            public void keyTyped(KeyEvent e) {}
        });


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
        final String[] args = {"-setPStateLimit:0,0","-forcePstate:0,12","-setPStateLimit:0,8",}; //setPStateLimit limits the MAX clock value, otherwise should go to IDLE
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", dir+"\\nvidiaInspector.exe " + args[n]);
        builder.start();
        if(n == 0) printAndClose("Defaults set! ");
           else if(n == 1) printAndClose("Energy set! ");
           else if(n == 2) printAndClose("Moderate set! ");
        }

    private static void printAndClose(String mess) {
        JOptionPane.showMessageDialog(null, mess + "Closing!");
        System.exit(0);
    }

    public static void main(String[] args) {
        BatteryGUI gui = new BatteryGUI();
        gui.setVisible(true);
    }
}

