package editor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TextEditor extends JFrame {
    public TextEditor() {
        JTextArea area = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(area);

        JPanel mainPanel = new JPanel();
        add(mainPanel);
        Border marginBorder = new EmptyBorder(new Insets(15,15,15,15));
        mainPanel.setBorder(marginBorder);
        mainPanel.setLayout(new BorderLayout());

        scrollPane.setName("ScrollPane");
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setName("TextArea");

        JTextField fieldForSaveOrLoad = new JTextField();
        fieldForSaveOrLoad.setName("FilenameField");
        JButton saveButton = new JButton("Save");
        saveButton.setName("SaveButton");
        JButton loadButton = new JButton("Load");
        loadButton.setName("LoadButton");
        loadButton.addActionListener(actionEvent -> area.setText(LoadSave.load(fieldForSaveOrLoad.getText())));
        saveButton.addActionListener(actionEvent -> LoadSave.save(area.getText(),fieldForSaveOrLoad.getText()));


        createLayout(scrollPane);
        setTitle("The first stage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setTitle("TextArea");
        setVisible(true);
    }

    private void createLayout(JComponent... arg) {

        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);
        gl.setAutoCreateGaps(true);

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addComponent(arg[0])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addComponent(arg[0])
        );
        pack();
    }
}
