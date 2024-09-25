package raf.dsw.gerumap.gui.swing.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StylePickerDialog extends JDialog {
    private JTextField nameField;
    private JTextField strokeField;
    private String name;
    private String stroke;
    private Color color;
    public StylePickerDialog(boolean isNameEditable){
        super(MainFrame.getInstance(), "Picker", true);
        setLayout(new BorderLayout());
        nameField = new JTextField("Change name...");
        nameField.setEditable(isNameEditable);
        strokeField = new JTextField("Type a number");
        color = null;
        JButton colorBtn = new JButton("Select a color");
        colorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                color = JColorChooser.showDialog(StylePickerDialog.this, "Select a color", Color.red);
            }
        });
        JButton btnApply = new JButton("Apply");
        btnApply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameField.getText();
                stroke = strokeField.getText();
                StylePickerDialog.this.setVisible(false);
            }
        });

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = null;
                stroke = null;
                color = null;
                StylePickerDialog.this.setVisible(false);
            }
        });
        setSize(new Dimension(350, 120));
        setTitle("Style picker");
        setLocationRelativeTo(MainFrame.getInstance());
        JPanel westPnael = new JPanel();
        westPnael.setLayout(new FlowLayout());
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new FlowLayout());
        westPnael.add(nameField);
        westPnael.add(strokeField);
        eastPanel.add(colorBtn);
        add(westPnael, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.add(btnApply);
        btnPanel.add(btnCancel);
        add(btnPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
