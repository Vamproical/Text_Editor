package editor.font;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import editor.SaveSettings;
import editor.TextEditor;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicReference;

public class SetFont extends JDialog {
    private final TextEditor editor;
    private final String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    AtomicReference<String> font = new AtomicReference<>();
    AtomicReference<Integer> size = new AtomicReference<>();

    public SetFont(TextEditor editor) {
        super(editor, "Font", true);
        this.editor = editor;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout());

        JLabel fontLabel = new JLabel("Font: ");
        JComboBox comboBoxFonts = new JComboBox(fonts);
        comboBoxFonts.setPreferredSize(new Dimension(135,25));
        font.set((String) comboBoxFonts.getSelectedItem());
        comboBoxFonts.addActionListener(e -> {
            JComboBox box = (JComboBox) e.getSource();
            font.set((String) box.getSelectedItem());
        });
        comboPanel.add(fontLabel);
        comboPanel.add(comboBoxFonts);

        JLabel sizeLabel = new JLabel("Size: ");

        JSpinner fontSize = new JSpinner();
        fontSize.setPreferredSize(new Dimension(50, 25));
        fontSize.setValue(12);
        size.set((Integer) fontSize.getValue());
        fontSize.addChangeListener(e -> size.set((Integer) fontSize.getValue()));
        comboPanel.add(sizeLabel);
        comboPanel.add(fontSize);
        add(comboPanel, BorderLayout.LINE_START);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(e -> {
            String action = e.getActionCommand();
            if (action.equals("Ok")) {
                setFonts(editor, font.get(), size.get());
                setVisible(false);
            } else {
                dispose();
            }
        });
        buttonPanel.add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            String action = e.getActionCommand();
            if (action.equals("Ok")) {
                setFonts(editor, font.get(), size.get());
                setVisible(false);
            } else {
                dispose();
            }
        });
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.AFTER_LAST_LINE);
        setPreferredSize(new Dimension(300, 105));
        setLocationRelativeTo(null);
        pack();
    }

    private void setFonts(TextEditor editor, String font, Integer size) {
        System.out.println("Font " + font + " size " + size);
        Font f = new Font(font, Font.PLAIN, size);
        SaveSettings saveSettings = new SaveSettings();
        saveSettings.save(font, size);
        editor.getTextArea().setFont(f);
    }
}
