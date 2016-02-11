/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode2016.model;

import hashcode2016.Simulation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Goncalo
 */
public class Order {

    public int ID;
    public HashMap<Product, Integer> products; //Produtos/quantidade
    public Position destination;

    public ArrayList<ProductPackage> getPackages(Simulation s) {
        ArrayList<ProductPackage> packages = new ArrayList<>();
        Iterator it = products.entrySet().iterator();
        while (!it.hasNext()) {
            ProductPackage p = new ProductPackage();
            while(p.weight < /*s.maxDroneLoad*/ s.drones.get(0).maxLoad){
                Product next = (Product)it.next();
                
            }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
