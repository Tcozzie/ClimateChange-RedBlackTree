/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climate.change;

/**
 *
 * @author tiegancozzie
 * // Most methods came from the book "Algorithms Fourth Edition" by Robert Sedgewick 
 */
public class RedBlackTree<Key extends Comparable<Key>,Value> {
    
    private static final boolean RED=true; // makes red equal to true
    private static final boolean BLACK=false; // makes black equal to false
    private inInfo root; // creates the root for the tree
    
    private class inInfo{ // node class with all the information for each Key Value
        Key key;
        Value value;
        inInfo left,right; // the nodes left and right leafs
        int n;
        boolean color;
        
        inInfo(Key key, Value val, int n, boolean color){
            this.key=key;
            this.value=val;
            this.n=n; // how many leafs the node has
            this.color=color; // the nodes color
        }
    }
    /**
     * Checks to see if the node you are looking at is red or not
     * returns a boolean, red=true
     * 
     * @param x
     * @return Boolean
     * @throws N/A
     */
    private boolean isRed(inInfo x){ 
        if(x==null) return false; // checks to see if there is a node int the RBT
        return x.color==RED; // if the color equals red, return true
    }
    
    /**
     * returns the color of a specific node
     * 
     * @param x
     * @return String
     * @throws N/A
     */
    private String Color(inInfo x){
        if(x==null) return null;
        if(x.color==RED) return "Red";
        else return "Black";
    }
    
    /**
     * rotates 3 nodes to the left
     * 
     * @param h
     * @return inInfo
     */
    inInfo RotateLeft(inInfo h){
        inInfo x=h.right; // assigns right leaf node to a temp
        h.right=x.left; // makes the right to input node temps left node
        x.left=h; // makes the left leaf of temp input node
        x.color=h.color; // temp node equals input nodes color
        h.color=RED; // input nodes color gets changed to red
        x.n=h.n; // temp node gets input nodes height
        h.n=1+size(h.left)+size(h.right); // assignes a new height to input node
        return x; // returns the temp node (new root)
    }
    
    /**
     * rotates 3 nodes to the right
     * 
     * @param h
     * @return inInfo
     * @throws N/A
     */
    inInfo RotateRight(inInfo h){
        inInfo x=h.left; // assigns left leaf node to temp
        h.left=x.right; // makes the left to input node temps left node
        x.right=h; // makes the right leaf of temp input node
        x.color=h.color; // temp node equals input nodes color
        h.color=RED; // input nodes color gets changed to red
        x.n=h.n; // temp node gets input nodes height
        h.n=1+size(h.left)+size(h.right); // assigns new height to input node
        return x; // returns temp node (new root)
    }
    
    /**
     * flips the colors of root node and its leafs
     * 
     * @param h 
     * @return N/A
     * @throws N/A
     */
    void flipColors(inInfo h){
        h.color=RED; // changes color of root node to red
        h.left.color=BLACK; // changes left leaf node to black
        h.right.color=BLACK; // changes right leaf node to black
    }
    
    /**
     * calls a recursive method to get the value with root
     * 
     * @param key
     * @return Value
     * @throws N/A
     */
    public Value get(Key key){
        return get(root,key); // calls recursive method
    }
    
    /**
     * returns the value of node inputted
     * 
     * @param x
     * @param key
     * @return Value
     * @throws N/A
     */
    private Value get(inInfo x,Key key){
        if (x==null) return null; // if there isnt any node within the tree, return null
        int cmp=key.compareTo(x.key); // compares inputted key to the root key
        if (cmp<0) return get(x.left,key); // if inputted key is less then root key, recall get method with the root.left key as new root
        else if (cmp>0) return get(x.right,key); // if inputted key is greater then root key, recall get method with the root.right key as new root
        else return x.value; // if the key equals the root key, return the value of the key
    }
    
    /**
     * returns the height
     * 
     * @param N/A
     * @return int
     * @throws N/A
     */
    public int size(){
        return size(root); // calls method with the root
    }
    
    /**
     * returns the height at which the root is at
     * 
     * @param x
     * @return int
     * @throws N/A
     */
    private int size(inInfo x){
        if(x==null) return 0; // if there is no root return 0
        else return x.n; // return the height of the root
    }
    
    /**
     * puts key and value into the RBT
     * 
     * @param key
     * @param value
     * @return N/A
     * @throws N/A
     */
    public void put(Key key,Value value){
        root=put(root,key,value); // calls a recursive method to find where to put the new Key,Value
        root.color=BLACK; // makes the color of the new input black
    }
    
    /**
     * recursive put method that finds the proper place to put into the RBT
     * 
     * @param h
     * @param key
     * @param value
     * @return inInfo
     * @throws N/A
     */
    private inInfo put(inInfo h,Key key, Value value){
        if (h==null) // if there is nothing within the tree, set first item || or if at bottom of tree, add to bottom
            return new inInfo(key,value,1,RED); // makes new node in the RBT
        int cmp=key.compareTo(h.key); // compares the root key to the inputted key
        if (cmp<0) h.left=put(h.left,key,value); // if inputted key is less then the root key, recall put method with root as root.left
        else if (cmp>0) h.right=put(h.right,key,value); // if inputted key is greater then the root key, recall put method with root as root.right
        else h.value=value; // if the key equals root key, update the value with input key value
        
        if(isRed(h.right) && !isRed(h.left)) h=RotateLeft(h); // if roots right leaf is red AND roots left leaf isnt red, rotate left
        if(isRed(h.left) && isRed(h.left.left)) h=RotateRight(h); // if roots left node is red AND roots left left node is red, rotate right
        if(isRed(h.left) && isRed(h.right)) flipColors(h); // if roots left and right leafs are both red, flip colors
        
        h.n=size(h.left)+size(h.right)+1; // make/update roots height
        return h; // return root
    }
    
    /**
     * prints all nodes within given level
     * 
     * @param root
     * @param level 
     * @return N/A
     * @throws N/A
     */
    void printGivenLevel(inInfo root, int level) 
    {
        if (root == null) // checks to see if there are nodes in the current position
            return;
        if (level == 1) { // prints nodes at the first level
            System.out.print(root.key + " ");
        }
        else if (level > 1) { // any level bigger then 1
            printGivenLevel(root.left, level - 1); // calls itself to go down the left subtree
            printGivenLevel(root.right, level - 1); // then calls itself to go down the right subtree
        }
    }
 
    /**
     * prints level order of RBT
     * 
     * @param N/A
     * @return N/A
     * @throws N/A
     */
    public void printLevelOrder()
    {
        int h = height(root); // finds the height of RBT
        int i;
        for (i = 1; i <= h; i++) { // iterating through the tree
            printGivenLevel(root, i); // prints all nodes within each level
            System.out.print(System.lineSeparator()); // printing format
        }
    }
    
    /**
     * finds the height of the RBT
     * 
     * @param root
     * @return int
     * @throws N/A
     */
    int height(inInfo root)  
    {
        if (root == null) // checking to see if there is a node within the RBT
            return 0;
        else {
            int lheight = height(root.left); // finding the height of the left subtree's
            int rheight = height(root.right); // finding the height of the right subtree's
 
            if (lheight > rheight) // sees which subtree is longer
                return (lheight + 1); // adding one for the root node
            else
                return (rheight + 1); // adding one for the root node
        }
    }
    
    /**
     * This method calls keys(inInfo root)
     */
    public void keys() { keys(root); }

    /**
     * printing out the RBT in order traversal
     * 
     * @param root 
     * @returns N/A
     * @throws N/A
     */
    void keys(inInfo root){ 
        if (root != null) {  // making sure there is a node in current position
            keys(root.left); // going down the left side of the RBT
            System.out.println(root.key+" "+ root.value); // printing the farthest left node 
            keys(root.right); // goes down the right side of the RBT if the left side is gone
        }
    }
    
    /**
     * Checks to see if the RBT is empty
     * 
     * @param N/A
     * @return boolean
     * @throws N/A
     */
    private boolean isEmpty(){
        return root==null ? true:false;
    }
    
    /**
     * gets the smallest key in the tree
     * 
     * @param N/A
     * @return Key
     * @throws N/A
     */
    public Key min(){
        if(isEmpty()) return null; // checks if the RBT is empty
        inInfo x= min(root); // calls recursive function with root
        return x.key; // returns final value
    }
    
    private inInfo min(inInfo x){
        if(x.left==null)return x; // goes to the next smallest data if there is one
        return min(x.left); // calls the recursive function again
    }
    /**
     * gets the smallest key value in the tree
     * 
     * @param N/A
     * @return Value
     * @throws N/A
     */
    public Value minKeyValue(){
        if(isEmpty()) return null; // checks if the RBT is empty
        inInfo x= minKeyValue(root); // calls recusive function with root
        return x.value; // returns final value
    }
    
    private inInfo minKeyValue(inInfo x){ 
        if(x.left==null)return x; // goes to the next smallest data if there is one
        return minKeyValue(x.left); // calls the recursive function agian
    }
    
    /**
     * gets the Largest key in the tree
     * 
     * @param N/A
     * @return Key
     * @throws N/A
     */
    public Key max(){
        if(isEmpty()) return null; // checks if the RBT is empty
        inInfo x=max(root); // calls the recursive functions with the root
        return x.key; // returns the final value
    }
    
    private inInfo max(inInfo x){
        if(x.right==null) return x; // goes to the next largest data if there is one
        return max(x.right); // calls the recursive funtion again
    }
    /**
     * gets the Largest key value in the tree
     * 
     * @param N/A
     * @return Value
     * @throws N/A
     */
    public Value maxKeyValue(){
        if(isEmpty()) return null; // checks if the RBT is empty
        inInfo x=maxKeyValue(root); // calls the recursive function with the root
        return x.value; // returns the final value
    }
    
    private inInfo maxKeyValue(inInfo x){
        if(x.right==null) return x; // goes to the next largest data if there is one
        return max(x.right); // calls the recursive funtion again
    }
    
}   

