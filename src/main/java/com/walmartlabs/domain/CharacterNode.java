package com.walmartlabs.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharacterNode {
    private char character;
    private CharacterNode next;
    private CharacterNode firstChild;
    private CharacterNode parent;
    private boolean isWord;
    private static Logger log = LoggerFactory.getLogger(CharacterNode.class);

    CharacterNode(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

    public CharacterNode getParent() {
        return parent;
    }

    public CharacterNode getFirstChild() {
        return firstChild;
    }

    public void setParent(CharacterNode parent) {
        this.parent = parent;
    }

    private void setFirstChild(CharacterNode firstChild) {
        this.firstChild = firstChild;
        this.firstChild.setParent(this);
    }

    public CharacterNode getNext() {
        return next;
    }

    public void setNext(CharacterNode next) {
        this.next = next;
    }

    public void setIsWord(boolean word) {
        isWord = word;
    }

    CharacterNode findOrAppendInChildList(char character) {
        CharacterNode characterNode;
        characterNode = new CharacterNode(character);
        characterNode.setParent(this);
        if (this.getFirstChild() == null) {
            log.info(this.character + " >> " + character);
            setFirstChild(characterNode);
        } else {
            CharacterNode node = getFirstChild();
            while (node.getNext() != null && node.getCharacter() != character) {
                node = node.getNext();
            }

            if (node.getCharacter() == character) {
                /*No need to insertWord as the character is already present in children list*/
                log.info("<" + node.getCharacter() + ">");
                return node;
            } else {
                /*Insert at character at the end in child list*/
                node.setNext(characterNode);
                log.info(node.getCharacter() + " -- " + character);
            }
        }
        return characterNode;
    }
}
