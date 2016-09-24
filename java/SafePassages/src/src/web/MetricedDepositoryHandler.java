package web;

import entity.Depository;
import entity.MetricedDepository;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.simple.JSONArray;
import query.SafePassages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MetricedDepositoryHandler extends AbstractHandler {

    private final SafePassages safePassages;

    public MetricedDepositoryHandler(SafePassages safePassages){
        this.safePassages = safePassages;
    }

    public void handle( String target,
                        Request baseRequest,
                        HttpServletRequest request,
                        HttpServletResponse response ) throws IOException,
            ServletException
    {
        // Declare response encoding and types
        response.setContentType("application/json; charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");

        // Declare response status code
        response.setStatus(HttpServletResponse.SC_OK);

        // Write back response
        List<MetricedDepository> depositories = safePassages.getDepositoriesWithMetrics();

        JSONArray jArray = new JSONArray();
        for(MetricedDepository depository : depositories ){
            jArray.add(depository.asJsonObject());
        }

        response.getWriter().println(jArray.toString());

        // Inform jetty that this request has now been handled
        baseRequest.setHandled(true);
    }

}
