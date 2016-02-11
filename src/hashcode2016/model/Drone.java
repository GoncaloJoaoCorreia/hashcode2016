/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode2016.model;

import hashcode2016.Simulation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Goncalo
 */
public class Drone {

    public int ID;
    public Position position;
    public static int maxLoad;
    public int turn;

    public ArrayList<String> deliver(ProductPackage p, Order order) {
        ArrayList<String> commandList = new ArrayList<>();
        Iterator it = p.products.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            Product product = (Product) pair.getKey();
            int amount = (int) pair.getValue();

            String command = "" + ID + " D " + order.ID + " " + product.ID + " " + amount;
            commandList.add(command);

            int turnsToDeliver = (int) Simulation.distance(this.position, order.destination) + 1;
            this.turn += turnsToDeliver;

            this.position = new Position();
            this.position.x = order.destination.x;
            this.position.y = order.destination.y;

            it.remove(); // avoids a ConcurrentModificationException
        }

        return commandList;
    }

    public ArrayList<String> load(ProductPackage p, Position destination) {
        ArrayList<String> commandList = new ArrayList<>();
        Iterator it = p.products.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            Product product = (Product) pair.getKey();
            int amount = (int) pair.getValue();

            String command = "" + ID + " L " + p.warehouseID + " " + product.ID + " " + amount;
            commandList.add(command);

            int turnsToLoad = (int) Simulation.distance(this.position, p.position) + 1;
            this.turn += turnsToLoad;

            this.position = new Position();
            this.position.x = p.position.x;
            this.position.y = p.position.y;

            it.remove(); // avoids a ConcurrentModificationException
        }

        return commandList;
    }

}
