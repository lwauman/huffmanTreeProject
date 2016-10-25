
import java.util.PriorityQueue;

public class lwauman_prj{
    private static final int NUM_ASCII_CHARS = 256;
    
    public static void main(String[] args) {
   
    }
    private static class HuffNode implements Comparable<HuffNode>{
        private final char value;
        private final int frequency;
        private final HuffNode left, right;
        
        
        HuffNode(char v, int f, HuffNode l, HuffNode r){
            value = v;
            frequency = f;
            left = l;
            right = r;
        }
        
        @Override
        public int compareTo(HuffNode node) {
            return frequency - node.frequency;
        }
    }
    public static void compress(String toCompress){
        HuffNode root;
        char[] characters = toCompress.toCharArray();
        int[] freqOfChars = new int[NUM_ASCII_CHARS];
        
        //get frequency of each character
        for(int i = 0; i <characters.length; i++)
            freqOfChars[characters[i]]++;
        
        //construct the Huffman Tree using frequencies
        root = constructTree(freqOfChars);
    }
    
    private static HuffNode constructTree(int[] freq){
        PriorityQueue<HuffNode> minHeap = new PriorityQueue<>();
        
        //add leafs to minHeap
        for(int i = 0; i < freq.length; i++)
            if(freq[i] > 0)
                minHeap.offer(new HuffNode((char)i, freq[i], null, null));
        
        //merge smallest into a node with two children
        while(minHeap.size()>1){
            HuffNode left = minHeap.poll();
            HuffNode right = minHeap.poll();
            //only leafs have char value
            HuffNode parent = new HuffNode('\0', left.frequency + right.frequency, left, right);
            minHeap.offer(parent);
        }
        return minHeap.poll();
    }
}