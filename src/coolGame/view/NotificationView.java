package coolGame.view;

import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;

/**
 * View that displays all the activity that occurs throughout gameplay; essential outputs the printsream onto a textArea that
 * is called the Activity Log
 */
public class NotificationView extends JFrame {

    private JTextArea textArea;
    private JPanel jpanel;
    private JLabel jlabel;
    private JScrollPane scrollPane;

    /**
     * Constructor that creates the textArea and sets the console output to display on the activity log
     */
    public NotificationView() {
        super("Activity Log");

        // displays activity log title
        jlabel = new JLabel();
        jlabel.setText("Activity Log");
        jpanel = new JPanel();
        jpanel.setBackground(Color.YELLOW);
        jpanel.add(jlabel);

        // displays textarea with output stream
        textArea = new JTextArea(10, 10);
        textArea.setEditable(false);
        System.setOut(new PrintStream(new ConsoleOutput(textArea)));
        setLayout(new BorderLayout());
        setBounds(100, 100, 500, 300);

        // adds scrollbar to textarea
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(jpanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 1200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Main test to display system.out.print onto the Activity Log
     * @param args
     */
    public static void main(String[] args) {
        new NotificationView();
        System.out.println("This is a test");
        System.out.println("Hello");
    }
}

