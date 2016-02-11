/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode2016.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Goncalo
 */
public class Order {
    
    public int ID;
    public HashMap<Product, Integer> products; //Produtos/quantidade
    public Position destination;

    public ArrayList<ProductPackage> getPackages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
