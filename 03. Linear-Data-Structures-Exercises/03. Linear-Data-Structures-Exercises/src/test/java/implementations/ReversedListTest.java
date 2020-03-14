package implementations;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReversedListTest {

    @Test
    public void add() {
        ReversedList<String> reversedList = new ReversedList<>();
        reversedList.add("0");
        reversedList.add("1");
        reversedList.add("2");
        reversedList.add("3");

        assertEquals( 4, reversedList.size());
    }

    @Test
    public void size() {
    }

    @Test
    public void capacity() {
    }

    @Test
    public void get() {
        ReversedList<String> reversedList = new ReversedList<>();
        reversedList.add("0");
        reversedList.add("1");
        reversedList.add("2");
        reversedList.add("3");

        String actual = reversedList.get(0);

        assertEquals( "3", actual);
    }

    @Test
    public void removeAt() {
        ReversedList<String> reversedList = new ReversedList<>();
        reversedList.add("0");
        reversedList.add("1");
        reversedList.add("2");
        reversedList.add("3");

         reversedList.removeAt(0);

        assertEquals( 3, reversedList.size());
    }

    @Test
    public void iterator() {
    }
}