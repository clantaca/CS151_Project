import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;

public class NotificationView extends JFrame {

    private int fWidth = 450;
    private int fHeight = 220;
    private int border = 10;

    private JPanel nPanel;
    private JLabel nLabel;

    private JPanel cPanel;

    private JTextArea textArea;
    private PrintStream standardOut;


    public NotificationView() {
        createFrame();
        createNorth();
        createCenter();
        this.setVisible(true);
    }

    public void createFrame() {
        this.setSize(fWidth, fHeight);
        this.setLayout(new BorderLayout());
        this.setTitle("Activity Log");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    // displays header
    public void createNorth() {
        nPanel = new JPanel();
        nPanel.setLayout(new GridBagLayout());
        //nPanel.setLayout(new BoxLayout(nPanel, BoxLayout.PAGE_AXIS));
        nPanel.add(Box.createRigidArea(new Dimension(border, border)));
        nPanel.setSize(100,100);
        nPanel.setBackground(Color.YELLOW);

        nLabel = new JLabel("Activity Log");
        nLabel.setHorizontalAlignment(JLabel.CENTER);
        nPanel.add(nLabel);
        this.add(nPanel, BorderLayout.NORTH);
    }
    // displays activity log information
    public void createCenter() {
        cPanel = new JPanel();
        cPanel.setLayout(new BoxLayout(cPanel, BoxLayout.PAGE_AXIS));
        cPanel.add(Box.createRigidArea(new Dimension(border, border)));
        cPanel.setSize(100,100);
        cPanel.setBackground(Color.LIGHT_GRAY);

        textArea = new JTextArea(50,10);
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new ConsoleOutput(textArea));
        standardOut = System.out;
        System.setOut(printStream);
        JScrollPane scrollPane = new JScrollPane(textArea);
        //scrollPane.setBounds(10,60,450,220);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        cPanel.setVisible(true);
        cPanel.add(textArea);
        this.add(cPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {

        new NotificationView();
    }
}

