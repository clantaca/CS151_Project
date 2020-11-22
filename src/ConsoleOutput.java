import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

public class ConsoleOutput extends OutputStream {
    private JTextArea textArea;

    public ConsoleOutput(JTextArea textArea) {
        this.textArea = textArea;
    }

    // overwrites output into textarea
    @Override
    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char)b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
