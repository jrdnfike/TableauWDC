package WDC;

import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.core.Response;

/**
 * Intermediate class sending JSON response to the web layer
 * @author Jordan Fike
 */

//Mark this path as "app/service" for the JSON
@Path("service")
public class WDC_Service {
    
    //Instance of query manager
    private final DB_Interface my_database;
    
    //Initialization
    public WDC_Service()
    {
        my_database = new DB_Interface();
    }
    
    //Run query and send back result as JSON
    @GET
    @Path("query.json")
    @Produces({APPLICATION_JSON})
    public Response runQuery()
    {
        String queryData = my_database.db_query(
                "select name, weight from data_table limit 10");
        
        System.out.println(queryData);
        
        Response json = Response.ok(queryData).build();
        
        return json;
    }
    
    @POST
    public Response displayError(
            @QueryParam("errorMsg") String errorMsg)
    {
        System.out.println(errorMsg);
        
        return Response.ok().build();
    }
    
    
}
