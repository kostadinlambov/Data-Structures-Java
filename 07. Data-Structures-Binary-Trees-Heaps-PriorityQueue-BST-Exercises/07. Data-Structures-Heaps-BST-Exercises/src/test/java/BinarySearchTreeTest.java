import groovy.util.Eval;
import org.apache.commons.collections.ArrayStack;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> bst;

    @Before
    public void beforeEach() {
        bst = new BinarySearchTree<>(5);

        bst.insert(3);
        bst.insert(7);
        bst.insert(6);
        bst.insert(1);
        bst.insert(17);
    }

    @Test
    public void createTree() {
        assertEquals(Integer.valueOf(5), bst.getRoot().getValue());
    }

    @Test
    public void eachInOrder() {
        List<Integer> elements = new ArrayList<>();
        bst.eachInOrder(elements::add);

        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 7, 17));

        assertEquals(expected.size(), elements.size());

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), elements.get(i));
        }
    }

    @Test
    public void getRoot() {
    }

    @Test
    public void insert() {
        assertEquals(Integer.valueOf(3), bst.getRoot().getLeft().getValue());
        assertEquals(Integer.valueOf(7), bst.getRoot().getRight().getValue());
        assertEquals(Integer.valueOf(6), bst.getRoot().getRight().getLeft().getValue());
    }

    @Test
    public void containsTrue() {
        assertTrue(bst.contains(6));
    }

    @Test
    public void containsFalse() {
        assertFalse(bst.contains(8));
    }

    @Test
    public void searchTrue() {
        BinarySearchTree<Integer> search = bst.search(7);

        bst.insert(8);

        assertEquals(Integer.valueOf(7), search.getRoot().getValue());
        assertEquals(Integer.valueOf(6), search.getRoot().getLeft().getValue());
        assertEquals(Integer.valueOf(17), search.getRoot().getRight().getValue());

        assertTrue(bst.contains(8));
        assertFalse(search.contains(8));
    }

    @Test
    public void searchFalse() {
        assertFalse(bst.contains(8));
    }

    @Test
    public void range() {
        List<Integer> range = bst.range(3, 7);

        // 3, 5, 6, 7
        List<Integer> expected = Arrays.asList(3, 5, 6, 7);

        assertEquals(4, range.size());

        for (Integer value : range) {
            assertTrue(expected.contains(value));
        }
    }

    @Test
    public void deleteMin() {
        assertTrue(bst.contains(1));
        bst.deleteMin();
        assertFalse(bst.contains(1));
    }

    @Test
    public void deleteMax() {
        assertTrue(bst.contains(17));
        bst.deleteMax();
        assertFalse(bst.contains(17));
    }

    @Test
    public void count() {
        assertEquals(6, bst.count());
    }

    @Test
    public void rank() {
        assertEquals(4, bst.rank(7));
    }

    @Test
    public void ceil() {
        assertEquals(Integer.valueOf(7), bst.ceil(6));
    }

    @Test
    public void floor() {
        assertEquals(Integer.valueOf(6), bst.floor(7));

    }
}