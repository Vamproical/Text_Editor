package editor;

import editor.command.SaveCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowsListener extends WindowAdapter {
    private final TextEditor editor;

    public WindowsListener(TextEditor editor) {
        this.editor = editor;
    }

    public void windowClosing(WindowEvent e) {
        if (editor.getText().isBlank()) {
            System.exit(0);
        } else {
            ConfirmWindow checker = new ConfirmWindow();
            checker.setVisible(true);
        }
    }

    private class ConfirmWindow extends JFrame implements ActionListener {
        public ConfirmWindow() {
            setSize(250, 100);
            setLayout(new BorderLayout());

            JLabel confirmLabel = new JLabel(
                    "Do you want to save the file before exit the program?", SwingConstants.CENTER);
            add(confirmLabel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());

            JButton exitButton = new JButton("Yes");
            exitButton.addActionListener(this);
            buttonPanel.add(exitButton);

            JButton cancelButton = new JButton("No");
            cancelButton.addActionListener(this);
            buttonPanel.add(cancelButton);

            add(buttonPanel, BorderLayout.SOUTH);
        }

        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();

            if (action.equals("Yes")) {
                SaveCommand save = new SaveCommand(editor);
                save.execute();
            }
            System.exit(0);
        }
    }
}
