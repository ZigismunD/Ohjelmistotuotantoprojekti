/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binaryheap;

import java.util.ArrayList;

/**
 *
 * @author RJulin
 */
    
 
public class BinaryHeap {
    
    private ArrayList<Integer> heap;
    private int heapSize = 0;
    
    public BinaryHeap() {
        heap = new ArrayList<>();
        heapSize = 0;
    }
    
    public void insert(int data) {
        heap.add(data);
        
        int position = heapSize;
        int temp;
        
        //Järjestä heap uudelleen
        while (position != 0) {
            //Vertaa arvoa parentiin
            int parent_position = (position - 1) / 2;
            
            //Jos nykypositio arvo on suurempi kuin parentin arvo
            if (heap.get(position) > heap.get(parent_position)) {
                //poistu
                break;
            } else {
                //muuten vaihda paikkaa parentin kanssa
                temp = heap.get(parent_position);
                heap.set(parent_position, heap.get(position));
                heap.set(position, temp);
                //Aseta uusi positio
                position = parent_position;
            }
        }
        heapSize++;
    }
    
    public void deleteMin() {
        //Aseta nollapositioon viimeinen arvo
        heap.set(0, heap.get(heapSize - 1));
        
        //Poista viimeinen arvo
        heap.remove(heapSize - 1);        

        //Poistettava arvo on positiossa 0
        int position = 0;
        int left;
        int right;
        int temp;
        
        while (true) {
            left  = 2 * position + 1;
            right = 2 * position + 2;
            
            //Poistutaan kun on päästy taulun loppuun 
            if (left > heapSize) {
                break;
            //Kumpi pienempi lapsi vaihdetaan tilalle
            } else if (left == heapSize || heap.get(left) < heap.get(right)) {
                //Aseta arvo talteen
                temp = heap.get(position);
                //Vaihda arvo vasemman kanssa
                heap.set(position, heap.get(left));
                heap.set(left, temp);
                position = left;
            } else if (heap.get(left) > heap.get(right)) {
                //Aseta arvo talteen
                temp = heap.get(position);
                //Oikea
                heap.set(position, heap.get(right));
                heap.set(right, temp);
                position = right;
            } else {
                break;
            }
            
        }
        //Poista viimeinen arvo
        heapSize--;
    }
    
    public void print() {
        System.out.println(heap);
    }
}