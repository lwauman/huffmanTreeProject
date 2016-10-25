
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class lwauman_prj {
    public static void main(String[] args) {
        
    }
    private class HuffNode{
        int weight;
        String value;
        HuffNode parentNode;
        HuffNode leftNode;
        HuffNode rightNode;
        
        private HuffNode(int w, String v, HuffNode p,HuffNode l, HuffNode r){
            weight = w;
            value = v;
            parentNode = p;
            leftNode = l;
            rightNode = r;
        }
    }
    private static class HuffTree{
        private HuffNode root;
        private char[] characters;
        private HashMap<Character,mapObj> frequency;
        
        private HuffTree(String toCompress){
            characters = new char[toCompress.length()];
            characters = convertToCharArray(toCompress);
            findAndSetFreqOfCharacters();
        }
        
        private char[] convertToCharArray(String toConvert){
            char[] temp = new char[toConvert.length()];
            for(int i=0; i<toConvert.length(); i++)
                temp[i] = toConvert.charAt(i);
            return temp;
        }
        
        private void findAndSetFreqOfCharacters(){
            frequency = new HashMap<>();
            for(int i = 0; i<characters.length; i++){
                if(frequency.containsKey(characters[i]))
                    frequency.get(characters[i]).incFreq();
                else
                    frequency.put(characters[i], new mapObj(characters[i]));
            }     
        }
 
        private class mapObj{
            char value;
            int freq;
            
            private mapObj(char val){
                value = val;
                freq = 1;
            }

            private char getValue() {
                return value;
            }

            private int getFreq() {
                return freq;
            }
            
            private void incFreq(){
                freq++;
            }
        }
    }
}
