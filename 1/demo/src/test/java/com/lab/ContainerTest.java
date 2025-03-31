package com.lab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ContainerTest {
    
    @Test
    public void shouldAddAndRetrieveElements() {
        Container c = new Container();
        c.add(10);
        c.add(20);
        c.add(30);

        assertEquals(10, c.get(0));
        assertEquals(20, c.get(1));
        assertEquals(30, c.get(2));
    }

    @Test
    public void shouldExpandWhenFull() {
        Container c = new Container();
        for(int i = 0; i < 15; i++) {
            c.add(i);
        }

        assertEquals(15, c.size());
    }

    @Test
    public void shouldTrackSize() {
        Container c = new Container();
        assertEquals(0, c.size());

        c.add(100);
        c.add(200);
        
        assertEquals(2, c.size());
    }

    @Test
    public void shouldRemoveElements() {
        Container c = new Container();
        c.add(5);
        c.add(10);
        c.add(15);

        assertEquals(5, c.remove(0));
        assertEquals(2, c.size());
    }

    @Test
    public void shouldClearAllElements() {
        Container c = new Container();
        c.add(1);
        c.add(2);
        assertEquals(2, c.size());

        c.clear();
        assertEquals(0, c.size());
    }

    @Test
    public void shouldCheckElementPresence() {
        Container c = new Container();
        c.add(7);
        c.add(8);

        assertTrue(c.contains(7));
        assertFalse(c.contains(9));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldFailWhenRemovingFromEmpty() {
        Container c = new Container();
        c.remove(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldFailWhenAccessingEmpty() {
        Container c = new Container();
        c.get(0);
    }
}