void copyPaste(Queue src, int index){
        
        // which copies all the elements of the src queue and inserts
        // to  queue at position index
        // you are not allowed to use enqueue dequeue and is empty functions
        //you can assume the destination queue has enough space for insertion
        // should run in O(n) time
        // hint: start by counting the number  of positions to shift for openeing for the elements of src




   //########################jawba 
    void copyPaste(Queue src, int index) {
      int shift = index ;
      int remaining = this.size - shift ;
      int size = src.size;

      //to shift 
      for (int i =0 ; i< shift ; i++){
          this.add(this.remove());
      }

      //to copy at a specific  index
      for (int i =0 ; i< size ; i++){
          this.add(src.remove());
      }
    
      //shift the rest of the elements of the destination
      for (int i =0 ; i< remaining ; i++){
          this.add(this.remove());
      }


    }


package Tree;

public class TreeNode {

    protected TreeNode left;
    protected TreeNode right;
    protected int data;

    public TreeNode(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public TreeNode getLeft(){
        return left;
    }

    public TreeNode getRight(){
        return right;
    }

    public int getData(){
        return data;
    }

    public void setLeft(TreeNode left){
        this.left = left;
    }

    public void setRight(TreeNode right){
        this.right = right;
    }

    public TreeNode recursiveSearch(int value){
        if (data == value){
            return this;
        }
        if (value < data){
            if (left != null){
                return left.recursiveSearch(value);
            } else {
                return null;
            }
        } else {
            if (right != null){
                return right.recursiveSearch(value);
            } else {
                return null;
            }
        }
    }

    public TreeNode recursiveMinSearch(){
        if (left == null){
            return this;
        }
        return left.recursiveMinSearch();
    }

    public TreeNode recursiveMaxSearch(){
        if (right == null){
            return this;
        }
        return right.recursiveMaxSearch();
    }

    public void preorder(){
        System.out.println(data);
        if (left != null){
            left.preorder();
        }
        if (right != null){
            right.preorder();
        }
    }

    public void inorder(){
        if (left != null){
            left.inorder();
        }
        System.out.println(data);
        if (right != null){
            right.inorder();
        }
    }

    public void postorder(){
        if (left != null){
            left.postorder();
        }
        if (right != null){
            right.postorder();
        }
        System.out.println(data);
    }

    public void prettyPrint(int level){
        for (int i = 0; i < level; i++){
            System.out.print("\t");
        }
        System.out.println(data);
        if (left != null){
            left.prettyPrint(level + 1);
        }
        if (right != null){
            right.prettyPrint(level + 1);
        }
    }

    public void recursiveInsert(TreeNode node){
        if (node.getData() < data){
            if (left != null){
                left.recursiveInsert(node);
            } else {
                left = node;
            }
        } else {
            if (right != null){
                right.recursiveInsert(node);
            } else {
                right = node;
            }
        }
    }
    //recursive
    //Question 3

    void accumulateLeafNodes(Queue queue){

        /*

write a recursive method  in treenode class which accumulates the contents
(integer as data field ) of all leaf nodes in queue. for queue, you
are only allowed to use enqueue function. you should use array implementation for
this queue in this question
         */


    }

}


//########################jawba 
void accumulateLeafNodes(Queue queue) {
    if (left == null && right == null) { // leaf node
        queue.enqueue(data);
    } else {
        if (left!= null) {
            left.accumulateLeafNodes(queue);
        }
        if (right!= null) {
            right.accumulateLeafNodes(queue);
        }
    }
}




/// question 4
// non recursive method

/* in the tree class which first finds the minimum (A) and max (B) elemet in the tree the method
 will then randomly search a num between {A,B} N times and returns the average num of nodes visted in this  search you are not allowed to use any tree methods
 */





package Tree;

public class Tree {

    protected TreeNode root;

    public Tree(){
        root = null;
    }

    public TreeNode getRoot(){
        return root;
    }

    public void setRoot(TreeNode root){
        this.root = root;
    }

    protected void insertChild(TreeNode parent, TreeNode child){
        if (parent == null) {
            root = child;
        } else {
            if (child.data < parent.data) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
    }

    public void iterativeInsert(TreeNode node){
        TreeNode parent = null;
        TreeNode tmp = root;
        while (tmp != null) {
            parent = tmp;
            if (node.getData() < tmp.getData()){
                tmp = tmp.getLeft();
            } else {
                tmp = tmp.getRight();
            }
        }
        insertChild(parent, node);
    }

    public void prettyPrint(){
        if (root != null){
            root.prettyPrint(0);
        }
    }

    double simulateSearch(int N){


        double simulateSearch;
    }

}
// non recursive method

/* in the tree class which first finds the minimum (A) and max (B) elemet in the tree the method
 wi;; then randomly search a num between {A,B} N times and returns the average num of nodes visted in this  search you are not allowed to use any tree methods
 */


//#########################################jawab 
double simulateSearch(int N) {
    if (root == null) {
       return null 
    }

    int min = root.data;
    int max = root.data;
    TreeNode current = root;

    // Find minimum and maximum 
    while (current != null) {
        min = Math.min(min, current.data);
        max = Math.max(max, current.data);
        current = current.left != null ? current.left : current.right;
    }

    double simulateSearch = 0;
    int visitedNodes = 0;

    // Randomly search for numbers between the minimum and maximum values N times
    for (int i = 0; i < N; i++) {
        int target = min + (int) (Math.random() * ((max - min) + 1));
        current = root;

        while (current != null) {
            visitedNodes++;
            if (target < current.data) {
                current = current.left;
            } else if (target > current.data) {
                current = current.right;
            } else {
                break;
            }
        }
    }

    // Calculate the average number of nodes visited
    simulateSearch = (double) visitedNodes / N;
    return simulateSearch;
}


package List;

public class Hash {

    private LinkedList[] table;

    private int N;

    public Hash(int N){
        table = new LinkedList[N];
        for (int i = 0; i < N; i++){
            table[i] = new LinkedList();
        }
        this.N = N;
    }

    public Node search(int value){
        int address;
        address = hashFunction(value);
        return table[address].search(value);
    }

    public void insert(int value){
        int address;
        address = hashFunction(value);
        table[address].insertFirst(new Node(value));
    }

    private int hashFunction(int value){
        return value % N;
    }

    // that takes an arrray of integers as parameters which contain distints num less than 2N where N is the num of elements inn the array and return the sorted version of that array your metod
    // should run on O(N) time Dont use any external data strucutres or arrays except the resultying array and hash table hint find the max num in the array the sorted array should contain only num less that that
    int[] intersection(int[] list1, int[] list2){

//###################################jawab 
 int[] intersection(int[] list1, int[] list2){
        // Find the maximum number in both arrays
        int maxNum = Integer.MIN_VALUE;
        for (int num : list1) {
            maxNum = Math.max(maxNum, num);
        }
        for (int num : list2) {
            maxNum = Math.max(maxNum, num);
        }

        // Create a hash table with size 2N
        Hash hashTable = new Hash(2 * maxNum);

        // Iterate through the first list and insert elements into the hash table
        for (int num : list1) {
            hashTable.insert(num);
        }

        // Initialize a result array to store the intersection
        int[] result = new int[Math.min(list1.length, list2.length)];
        int resultIndex = 0;

        // Iterate through the second list and check for intersection
        for (int num : list2) {
            if (hashTable.search(num) != null) {
                result[resultIndex++] = num;
            }
        }

        // Sort the result array
        Arrays.sort(result);

        // Trim the result array to the actual size
        return Arrays.copyOf(result, resultIndex);
    }


    }
}