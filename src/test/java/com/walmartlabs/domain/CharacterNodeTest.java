package com.walmartlabs.domain;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CharacterNodeTest {

    @Test
    public void shouldAddAsFirstChild() {
        CharacterNode parent = new CharacterNode('p');
        parent.findOrAppendInChildList('c');
        assertEquals('c', parent.getFirstChild().getCharacter());
    }

    @Test
    public void shouldAddAsSiblingToFirstChild() {
        CharacterNode parent = new CharacterNode('p');
        CharacterNode firstChild = parent.findOrAppendInChildList('1');

        CharacterNode secondChild = parent.findOrAppendInChildList('2');
        assertEquals(secondChild, firstChild.getNext());
    }


    @Test
    public void shouldAddAsSiblingToSecondChild() {
        CharacterNode parent = new CharacterNode('p');
        CharacterNode firstChild = parent.findOrAppendInChildList('1');

        CharacterNode secondChild = parent.findOrAppendInChildList('2');
        CharacterNode thirdChild = parent.findOrAppendInChildList('3');
        assertEquals(thirdChild, firstChild.getNext().getNext());
    }

    @Test
    public void shouldFindAnExistingChild() {
        CharacterNode parent = new CharacterNode('p');
        CharacterNode firstChild = parent.findOrAppendInChildList('1');
        CharacterNode secondChild = parent.findOrAppendInChildList('2');

        assertSame(firstChild, parent.findOrAppendInChildList('1'));
        assertSame(secondChild, parent.findOrAppendInChildList('2'));
    }
}
