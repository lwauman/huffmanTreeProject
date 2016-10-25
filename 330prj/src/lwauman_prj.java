abstract class HuffTree implements Comparable<HuffTree>{
    private int frequency;
    
    public HuffTree(int freq){
        frequency = freq;
    }
    @Override
    public int compareTo(HuffTree tree){
        return frequency - tree.getFrequency();
    }
    public int getFrequency() {
        return frequency;
    }
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    } 
}


public class lwauman_prj{
    
    public class HuffLeaf extends HuffTree{
        private char value;

        public HuffLeaf(int freq, char val){
            super(freq);
            value = val;
        }
    }
    
}