package basket.start;

import basket.Bascet;
import basket.Type;
import basket.model.planets.Plant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 09.11.2015.
 */
public class Start {
    public static void main(String[] args) {
        Bascet basket = new Bascet();
        List<Plant> plantList = new ArrayList<Plant>();

        plantList.add(new Plant(Type.CABBEGE));
        plantList.add(new Plant(Type.CARROT));
        plantList.add(new Plant(Type.STRAWBERRY));

        for (Plant item : plantList)
        {
            basket.addPlant(item, item.getCount());
        }

        System.out.println(basket.getBasket());
    }
}
