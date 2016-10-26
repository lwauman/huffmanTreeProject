
import java.util.Arrays;
import java.util.PriorityQueue;

public class HuffTree{
    private HuffNode root;
    private String[] charCodes;
    private String orginalString;
    
    public HuffTree(){
        root = null;
        charCodes = null;
    }
    
    public void compress(String toCompress){
        orginalString = toCompress;
        char[] characters = toCompress.toCharArray();
        int[] freqOfChars = new int[256];

        //get frequency of each character
        for(int i = 0; i <characters.length; i++)
            freqOfChars[characters[i]]++;

        //construct and return the Huffman Tree using frequencies
        root = constructTree(freqOfChars);
        
        charCodes = new String[256];
        populateCharCodes(root, "");
    }

    private HuffNode constructTree(int[] freq){
        PriorityQueue<HuffNode> minHeap = new PriorityQueue<>();

        //add leafs to minHeap
        for(int i = 0; i < freq.length; i++)
            if(freq[i] > 0)
                minHeap.offer(new HuffNode((char)i, freq[i], null, null));

        //merge smallest into a node with two children
        while(minHeap.size()>1){
            HuffNode left = minHeap.poll();
            HuffNode right = minHeap.poll();
            //only leafs have char value. '\0' is null
            HuffNode parent = new HuffNode('\0', left.frequency + right.frequency, left, right);
            minHeap.offer(parent);
        }
        return minHeap.poll();
    }
    
    private void populateCharCodes(HuffNode node, String code){
        if(!node.isLeaf()){
            populateCharCodes(node.left, code + '0');
            populateCharCodes(node.right, code + '1');
        }
        else
            charCodes[node.value] = code;
    }
    
    public void printTreePreOrder(HuffNode node, int depth){
        if(node == null)
            return;
        
        //appends - according to level
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < depth; i++)
                sb.append('-');
        
        //if not a leaf just append frequency 
        if(!node.isLeaf())
            sb.append(node.frequency);
        
        //else (is a leaf) append frequency and value
        else
            sb.append(" ").append(node.frequency).append(" : ").append(node.value);
        
        //print string and recall method with appropiate node
        System.out.println(sb.toString());
        printTreePreOrder(node.left, depth+1);
        printTreePreOrder(node.right, depth+1);
        
    }
    
    public String getCode(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < orginalString.length(); i++)
            sb.append(charCodes[orginalString.charAt(i)]);
        return sb.toString();
    }
    
    public String decode(String code){
        HuffNode currentNode = root;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < code.length(); i++){
            if(code.charAt(i)=='0')
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
            if(currentNode.isLeaf()){
                sb.append(currentNode.value);
                currentNode = root;
            }
        }
        return sb.toString();
    }
    
    public static class HuffNode implements Comparable<HuffNode>{
        private final char value;
        private final int frequency;
        private final HuffNode left, right;

        public HuffNode(char v, int f, HuffNode l, HuffNode r){
            value = v;
            frequency = f;
            left = l;
            right = r;
        }
        
        public boolean isLeaf(){
            return left == null && right == null;
        }
        
        @Override
        public int compareTo(HuffNode node) {
            return frequency - node.frequency;
        }
    }
    
    public static void main(String[] args) {
        HuffTree tree = new HuffTree();
        tree.compress("teeeeeeeea");
        tree.printTreePreOrder(tree.root,0);
        System.out.println(tree.getCode());
        System.out.println(tree.decode(tree.getCode()));
        
    }
}
    
