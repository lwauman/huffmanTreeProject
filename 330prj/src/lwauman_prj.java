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
    private class HuffTree{
        HuffNode root;
        
        private HuffTree()
    }
}
