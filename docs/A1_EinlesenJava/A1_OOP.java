import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * @author B.Sauerstein
 * Gymnasium Ricarda-Huch-Schule Braunschweig 2025
 * Besonders bei der Verwendung von BlueJ geeignet.
 */
public class A1_OOP{    
    String text;
    public A1_OOP(){
        // Variante mit SimpleInput
        //SimpleInput input = new SimpleInput();
        //String datei = input.getString("Bitte Dateipfad für Loesung eingeben!");

        //alternativ ohne SimpleInput
        //die Datei einfach im Projektordner speichern, dann reicht das hier:
        String datei = "drehfreudig01.txt";
        File file = new File(datei);
        if (!file.canRead() || !file.isFile()){
            System.exit(0);
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(datei));
            text = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {}
        } 
        aufgabeLoesen();
    }

    public void aufgabeLoesen(){
        System.out.println("Die Grundlage " + text);
    }
}