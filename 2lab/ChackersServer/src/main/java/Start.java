import org.apache.log4j.Logger;

/**
 * Created by Дмитрий on 04.11.2015.
 */
public class Start {
    public static void main(String[] args) {
         Logger log = Logger.getLogger(Start.class.getName());
        try
        {
            System.out.println(2/0);
        }catch (Exception e)
        {
            log.info(e);
        }
    }
}
