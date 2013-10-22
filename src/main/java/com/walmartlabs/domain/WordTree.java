package com.walmartlabs.domain;

import org.slf4j.Logger;

import static org.apache.commons.lang3.StringUtils.reverse;

public class WordTree {

    /* Head node with some proxy character so as to avoid un-necessary null checks in the main logic */
    private CharacterNode HEAD = new CharacterNode('~');
    private static Logger log = org.slf4j.LoggerFactory.getLogger(WordTree.class);

    public String getWord(CharacterNode lastCharacterNodeOfWord) {
        CharacterNode currentNode = lastCharacterNodeOfWord;
        StringBuilder builder = new StringBuilder();
        while (currentNode != HEAD) {
            builder.append(currentNode.getCharacter());
            currentNode = currentNode.getParent();
        }

        return reverse(builder.toString());
    }

    public CharacterNode insertWord(String word) {
        log.info("Word to be inserted =" + word);
        CharacterNode parentNode = HEAD;
        String wordInLowerCase = word.toLowerCase();
        CharacterNode characterNode = null;
        for (int i = 0; i < wordInLowerCase.length(); i++) {
            char character = wordInLowerCase.charAt(i);

            characterNode = parentNode.findOrAppendInChildList(character);
            parentNode = characterNode;
        }

        characterNode.setIsWord(true);
        return characterNode;
    }

}
