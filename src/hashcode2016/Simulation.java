/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode2016;

import hashcode2016.model.Drone;
import hashcode2016.model.Order;
import hashcode2016.model.Position;
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

    public Simulation() {
        drones = new ArrayList<>();
        orders = new ArrayList<>();
        warehouses = new ArrayList<>();
    }

    //Returns a list of all needed commands
    public ArrayList<String> simulate() {
        ArrayList<String> commands = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            Order o = orders.get(i);
            ArrayList<ProductPackage> packages = o.getPackages(this);
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
        Drone bestDrone = null;
        int bestTurns = maxTurns;
        for (int i = 0; i < drones.size(); i++) {
            Drone d = drones.get(i);
            int turnsToLoad = (int) distance(d.position, p.position) + 1;
            int turnsToDeliver = (int) distance(p.position, o.destination) + 1;
            int cTurns = d.turn + turnsToLoad + turnsToDeliver;
            if (cTurns < bestTurns) {
                bestTurns = cTurns;
                bestDrone = d;
            }
        }
        return bestDrone;
    }

    private double distance(Position p, Position p2) {
        double distance = Math.ceil(Math.sqrt(Math.pow(p.x - p2.x, 2) + Math.pow(p.y - p2.y, 2)));
        return distance;
    }
}
