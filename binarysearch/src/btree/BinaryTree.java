/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package btree;

/**
 *
 * @author kamaj
 */
public class BinaryTree {

    private Node root;

    public BinaryTree(String rootValue, int level) {
        root = new Node(rootValue, level); //vastaan otettava kerros +1
    }

    public BinaryTree() {
        root = null;
    }

    /*public BinaryTree(String rootValue, BinaryTree left, BinaryTree right){
        root = new Node(rootValue, left, right);
    } */
    public void insert(String aData) {// Insert ja find hyvin samanlaisia
        if (root == null) {
            root = new Node(aData,1); //       
        } else {
            if (Integer.parseInt(root.getData()) > Integer.parseInt(aData)) {
                if (root.left() == null) { // pääseeekö vasemmalle?
                    this.setLeft(new BinaryTree(aData, root.getLevel() + 1));
                } else {
                    root.left().insert(aData);
                }
            } else {
                if (root.right() == null) { // pääseeekö vasemmalle?
                    this.setRight(new BinaryTree(aData, root.getLevel() + 1));
                } else {
                    root.right().insert(aData);
                }
            }
        }

    }
         
    public BinaryTree find(String aData){

        BinaryTree tempData = null;
        String value = root.getData();
               
        if(Integer.parseInt(value) > Integer.parseInt(aData)){
          tempData = root.left().find(aData);
        } else if(Integer.parseInt(value) < Integer.parseInt(aData)){
          tempData = root.right().find(aData);
        } else if (Integer.parseInt(value) == Integer.parseInt(aData)){
            tempData = this;
        }
        //Printtaustesti tulostaa polun
        if(tempData != null){
             System.out.println(value +" ja korkeus: " + root.getLevel());
        }
        return tempData;
    }
  

    public void preOrder() {
        if (root != null) {
            System.out.println(root.getData() + ',');
            if (root.left() != null) // pääseeekö vasemmalle?
            {
                root.left().preOrder();
            }
            if (root.right() != null) // pääseekö oikealle?
            {
                root.right().preOrder();
            }
        }

    }
    

public void deleteNode(String aData, BinaryTree parent) {
        
        String value = root.getData();

        //Onko poistettava data vasemmalla vai oikealla oksalla
        if(Integer.parseInt(value) > Integer.parseInt(aData)){
            root.left().deleteNode(aData, this);
        } else if(Integer.parseInt(value) < Integer.parseInt(aData)){
            root.right().deleteNode(aData, this);
        } else if (Integer.parseInt(value) == Integer.parseInt(aData)){
           
            //Molemmat oksat käytössä
            if (root.right() != null && root.left() != null) {
                //Aseta pienin arvo tilalle
                aData = root.right().getMin().getData();
                root.setData(aData);
                //Etsi ja poista siirretty arvo
                root.right().deleteNode(aData, this);
            //Onko root parentin vasemmalla
            } else if (parent.root.left() == this) {
                //Onko rootilla vasen
                if (root.left() != null) {
                    //Aseta parentin vasemmaksi rootin vasen
                    parent.setLeft(root.left());
                //Muussa tapauksessa parentin vasemmaksi rootin oikea
                } else {
                    parent.setLeft(root.right());
                }
            //Onko root parentin oikealla
            } else if (parent.root.right() == this) {
                //Onko rootilla vasen
                if (root.left() != null) {
                    //Aseta parentin oikeaksi rootin vasen
                    parent.setRight(root.left());
                //Muussa tapauksessa parentin oikeaksi rootin oikea
                } else {
                    parent.setRight(root.right());
                }
            }
        }
    }

    public Node getMin() {
        Node tempNode = root;
        
        if (tempNode.left() != null) {
            tempNode = tempNode.left().getMin(); 
        }
        
        return tempNode;
    }
    
    public void setLeft(BinaryTree tree) {
        root.setLeft(tree);
    }

    public void setRight(BinaryTree tree) {
        root.setRight(tree);
    }
}
