package com.walmartlabs.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class WordTreeTest {

    @Test
    public void shouldInsertWord() {
        WordTree tree = new WordTree();
        CharacterNode lastCharacterNode = tree.insertWord("this");

        assertEquals("this", tree.getWord(lastCharacterNode));
    }

    @Test
    public void shouldStoreWordInLowerCase() {
        WordTree tree = new WordTree();
        CharacterNode lastCharacterNode = tree.insertWord("THIS");

        assertEquals("this", tree.getWord(lastCharacterNode));
    }

    @Test
    public void shouldShareCommonPrefixAmongWords() {
        WordTree tree = new WordTree();
        CharacterNode handleForThis = tree.insertWord("this");
        CharacterNode handleForThat = tree.insertWord("that");

        /* compare if 'h' is shared */
        assertSame(handleForThis.getParent().getParent(), handleForThat.getParent().getParent());

        /* compare if 'i' points to 'a' as sibling */
        assertSame(handleForThis.getParent().getNext(), handleForThat.getParent());
    }


}
