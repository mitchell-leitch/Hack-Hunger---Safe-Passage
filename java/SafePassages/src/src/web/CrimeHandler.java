package web;

import entity.Crime;
import entity.Depository;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.json.simple.JSONArray;
import query.SafePassages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CrimeHandler extends AbstractHandler {

    private final SafePassages safePassages;

    public CrimeHandler(SafePassages safePassages){
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
        List<Crime> crimes = safePassages.getCrimes();

        JSONArray jArray = new JSONArray();
        for(Crime crime : crimes ){
            jArray.add(crime.asJsonObject());
        }

        response.getWriter().println(jArray.toString());

        // Inform jetty that this request has now been handled
        baseRequest.setHandled(true);
    }

}
