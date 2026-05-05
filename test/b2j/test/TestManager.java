package b2j.test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import b2j.wrappers.HundWrapper;
import b2j.wrappers.MainWrapper;
import b2j.wrappers.MenschWrapper;
import de.tum.cit.aet.levenshtein.LevenshteinTest;
import de.tum.cit.aet.levenshtein.StructuralLevenshtein;



@LevenshteinTest
public class TestManager {


    @BeforeAll
    static void beforeAll() {
        testCompilationAndSetup();
    }

    static void testCompilationAndSetup() {
        assertThat(Tests.main).isNotNull();
        assertThat(Tests.hund).isNotNull();
        assertThat(Tests.mensch).isNotNull();
        assertThat(Tests.main).isInstanceOf(MainWrapper.class);
        assertThat(Tests.hund).isInstanceOf(HundWrapper.class);
        assertThat(Tests.mensch).isInstanceOf(MenschWrapper.class);
    }
    
    @TestFactory
    List<DynamicTest> strukturTests() {
        return StructuralLevenshtein.structuralTestFactory(
            StructuralLevenshtein.DetailLevel.ONE_FOR_EVERYTHING,
            Tests.main, Tests.hund, Tests.mensch
        );
    }

    @Test
    void testMenschGetX() {
        try {
            Tests.testMenschGetX();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }



    @Test
    void testMenschGetY() {
        try {
            Tests.testMenschGetY();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }



    @Test
    void testGassiGehen() {
        try {
            //fail("Dieser Teil kann noch nicht automatisch überprüft werden.");
            Tests.testGassiGehen();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }



    @Test
    void testHundRufen() {
        try {
            //fail("Dieser Teil kann noch nicht automatisch überprüft werden.");
            Tests.testHundRufen();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }



    @Test
    void testBeiFuss() {
        try {
            //fail("Dieser Teil kann noch nicht automatisch überprüft werden.");
            Tests.testBeiFuss();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }



}    

