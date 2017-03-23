package WDC;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * REST application class
 * @author Jordan Fike
 */

//Mark this path as "app" for the JSON

@ApplicationPath("app")
public class WDC_Config extends Application {

    /*
        Instantiate our web service class when running the service
        Add other appropriate service classes as needed
    */
    @Override
    public Set<Object> getSingletons() {
        HashSet<Object> instances = new HashSet<>();
        instances.add(new WDC_Service());
        return instances;
    }
}

