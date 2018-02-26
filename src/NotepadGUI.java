import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class NotepadGUI extends JFrame{
    private JPanel notePanel;
    private JTextArea textArea1;
    private JButton button1;
    private JButton öppnaButton;
    private JButton sparaButton;

    public NotepadGUI() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
            }
        });
        öppnaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser("C:\\Users\\" + System.getProperty("user.name" + "\\Documents"));
                if (fc.showOpenDialog(null) == fc.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    textArea1.setText("");
                    try {
                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            textArea1.setText(textArea1.getText() + scanner.nextLine() + "\n");
                        }
                        scanner.close();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        sparaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                if (fc.showSaveDialog(null) == fc.APPROVE_OPTION) {
                    File file  = fc.getSelectedFile();
                    if (file.exists()) {
                        int option = JOptionPane.showConfirmDialog(null, "File already exists. Do you want to replace it?", "File exists", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (option == 0) {
                            try {
                                PrintWriter output = new PrintWriter(file);
                                for (String line : textArea1.getText().split("\\n")) output.println(line);
                                System.out.println(textArea1.getText());
                                output.flush();
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }
                        }
                    } else {
                        try {
                            file.createNewFile();
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }
                    }
                }
            }
        });
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("NotepadGUI");
        frame.setContentPane(new NotepadGUI().notePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,600);
    }
}
