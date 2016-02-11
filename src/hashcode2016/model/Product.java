/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode2016.model;

/**
 *
 * @author Goncalo
 */
public class Product {
    public int ID; //Tipo
    public int weight;

    public Product(int ID, int weight) {
        this.ID = ID;
        this.weight = weight;
    }
    
    public Product(Product p) {
        this.ID = p.ID;
        this.weight = p.weight;
    }
    
}
