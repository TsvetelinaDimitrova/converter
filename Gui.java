package zahlenumrechner;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Gui extends JFrame implements ActionListener{
    private static Gui instance;
    private String error;
    // Deklaration der notwendigen (Objekt)variablen
    private JFrame umrechner;
    // Deklaration Startcontainer
    private JPanel jp;
    private JTextField eingabe, 
            aus_dual, aus_oktal, aus_decimal, aus_hexadecimal;
    //private int h = 25;
    private JRadioButton dual, oktal,decimal, hexadecimal;
    private ButtonGroup zahlensystem;
    private JButton berechnen, reset, neu;
    private JLabel lbl_dual, lbl_oktal, lbl_decimal, lbl_hexadecimal;
    
    /** 
     * Konstruktor
     * Erstellen des Hauptanwendungsfensters
     */
    private Gui(){
        // JFrame-Objekt erzeugen
        umrechner = new JFrame("Zahlenumrechner");
        // Größe setzen
        umrechner.setSize(400,300);
        // Optional: Anzeige in der Bildschirmmitte
        umrechner.setLocationRelativeTo(null);
        // Prozess beim Beenden schließen
        umrechner.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // umrechner.setDefaultCloseOperation(3);
        // Methode für Menü
        // Methode für Startcontainer
        umrechner.add(getStartcontainer());
        // Wichtig: Sichtbar machen des Fensters als letze Anweisung
        umrechner.setVisible(true);
    }
    
    /**
     * Erstellen des Startcontainers für den Zahlenumrechner
     * @return JPanel mit den Bedienelementen
     */
    private JPanel getStartcontainer(){
    //Schritt 1: Definieren des Containers
        jp = new JPanel();
        jp.setLayout(null);
        // jp.setBackground(Color.red);
        
    // Scritt 2: Definieren der Bedienelemente
        eingabe = new JTextField();
        //eingabe.setBounds(50, 50, 200, h);
        eingabe.setBounds(50, 50, 200, 25);// Große und Position  von textfeld
        eingabe.setToolTipText("Zahl die umgerechnet werden soll");
        zahlensystem = new ButtonGroup();
        dual = new JRadioButton("Dual");
        dual.setBounds(50, 80, 75, 25);
        oktal = new JRadioButton("Oktal");
        oktal.setBounds(125, 80, 75, 25);
        decimal = new JRadioButton("Dezimal");
        decimal.setBounds(200, 80, 75, 25);
        hexadecimal = new JRadioButton("Hexadezimal");
        hexadecimal.setBounds(275, 80, 100, 25);
        zahlensystem.add(dual);
        zahlensystem.add(oktal);
        zahlensystem.add(decimal);
        zahlensystem.add(hexadecimal);
        berechnen = new JButton("Berechnung");
        berechnen.setBounds(50,110,125,25);
        berechnen.addActionListener(this);
        reset = new JButton("Reset");
        reset.setBounds(200,110,125,25);
        reset.addActionListener(this);
        neu = new JButton("Neu");
        neu.setBounds(200,110,125,25);
        neu.setVisible(false);
        neu.addActionListener(this);
        lbl_dual = new JLabel("Dual");
        lbl_dual.setVisible(false);
        lbl_oktal = new JLabel("Oktal");
        lbl_oktal.setVisible(false);
        lbl_decimal = new JLabel("Decimal");
        lbl_decimal.setVisible(false);
        lbl_hexadecimal = new JLabel("Hexadecimal");
        lbl_hexadecimal.setVisible(false);
        aus_dual = new JTextField();
        aus_dual.setEditable(false);
        aus_dual.setVisible(false);
        aus_oktal = new JTextField();
        aus_oktal.setEditable(false);
        aus_oktal.setVisible(false);
        aus_decimal = new JTextField();
        aus_decimal.setEditable(false);
        aus_decimal.setVisible(false);
        aus_hexadecimal = new JTextField();
        aus_hexadecimal.setEditable(false);
        aus_hexadecimal.setVisible(false);
        
    //Schritt 3: Hinzufügen der Bedienelemente
        jp.add(eingabe);
        jp.add(dual);
        jp.add(oktal);
        jp.add(decimal);
        jp.add(hexadecimal);
        jp.add(berechnen);
        jp.add(neu);
        jp.add(reset);
        jp.add(lbl_dual);
        jp.add(lbl_oktal);
        jp.add(lbl_decimal);
        jp.add(lbl_hexadecimal);
        jp.add(aus_dual);
        jp.add(aus_oktal);
        jp.add(aus_decimal);
        jp.add(aus_hexadecimal);
        
    //Schritt 4: Container mit return zurückgeben
        return jp;
    }
            
    /**
     * Prüfung, ob bereits ein Gui-Objekt existiert
     * 
     * @return Gui-Instance
     */
    
    public static Gui pruefeInstanz(){
        if (Gui.instance == null){
            Gui.instance = new Gui();
        }
        return Gui.instance;
    }
    
    /**
     * Ermitteln des selektierten RadioButtons
     * @return Nummer des selektierten RadioButtons
     */
    private int getSelectedRadiobutton(){
        int radio = 0;
        if (dual.isSelected()){
            radio = 1;
        }
        if (oktal.isSelected()){
            radio = 2;
        }
        if (decimal.isSelected()){
            radio = 3;
        }
        if (hexadecimal.isSelected()){
            radio = 4;
        }
        return radio;
    }
    
    /**
     * Prüft, ob die eingegebene Zahl zum Zahlensystem passt
     * @param radio Status des Radiobuttons
     * @return true bei Stimmigkeit, false bei Fehler
     */
    private boolean pruefeZahlenbereich(int radio){
        boolean pruef = true;
        char[] ziffern = eingabe.getText().trim().toLowerCase().toCharArray();
        for (int i = 0; i < ziffern.length; i++){
            switch(ziffern[i]){
                case '0': break;
                case '1': break;
                case '2': 
                case '3': 
                case '4':
                case '5':
                case '6':
                case '7': 
                    if (radio == 1){
                        pruef = false;
                    }
                    break;
                case '8':
                case '9':
                    if (radio == 1 || radio == 2){
                        pruef = false;
                    }
                    break;
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                    if (radio != 4){
                        pruef = false;
                    }
                    break;
                default: 
                    pruef = false;
                    break;                              
            }
        }
        if(!pruef) {
        error = "Eingegebene Zahl passt nicht zum ausgewählten Zahlenbereich";
        }
        return pruef;
    }
    
    /**
     * Prüfen der Eingabe
     * @param radio Status des ausgewählte RadioButtons
     * @return Ergebnis der Prüfung true oder false
     */
    private boolean pruefeEingabe(int radio){
        boolean pruef = true;
        // Prüfung Zahlensystem
        pruef = pruefeZahlenbereich(radio);
        
        // Prüfung Auswahl RadioButton
        if (radio == 0){
            pruef = false;
            error = "Es wurde kein Zahlensystem ausgewählt.";
        }
        
        // Prüfung, ob ein Eintrag im Textfeld erfolgt ist.
        if(eingabe.getText().trim().isEmpty()){
            pruef = false;
            error = "Es wurde keine Zahl eingegeben.";
        }
        return pruef;
    }
    /**
     * Ausgabe der Fehlermeldung
     */
    private void showErrorMessage(){
        JOptionPane.showMessageDialog(null, error, "Osi-Schicht 8 Mitteilung",
                JOptionPane.OK_OPTION);
    }
    
    /**
     * Methode zum ein- und ausblenden der Ausgabeelemente
     * @param b true oder false (ein oder ausblenden)
     * @param radio status der Radiobuttons
     */    
    private void setAusgabe(boolean b, int radio){
        if(b){
            // Anweisung zum Einblenden
            switch (radio){
                case 1:
                    lbl_oktal.setVisible(b);
                    lbl_decimal.setVisible(b);
                    lbl_hexadecimal.setVisible(b);
                    aus_oktal.setVisible(b);
                    aus_decimal.setVisible(b);
                    aus_hexadecimal.setVisible(b);
                    lbl_oktal.setBounds(50,140,125,25);
                    aus_oktal.setBounds(200,140,125,25);
                    lbl_decimal.setBounds(50,170,125,25);
                    aus_decimal.setBounds(200,170,125,25);
                    lbl_hexadecimal.setBounds(50,200,125,25);
                    aus_hexadecimal.setBounds(200,200,125,25);
                    break;
                case 2:
                    lbl_dual.setVisible(b);
                    lbl_decimal.setVisible(b);
                    lbl_hexadecimal.setVisible(b);
                    aus_dual.setVisible(b);
                    aus_decimal.setVisible(b);
                    aus_hexadecimal.setVisible(b);
                    lbl_dual.setBounds(50,140,125,25);
                    aus_dual.setBounds(200,140,125,25);
                    lbl_decimal.setBounds(50,170,125,25);
                    aus_decimal.setBounds(200,170,125,25);
                    lbl_hexadecimal.setBounds(50,200,125,25);
                    aus_hexadecimal.setBounds(200,200,125,25);
                    break;
                case 3:
                    lbl_dual.setVisible(b);
                    lbl_oktal.setVisible(b);
                    lbl_hexadecimal.setVisible(b);
                    aus_dual.setVisible(b);
                    aus_oktal.setVisible(b);
                    aus_hexadecimal.setVisible(b);
                    lbl_dual.setBounds(50,140,125,25);
                    aus_dual.setBounds(200,140,125,25);
                    lbl_oktal.setBounds(50,170,125,25);
                    aus_oktal.setBounds(200,170,125,25);
                    lbl_hexadecimal.setBounds(50,200,125,25);
                    aus_hexadecimal.setBounds(200,200,125,25);
                    break;
                default:
                    lbl_dual.setVisible(b);
                    lbl_oktal.setVisible(b);
                    lbl_decimal.setVisible(b);
                    aus_dual.setVisible(b);
                    aus_oktal.setVisible(b);
                    aus_decimal.setVisible(b);
                    lbl_dual.setBounds(50,140,125,25);
                    aus_dual.setBounds(200,140,125,25);
                    lbl_oktal.setBounds(50,170,125,25);
                    aus_oktal.setBounds(200,10,125,25);
                    lbl_decimal.setBounds(50,200,125,25);
                    aus_decimal.setBounds(200,200,125,25);
                    break;
            }
        }else{
            //Anweisungen zum Ausblenden
            lbl_dual.setVisible(b);
            lbl_oktal.setVisible(b);
            lbl_decimal.setVisible(b);
            lbl_hexadecimal.setVisible(b);
            aus_dual.setVisible(b);
            aus_oktal.setVisible(b);
            aus_decimal.setVisible(b);
            aus_hexadecimal.setVisible(b);            
            eingabe.setText("");
            zahlensystem.clearSelection();
        }
        reset.setVisible(!b);
        neu.setVisible(b);
        eingabe.setEditable(!b);
        berechnen.setEnabled(!b);
        dual.setEnabled(!b);
        oktal.setEnabled(!b);
        decimal.setEnabled(!b);
        hexadecimal.setEnabled(!b);
    }
    
    /**
     * Rechnet eine Dezimalzahl in ein anderes Zahlensystem um
     * @param wert Zahl, welche umgerechnet werden soll
     * @param zs Zahlensystem, in welches umgerechnet werden soll
     * @return Zahl des Zielzahlensystems als String
     */
    private String fromDecimal(int wert, int zs){
        String rueck = "";
        while (wert > 0){
            int rest = wert % zs;
            wert = wert / zs;
            switch(rest){
                case 10: rueck = 'A' + rueck; break;
                case 11: rueck = 'B' + rueck; break;
                case 12: rueck = 'C' + rueck; break;
                case 13: rueck = 'D' + rueck; break;
                case 14: rueck = 'E' + rueck; break;
                case 15: rueck = 'F' + rueck; break;
                default:
                    rueck = rest + rueck; break;
            }  
        }
        return rueck;
    }
    /**
     * Methode, welche eine Zahl aus einem Zahlensystem in eine Dezimalzahl
     * umrechnen
     * @param zsUnsprungszahlensystem (2 dual, 8 oktal, 16 hexadezimal)
     * @return errechnete Dezimalzahl 
     */
    private int toDecimal(int zs){
        int summe = 0;
        int expo = 0;
        int multi = 0;
        char[] zahl = eingabe.getText().trim().toLowerCase().toCharArray();
        int lang = zahl.length-1;
        for (int i = lang;i>= 0; i--){
            if (zs == 16){
                switch(zahl[i]){
                    case 'a':
                        multi = 10;
                        break;
                    case 'b':
                        multi = 11;
                        break;
                    case 'c': 
                        multi = 12;
                        break;
                    case 'd':
                        multi = 13;
                        break;
                    case 'e':
                        multi = 14;
                        break;
                    case 'f':
                        multi = 15;
                        break;
                    default:
                        multi = Integer.valueOf(String.valueOf(zahl[i]));
                }
            }else{
                multi = Integer.valueOf(String.valueOf(zahl[i]));
            }
            summe = summe + multi * (int)Math.pow(zs, expo);
            expo++;
        }              
        return summe;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == berechnen){
            // Funktionalität die ausgeführt werden sollen
            int radiobutton = getSelectedRadiobutton();
            boolean pruef = pruefeEingabe(radiobutton);
            if(pruef){
                setAusgabe(true, radiobutton);
                switch(radiobutton){
                    case 1:
                        aus_oktal.setText(fromDecimal(toDecimal(2),8));
                        aus_decimal.setText(String.valueOf(toDecimal(2)));
                        aus_hexadecimal.setText(fromDecimal(toDecimal(2),16));
                        break;
                    case 2:
                        aus_dual.setText(fromDecimal(toDecimal(8),2));
                        aus_decimal.setText(String.valueOf(toDecimal(8)));
                        aus_hexadecimal.setText(fromDecimal(toDecimal(8),16));
                        break;
                    case 3:
                        aus_dual.setText(fromDecimal(Integer.valueOf(eingabe.getText().trim()),2));
                        aus_oktal.setText(fromDecimal(Integer.valueOf(eingabe.getText().trim()),8));
                        aus_hexadecimal.setText(fromDecimal(Integer.valueOf(eingabe.getText().trim()),16));
                        break;
                    default: 
                        aus_dual.setText(fromDecimal(toDecimal(16),2));
                        aus_oktal.setText(fromDecimal(toDecimal(16),8));
                        aus_decimal.setText(String.valueOf(toDecimal(16)));
                        break;
                }
            }else{
                showErrorMessage();
            }           
        }
        if(e.getSource() == reset){
            //Auswahl(Selection) der ButtonGroup löschen
            zahlensystem.clearSelection();
            //Textfeldeintrag löschen
            eingabe.setText("");
        }
        if (e.getSource() == neu) {
            setAusgabe(false, 0);
        }
    }
}
