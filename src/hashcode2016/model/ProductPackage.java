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
public class ProductPackage {
    
    public HashMap<Product, Integer> products; //Produtos/quantidade
    public int weight;
    public int warehouseID;
    public Position position;

    public ProductPackage() {
        products = new HashMap<>();
    }
}
