package hashcode2016.io;

import hashcode2016.Simulation;
import hashcode2016.model.Drone;
import hashcode2016.model.Order;
import hashcode2016.model.Position;
import hashcode2016.model.Product;
import hashcode2016.model.Warehouse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Reader {
    
    private static enum ParamSimualtion {
        MAP_ROWS, MAP_COLS,
        Nr_DRONES, Nr_TURNS, MAX_PAYLOAD
    };
    
    public Simulation readFile(String path){
        Simulation sim = new Simulation();
        try {
            
            Scanner reader = new Scanner(new File(path));     
            
            readParamsSimulation(reader.nextLine(), sim);
            
            List<Product> products = createProducts(reader);
            
            createWarehouses(reader, sim, products);
            
            createOrders(reader,sim, products);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return sim;
    }
    
    
    private void readParamsSimulation(String line, Simulation sim){
        String data[] = line.split(" ");
        
        //set maxLoad in Drone
        Drone.maxLoad = Integer.parseInt(
                data[ParamSimualtion.MAX_PAYLOAD.ordinal()]);
        
        sim.maxTurns = Integer.parseInt(data[ParamSimualtion.Nr_TURNS.ordinal()]);
        
        createDrones(sim,
                Integer.parseInt(data[ParamSimualtion.Nr_DRONES.ordinal()]));
        
        
    }
    
    
    private void createDrones(Simulation sim, int nrDrones) {
            for (int i = 0; i < nrDrones; i++) {
                Drone drone = new Drone();
                drone.ID = i;
                sim.drones.add(drone);
            }
    }
    
    
    private List<Product> createProducts(Scanner reader){
        List<Product> products = new ArrayList<>();
        int nrProducts = reader.nextInt();
        
        String temp;
        while((temp=reader.nextLine()).isEmpty())
            ;
        
        String weigths[] = temp.split(" ");
        for (int i = 0; i < nrProducts; i++) {
            Product prod = new Product(i, Integer.parseInt(weigths[i]));
            products.add(prod);
        }     
        return products;
    }
    
    private void createWarehouses(Scanner reader, Simulation sim,
            List<Product> products){
        
        int nrWarehouses = reader.nextInt();
        for (int i = 0; i < nrWarehouses; i++) {
            Warehouse wh = getWarehouse(reader,i);
            loadWarehouse(wh, products, reader);
            sim.warehouses.add(wh);
        }
    }
    
    private Warehouse getWarehouse(Scanner reader, int id) {
        Warehouse warehouse = new Warehouse();
        warehouse.ID = id;
        
        String temp;
        while((temp=reader.nextLine()).isEmpty())
            ;
        
        String position[] = temp.trim().split(" ");
        warehouse.position = new Position(Integer.parseInt(position[0]),
                                            Integer.parseInt(position[1]));
        return warehouse;
    }
    
    private void loadWarehouse(Warehouse wh, List<Product> products,
            Scanner reader) {
        String[] qty = reader.nextLine().split(" ");
        int size = products.size();
        for (int i = 0; i < size ; i++) {
            wh.products.put(products.get(i), Integer.parseInt(qty[i]));
        }
    }
 
    private void createOrders(Scanner reader, Simulation sim, 
            List<Product> products) {
        
        int nrOrders = reader.nextInt();
        for (int i = 0; i < nrOrders; i++) {
            sim.orders.add(getOrder(i,products,reader));
        }
    }
    
    private Order getOrder(int id, List<Product> products, Scanner reader){
        Order order = new Order();
        
        order.ID = id;
        
        String temp;
        while((temp=reader.nextLine()).isEmpty())
            ;
        
        String[] pos = temp.trim().split(" ");
        order.destination = new Position(Integer.parseInt(pos[0]),
                                         Integer.parseInt(pos[1]));
        
        int nrItems = reader.nextInt();
        
        
        while((temp=reader.nextLine()).isEmpty())
            ;
        
        String[] idsProducts = temp.trim().split(" ");
        int size = idsProducts.length;
        for (int i = 0; i < size; i++) {
            order.products.put(products.get(Integer.parseInt(idsProducts[i])),
                    nrItems);
        }
        
        return order;
    }
    
}
