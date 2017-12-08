/**
 * Copyright 2014, Emory University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 *
 *
 * THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING

 A TUTOR OR CODE WRITTEN BY OTHER STUDENTS - Xinyi Jiang*/
package trie.autocomplete;

import trie.Trie;
import trie.TrieNode;

import java.util.*;

/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class JiangAutocomplete extends Trie<List<String>> implements IAutocomplete<List<String>>
{
    @Override
    public List<String> getCandidates(String prefix)
    {
        // TODO must be modified
        prefix = prefix.trim();
        List<String> list = new ArrayList<>();
        TrieNode noderoot = super.find(prefix);
        if(prefix.isEmpty()){
            noderoot = super.getRoot();
        }
        if(noderoot==null){return list;}
        if(noderoot.getValue()!=null) {
            list.addAll((ArrayList) noderoot.getValue());
        }
        if(list.size()>= 20){
            return list;
        }
        Queue<TrieNode> queuelist= new LinkedList<>();
        queuelist.add(noderoot);
        while(!queuelist.isEmpty()) {
            TrieNode p=queuelist.remove();

            if(p.isEndState()) {
                TrieNode b = p;
                String word = "";
                while (b != super.getRoot()) {
                    word = b.getKey() + word;
                    b = b.getParent();
                }
                if (!list.contains(word)) {
                    list.add(word);
                }
                if (list.size() >= 20) {
                    return list;
                }
            }

                List<Map.Entry<Character,TrieNode>> unsortedList = new ArrayList<Map.Entry<Character,TrieNode>>(p.getChildrenMap().entrySet());
                Collections.sort(unsortedList, new Comparator<Map.Entry<Character, TrieNode>>() {
                    @Override
                    public int compare(Map.Entry<Character, TrieNode> o1, Map.Entry<Character, TrieNode> o2) {
                        return o1.getKey().compareTo(o2.getKey());
                    }
                });
                for(Map.Entry<Character, TrieNode> entry: unsortedList){
                    queuelist.add(entry.getValue());
                }


        }
        
        return list;
    }

    @Override
    public void pickCandidate(String prefix, String candidate)
    {
        prefix=prefix.trim();
        candidate = candidate.trim();
        TrieNode node = super.find(prefix);
        if(node == null){
            super.put(prefix, new ArrayList<>());
            node = super.find(prefix);
            node.setEndState(false);
        }
        if(super.find(candidate)==null){
            super.put(candidate,null);
        }
        List<String> list =(List<String>) node.getValue();
        if(list == null){
            list = new ArrayList<>();
        }

        if(list.contains(candidate)) {
            list.remove(candidate);
        }
        if(list.size()==20){
            list.remove(19);
        }
        list.add(0,candidate);

        node.setValue(list);

        // TODO must be filled
    }
}
