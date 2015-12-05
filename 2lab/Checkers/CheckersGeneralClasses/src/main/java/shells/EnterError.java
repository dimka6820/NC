package shells;

import java.io.Serializable;

/**
 * Created by Дмитрий on 05.12.2015.
 */
public class EnterError implements Serializable {
    private String error;

    public EnterError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "error='" + error ;
    }
}
