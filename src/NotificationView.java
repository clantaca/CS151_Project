import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;

public class NotificationView extends JFrame {

    private JTextArea textArea;
    private JPanel jpanel;
    private JLabel jlabel;
    private JScrollPane scrollPane;

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
        setSize(450, 220);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new NotificationView();
        System.out.println("This is a test");
        System.out.println("Hello");
    }
}

