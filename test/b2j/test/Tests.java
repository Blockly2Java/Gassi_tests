package b2j.test;
import static org.assertj.core.api.Assertions.assertThat;

import b2j.wrappers.HundWrapper;
import b2j.wrappers.MainWrapper;
import b2j.wrappers.MenschWrapper;
public class Tests {
    static MainWrapper<?> main = new MainWrapper<>(); 
    static HundWrapper<?> hund = new HundWrapper<>();
    static MenschWrapper<?> mensch = new MenschWrapper<>();

    public static void testMenschGetX() {
        double x = (double) Tests.mensch.getX().invoke();
        assertThat(x).isNotNaN();
    }

    public static void testMenschGetY() {
        double y = (double) Tests.mensch.getY().invoke();
        assertThat(y).isNotNaN();
    }

    public static void testGassiGehen() {
        Tests.mensch.getObj(true, false, Tests.mensch.constructor(), Tests.hund.getObj());
        double initialMenschX = (double) Tests.mensch.getX().invoke();
        double initialMenschY = (double) Tests.mensch.getY().invoke();
        Tests.mensch.gassiGehen().invoke();
        double newMenschX = (double) Tests.mensch.getX().invoke();
        double newMenschY = (double) Tests.mensch.getY().invoke();
        double newHundX = (double) Tests.hund.getX().invoke();
        double newHundY = (double) Tests.hund.getY().invoke();
        // Check Mensch moved
        assertThat(Math.abs(newMenschX - initialMenschX) > 0 || Math.abs(newMenschY - initialMenschY) > 0)
            .withFailMessage("Der Mensch hat sich beim Gassigehen nicht zufällig irgendwohin bewegt. \n(Diese Überprüfung funktioniert nur, wenn die Getter korrekt programmiert sind!)")
            .isTrue();
        // Check Hund moved to Mensch
        double dist = Math.sqrt(Math.pow(newMenschX - newHundX, 2) + Math.pow(newMenschY - newHundY, 2));
        assertThat(dist)
        .withFailMessage(String.format("""
            Der Hund ist nicht zum Menschen gelaufen.
            Er muss am Ende des Gassigehens mit den Methoden, die du schon programmiert hast, 
            gerufen werden und darf dann nicht mehr als 10 Längeneinheiten vom Mensch entfernt sein.
            Aktuell ist er aber %.1f Einheiten entfernt. Nutze die Methoden, die du bereits
            (Diese Überprüfung funktioniert nur, wenn die Getter korrekt programmiert sind!)
        """, dist))
        .isLessThan(10);
            
    }

    public static void testHundRufen() {
        Tests.mensch.getObj(true, false, Tests.mensch.constructor(), Tests.hund.getObj());
        Tests.mensch.gassiGehen().invoke();
        double newMenschX = (double) Tests.mensch.getX().invoke();
        double newMenschY = (double) Tests.mensch.getY().invoke();
        double newHundX = (double) Tests.hund.getX().invoke();
        double newHundY = (double) Tests.hund.getY().invoke();
        // Check Hund moved to Mensch
        double dist = Math.sqrt(Math.pow(newMenschX - newHundX, 2) + Math.pow(newMenschY - newHundY, 2));
        assertThat(dist)
        .withFailMessage(String.format("""
            Der Hund läuft nicht zum Menschen, wenn die Methode hundRufen() aufgerufen wird.
            Er darf dann nicht mehr als 10 Längeneinheiten vom Menschen entfernt sein.
            Aktuell ist er aber %.1f Einheiten entfernt.
            Tipp: Der Methode beiFuss() muss man ein Mensch-Objekt übergeben, damit der Hund weiß, zu wem er laufen soll. Wir kennen dafür ein besonderes Referenzattribut.
            (Diese Überprüfung funktioniert nur, wenn die Getter korrekt programmiert sind!)
        """, dist))
        .isLessThan(10);
    }

    public static void testBeiFuss() {
        Object menschObj = Tests.mensch.getObj();
        double menschX = (double) Tests.mensch.getX().invoke();
        double menschY = (double) Tests.mensch.getY().invoke();
        Tests.hund.beiFuss().invoke(menschObj);
        double newHundX = (double) Tests.hund.getX().invoke();
        double newHundY = (double) Tests.hund.getY().invoke();
        double dist = Math.sqrt(Math.pow(menschX - newHundX, 2) + Math.pow(menschY - newHundY, 2));
        assertThat(dist)
        .withFailMessage(String.format("""
            Der Hund läuft nicht zum Mensch, wenn er mit der Methode beiFuss(Mensch) gerufen wird.
            Er darf dann nicht mehr als 10 Längeneinheiten vom Mensch entfernt sein.
            Aktuell ist er aber %.1f Einheiten entfernt.
            Tipp: 
            (Diese Überprüfung funktioniert nur, wenn die Getter korrekt programmiert sind!)
        """, dist))
        .isLessThan(10);
    }
}
