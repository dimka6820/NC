package basket.model.planets;

import basket.Type;
import basket.model.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Дмитрий on 09.11.2015.
 */
public class Plant {
    private Random random = new Random();
    private int count;
    private Type plane;

    public Plant(Type plane) {
        this.count = random.nextInt(10)+1;
        this.plane = plane;
    }

    public int getCount() {
        return count;
    }


    public Type getPlane() {
        return plane;
    }

    public void setPlane(Type plane) {
        this.plane = plane;
    }


    //    protected List<Product> products;
//
//    public Planet() {
//        this.products = new ArrayList<Product>();
//    }
//
//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
}
