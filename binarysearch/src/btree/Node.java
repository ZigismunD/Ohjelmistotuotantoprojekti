/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package btree;

/**
 *
 * @author kamaj
 */
public class Node {
    private String data;
    private BinaryTree left;
    private BinaryTree right;
    private int level;

    public Node(String value, int level) {
        data = new String(value);
        left = right = null;
        this.level = level;
    }
    public  Node (String value, BinaryTree left, BinaryTree right) {
        data = new String(value);
        this.left = left;
        this.right = right;
    }
    public String getData() {
        return data;
    }
    public void setData(String data){
        data = data;
    }
    public BinaryTree left() {
        return left;
    }
    public BinaryTree right() {
        return right;
    }
    public void setLeft(BinaryTree tree) {
        left = tree;
    }
    public void setRight(BinaryTree tree) {
        right = tree;
    }
    public int getLevel(){
        return level;
    }
    public void setLevel(int Level){
        level = Level;
    }


}
