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
        double initialMenschX = (double) Tests.mensch.getX().invoke();
        double initialMenschY = (double) Tests.mensch.getY().invoke();
        Tests.mensch.gassiGehen().invoke();
        double newMenschX = (double) Tests.mensch.getX().invoke();
        double newMenschY = (double) Tests.mensch.getY().invoke();
        double newHundX = (double) Tests.hund.getX().invoke();
        double newHundY = (double) Tests.hund.getY().invoke();
        // Check Mensch moved
        assertThat(Math.abs(newMenschX - initialMenschX) > 0 || Math.abs(newMenschY - initialMenschY) > 0).isTrue();
        // Check Hund moved to Mensch
        double dist = Math.sqrt(Math.pow(newMenschX - newHundX, 2) + Math.pow(newMenschY - newHundY, 2));
        assertThat(dist).isLessThan(5);
    }

    public static void testHundRufen() {
        double menschX = (double) Tests.mensch.getX().invoke();
        double menschY = (double) Tests.mensch.getY().invoke();
        Tests.mensch.hundRufen().invoke();
        double newHundX = (double) Tests.hund.getX().invoke();
        double newHundY = (double) Tests.hund.getY().invoke();
        double dist = Math.sqrt(Math.pow(menschX - newHundX, 2) + Math.pow(menschY - newHundY, 2));
        assertThat(dist).isLessThan(5);
    }

    public static void testBeiFuss() {
        Object menschObj = Tests.mensch.getObj();
        double menschX = (double) Tests.mensch.getX().invoke();
        double menschY = (double) Tests.mensch.getY().invoke();
        Tests.hund.beiFuss().invoke(menschObj);
        double newHundX = (double) Tests.hund.getX().invoke();
        double newHundY = (double) Tests.hund.getY().invoke();
        double dist = Math.sqrt(Math.pow(menschX - newHundX, 2) + Math.pow(menschY - newHundY, 2));
        assertThat(dist).isLessThan(5);
    }
}
