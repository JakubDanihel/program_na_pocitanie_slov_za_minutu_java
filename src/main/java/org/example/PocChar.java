import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class PocChar extends JFrame implements ActionListener {
    //urcenie potrebnych vlastnosti
    JLabel label1, label2, label3;
    JTextArea area;
    JButton button, pozadieFarba, text;

    private long startTime; // premenna pre ulozenie pociatocneho casu
    private int wordCount;  // premenna pre ulozeniu poctu slov


    //nastavenie kde sa nachadzaju jednotlive objekty
    PocChar() {
        super("Pocitadlo slov");
        label1 = new JLabel("Znaky");
        label1.setBounds(50, 50, 100, 20);

        label3 = new JLabel("Pocet slov za minutu");
        label3.setBounds(50, 30, 120, 20);

        label2 = new JLabel("Slova");
        label2.setBounds(50, 80, 100, 20);

        area = new JTextArea();
        area.setBounds(50, 110, 300, 200);

        button = new JButton("Klik");
        button.setBounds(50, 320, 80, 30);
        button.addActionListener(this);

        pozadieFarba = new JButton("Farba pozadia");
        pozadieFarba.setBounds(150, 320, 110, 30);
        pozadieFarba.addActionListener(this);

        text = new JButton("Farba textu");
        text.setBounds(260, 320, 110, 30);
        area.setLineWrap(true);
        text.addActionListener(this);

        add(label1);
        add(label2);
        add(label3);
        add(area);
        add(button);
        add(pozadieFarba);
        add(text);

        setSize(512, 512);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    //nastavenie zmeny farby pozadia a textu
    public void actionPerformed(ActionEvent e) {
        //po stlaceni tlacidla klik mozme zacat pisat slova a nasledne sa vyrata kolko slov pouzivatel napisal za minutu
        if (e.getSource() == button) {
            if (startTime == 0) {
                startTime = System.currentTimeMillis(); // ulozenie casu pokial sa jedna o prve pouzitie
            }

            String text = area.getText();
            String slova[] = text.split("\\s");

            wordCount = slova.length;
            label2.setText("Slova: " + wordCount);

            updateSlovaZaMinutu(); // metoda pre vypocitanie slov za minutu

        } else if (e.getSource() == pozadieFarba) {
            Color c = JColorChooser.showDialog(this, "Vyber farbu", Color.BLACK);
            pozadieFarba.setBackground(c);
            area.setBackground(c);
        } else if (e.getSource() == text) {
            Color c = JColorChooser.showDialog(this, "Vyber farbu", Color.BLACK);
            area.setForeground(c);
        }
    }

    //metoda pre vypocet slov za minutu
    private void updateSlovaZaMinutu() {
        if (startTime != 0) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = (currentTime - startTime) / 1000; // Prevedieme na sekundy
            int wordsPerMinute = (int) ((wordCount * 60) / elapsedTime); // Vypočítame slová za minútu
            label3.setText("Slova: " + wordCount + " Slov za minutu: " + wordsPerMinute);
        }
    }


    public static void main(String[] args) {
        new PocChar();
    }
}
