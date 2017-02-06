package main.logic;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextReaderGui {
    private JTextPane inputPane;
    private JTextArea infoTextArea;
    private JTextArea wordsTextArea;
    private JTextArea wordsResultTextArea;
    private JTextArea charTextArea;
    private JTextArea charResultTextArea;
    private JButton startButton;
    private JPanel workPanel;
    private JScrollPane scrollPane;

    private TextReaderGui() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wordsResultTextArea.setText("" + TextReader.countWords(inputPane.getText()));
                charResultTextArea.setText("" + TextReader.countChar(inputPane.getText()));
                inputPane.setText("");
            }
        });
    }

    static void startGui(){
        JFrame frame = new JFrame("TextReader");
        TextReaderGui textReaderGui = new TextReaderGui();
        frame.setContentPane(textReaderGui.workPanel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
