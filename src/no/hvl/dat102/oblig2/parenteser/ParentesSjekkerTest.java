package no.hvl.dat102.oblig2.parenteser;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ParentesSjekkerTest {

    @Test
    public void testSjekkParenteser() {
        assertTrue(ParentesSjekker.sjekkParenteser("{ [ ( ) ] }"));
        assertFalse(ParentesSjekker.sjekkParenteser("{ [ ( ) }"));
        assertFalse(ParentesSjekker.sjekkParenteser("[ ( ) ] }"));
        assertFalse(ParentesSjekker.sjekkParenteser("{ [ ( ] ) }"));
        assertTrue(ParentesSjekker.sjekkParenteser("""
            class HelloWorld {
            public static void main(String[] args) {
            System.out.println("Hello World!");
            }
            }
            """));
    }
}
