package test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import levenshtein.LevenshteinTest;
import static levenshtein.StructuralLevenshtein.DetailLevel.ONE_PER_MEMBER_CATEGORY;
import static levenshtein.StructuralLevenshtein.structuralTestFactory;
import wrappers.HundWrapper;
import wrappers.MainWrapper;
import wrappers.MenschWrapper;



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
        return structuralTestFactory(
            ONE_PER_MEMBER_CATEGORY,
            Tests.main, Tests.hund, Tests.mensch
        );
    }

    @Test
    void testMain() {
        try {
            Tests.testMain();
        }
        catch (AssertionError e) {
            fail(e.getMessage());
        }
    }



}    

