package test.wci.frontend;

import org.junit.Before;
import org.junit.Test;
import wci.frontend.Source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.assertTrue;

public class SourceTest {
    private Source source;

    @Before
    public void setUp() {
    }

    @Test
    public void ABCDETest() {
        try {
            source = new Source(new BufferedReader(new StringReader("ABCDE")));
            assertTrue(source.currentChar() == 'A');
            assertTrue(source.nextChar() == 'B');
            assertTrue(source.nextChar() == 'C');
            assertTrue(source.nextChar() == 'D');
            assertTrue(source.currentChar() == 'D');
            assertTrue(source.currentChar() == 'D');
            assertTrue(source.nextChar() == 'E');
            assertTrue(source.nextChar() == Source.EOL);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
