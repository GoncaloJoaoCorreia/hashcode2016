/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode2016.model;

import hashcode2016.Simulation;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author Goncalo
 */
public class Warehouse{
    
    public int ID;
    public Position position;
    public HashMap<Product, Integer> products; //Produtos/quantidade

    public Warehouse() {
        products = new HashMap<>();
    }    

    public static class WarehouseComparator implements Comparator<Warehouse> {

        private Position destination;
        
        public WarehouseComparator(Position destination){
            this.destination = destination;
        }
        
        @Override
        public int compare(Warehouse o1, Warehouse o2) {
            int distance1 = (int) Simulation.distance(o1.position, destination);
            int distance2 = (int) Simulation.distance(o2.position, destination);
            return distance2 - distance1;
        }
        
    }
    
}
