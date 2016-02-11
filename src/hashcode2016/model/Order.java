/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashcode2016.model;

import hashcode2016.Simulation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Goncalo
 */
public class Order {

    public int ID;
    public HashMap<Product, Integer> products; //Produtos/quantidade
    public Position destination;

    public Order() {
        products = new HashMap<>();
    }

    public ArrayList<ProductPackage> getPackages(Simulation s) {
        ArrayList<ProductPackage> packages = new ArrayList<>();
        List<Warehouse> warehousesByDistance = getWarehousesByDistance(s);
        for (Warehouse w : warehousesByDistance) {
            HashMap<Product, Integer> warehouseProds = getProductsInWarehouse(w);
            List<Product> prodList = buildProductList(warehouseProds);
            ProductPackage pkg = new ProductPackage();
            List<Product> prodPkg = fillPackage(prodList, Drone.maxLoad);
            pkg.products = buildProductMap(prodPkg);
            pkg.position = destination;
            pkg.warehouseID = w.ID;
            pkg.weight = sumList(prodPkg);
            packages.add(pkg);
        }
        return packages;
    }

    private ArrayList<Product> fillPackage(List<Product> productList, int maxWeight){        
        knapsack = new ArrayList<>();
        hasGoalSum(productList, maxWeight);
        return knapsack;
    }
    
    private ArrayList<Product> knapsack = new ArrayList<>();

    //Modified knapsack algorithm
    private boolean hasGoalSum(List<Product> list, int goal) {
        if (list.isEmpty() || goal > sumList(list) || goal < min(list)) {
            return false;
        }
        list = rejectIfGreaterThanGoal(list, goal);
        Product p = containsMaxWeight(list, goal);
        if (p != null) {
            knapsack.add(p);
            return true;
        }

        while (!list.isEmpty()) {
            Product m = max(list);
            list.remove(m);
            Product diff = containsDifference(list, p, goal);
            if (diff != null) {
                knapsack.add(m);
                knapsack.add(diff);
                return true;
            }
            if (hasGoalSum(list, goal - m.weight)) {
                knapsack.add(m);
                return true;
            }
        }

        return false;
    }

    private Product containsDifference(List<Product> list, Product prod, int goal) {
        for (Product p : list) {
            if (p.weight == goal - prod.weight) {
                return p;
            }
        }
        return null;
    }

    private Product containsMaxWeight(List<Product> list, int goal) {
        for (Product p : list) {
            if (p.weight == goal) {
                return p;
            }
        }
        return null;
    }

    private List<Product> rejectIfGreaterThanGoal(List<Product> list, int goal) {
        List<Product> newList = new ArrayList<>();
        for (Product p : list) {
            if (p.weight <= goal) {
                newList.add(p);
            }
        }
        return newList;
    }

    private int sumList(List<Product> list) {
        int sum = 0;
        for (Product p : list) {
            sum += p.weight;
        }
        return sum;
    }

    private int min(List<Product> list) {
        int min = list.get(0).weight;
        for (Product p : list) {
            if (p.weight < min) {
                min = p.weight;
            }
        }
        return min;
    }

    private Product max(List<Product> list) {
        Product max = list.get(0);
        for (Product p : list) {
            if (p.weight > max.weight) {
                max = p;
            }
        }
        return max;
    }

    //Builds a product list to use with the knapsack algorithm
    private List<Product> buildProductList(HashMap<Product, Integer> products) {
        List<Product> prodList = new ArrayList<>();
        Iterator it = products.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry<Product, Integer> pair = (HashMap.Entry<Product, Integer>) it.next();
            for (int i = 0; i < pair.getValue(); i++) {
                prodList.add(new Product(pair.getKey()));
            }
        }
        return prodList;
    }

    private List<Warehouse> getWarehousesByDistance(Simulation s) {
        Warehouse.WarehouseComparator comp = new Warehouse.WarehouseComparator(destination);
        Collections.sort(s.warehouses, comp);
        return s.warehouses;
    }

    private HashMap<Product, Integer> getProductsInWarehouse(Warehouse w) {
        HashMap<Product, Integer> result = new HashMap<>();
        Iterator it = w.products.entrySet().iterator();
        while(it.hasNext()){
            HashMap.Entry<Product, Integer> pair = (HashMap.Entry<Product, Integer>) it.next();
            Iterator itOrder = products.entrySet().iterator();
            while(itOrder.hasNext()){
                HashMap.Entry<Product, Integer> prod = (HashMap.Entry<Product, Integer>) itOrder.next();
                if(pair.getKey().equals(prod.getKey())){
                    //Se a warehouse tem o produto da order exatamente na quantidade necessaria
                    if(pair.getValue() >= prod.getValue()){
                        pair.setValue(pair.getValue() - prod.getValue());
                        result.put(prod.getKey(), prod.getValue());
                        itOrder.remove();
                    }else if(pair.getValue() < prod.getValue()){
                        prod.setValue(prod.getValue() - pair.getValue());
                        it.remove();
                        pair = (HashMap.Entry<Product, Integer>) it.next();
                        result.put(prod.getKey(), prod.getValue());
                    }
                }
            }
        }
        return result;
    }

    private HashMap<Product, Integer> buildProductMap(List<Product> prodPkg) {
        HashMap<Product, Integer> res = new HashMap<>();
        for (Product p : prodPkg) {
            Integer x = res.get(p);
            if(x != null){
                res.put(p, x++);
            }else{
                res.put(p, 1);
            }
        }
        return res;
    }
}
