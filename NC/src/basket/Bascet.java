package basket;

import basket.model.planets.Plant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Дмитрий on 09.11.2015.
 */
public class Bascet {
    private Map<Type, Integer> basket = new HashMap<Type, Integer>();

    public Map<Type, Integer> getBasket() {
        return basket;
    }

    public void addPlant(Plant plane, Integer count) {
        basket.put(plane.getPlane(), count);
    }
}
