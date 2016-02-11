/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode2016;

import hashcode2016.model.Drone;
import hashcode2016.model.Order;
import hashcode2016.model.ProductPackage;
import hashcode2016.model.Warehouse;
import java.util.ArrayList;

/**
 *
 * @author Goncalo
 */
public class Simulation {
    
    public int maxTurns;
    public ArrayList<Drone> drones;
    public ArrayList<Order> orders;
    public ArrayList<Warehouse> warehouses;
    
    
    //Returns a list of all needed commands
    public ArrayList<String> simulate(){
        ArrayList<String> commands = new ArrayList<>();
        for(int i = 0; i < orders.size(); i++){
            Order o = orders.get(i);
            ArrayList<ProductPackage> packages = o.getPackages();
            for (int j = 0; j < packages.size(); j++) {
                ProductPackage p = packages.get(j);
                Drone d = chooseBestDrone(p, o);
                String loadCommand = d.load(p);
                String deliverCommand = d.deliver(o.destination);
                //Adicionar a lista de comandos
                commands.add(loadCommand);
                commands.add(deliverCommand);
            }
        }
        return commands;
    }
    
    private Drone chooseBestDrone(ProductPackage p, Order o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
