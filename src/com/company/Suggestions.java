package com.company;

import java.util.ArrayList;
import java.util.List;

class Suggestions {
    public List<List<String>> searchSuggestions(List<String> reviewsRepository, String searchWord) {
        TrieNode root = buildTrie(reviewsRepository);
        List<List<String>> result = new ArrayList();
        for (int i = 1; i <= searchWord.length(); i++){
            result.add(findTopThree(root, searchWord.substring(0, i)));
        }
        return result;
    }

    private static List<String> findTopThree(TrieNode root, String searchTerm){
        List<String> result = new ArrayList();
        TrieNode node = root;

        //Find where search word ends
        for (char c : searchTerm.toCharArray()){
            if(node.children[c -'a'] == null){
                //Returns empty array if less than 2 characters
                return result;
            } else {
                node = node.children[c - 'a'];
            }
        }
        if(node.isWord){
            result.add(searchTerm);
        }
        for (TrieNode child : node.children){
            if(child != null){
                //Depth First Search
                List<String> thisResult = dfs(child, searchTerm, new ArrayList());
                result.addAll(thisResult);
                if(result.size() >= 3){
                    return result.subList(0,3);
                }
            }
        }
        return result;
    }

    private List<String> dfs(TrieNode root, String word, List<String> result){
        if(root.isWord){
            result.add(word + root.c);
            if(result.size() >= 3){
                return result;
            }
        }
        for (TrieNode child : root.children){
            if(child != null){
                dfs(child, word + root.c, result);
            }
        }
        return result;
    }

    private TrieNode buildTrie(List<String> products){
        TrieNode root = new TrieNode(' ');
        for (String product : products){
            insert(product, root);
        }
        return root;
    }

    private void insert(String product, TrieNode root){
        TrieNode node = root;
        for (int i=0; i<product.length(); i++){
            char c = product.charAt(i);
            if(node.children[c - 'a'] == null){
                node.children[c - 'a'] = new TrieNode(c);
            }
            node = node.children[c - 'a'];
        }
        node.isWord = true;
    }

    class TrieNode {
        char c;
        TrieNode[] children;
        boolean isWord;

        public TrieNode(char c){
            this.c = c;
            this.children = new TrieNode[26];
        }
    }
}
