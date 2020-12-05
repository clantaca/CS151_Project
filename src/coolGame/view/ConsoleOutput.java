package coolGame.view;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * ConsoleOutput that displays the outputstream onto a textArea
 *
 * Author: Nam Ha Minh https://www.codejava.net/java-se/swing/redirect-standard-output-streams-to-jtextarea
 */
public class ConsoleOutput extends OutputStream {
    private JTextArea textArea;

    /**
     * Constructor that initializes the textarea
     *
     * @param textArea
     */
    public ConsoleOutput(JTextArea textArea) {
        this.textArea = textArea;
    }

    /**
     * Overwrites the output and appends text into the textarea
     *
     * @param b
     * @throws IOException
     */
    @Override
    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char) b));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
