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
            setSize(350, 150);
            setLayout(new BorderLayout());

            JLabel confirmLabel = new JLabel(
                    "Do you want to save the file before exit the program?", SwingConstants.CENTER);
            add(confirmLabel, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(this);
            buttonPanel.add(saveButton);

            JButton doNotSaveButton = new JButton("Don't save");
            doNotSaveButton.addActionListener(this);
            buttonPanel.add(doNotSaveButton);

            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(this);
            buttonPanel.add(cancelButton);

            add(buttonPanel, BorderLayout.SOUTH);
            setLocationRelativeTo(null);
            pack();
        }

        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();

            if (action.equals("Save")) {
                SaveCommand save = new SaveCommand(editor);
                save.execute();
                System.exit(0);
            } else if (action.equals("Don't save")) {
                System.exit(0);
            } else {
                dispose();
            }

        }
    }
}
