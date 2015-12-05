package shells;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Дмитрий on 05.12.2015.
 */
public class Shell implements Serializable{
    private Map<TypeObject, Object> objectList;

    public Shell() {
        this.objectList = new HashMap<TypeObject, Object>();
    }

    public Map<TypeObject, Object> getObjectList() {
        return objectList;
    }

    public void addObjectList(TypeObject type, Object object) {
        this.objectList.put(type, object);
    }

    public void clearList(){
        objectList.clear();
    }
}
