package web;

import entity.Depository;
import entity.School;
import entity.SchoolDepositoryPair;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import query.SafePassages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class NearbyRepositoriesHandler extends AbstractHandler {

    private final SafePassages safePassages;

    public NearbyRepositoriesHandler(SafePassages safePassages){
        this.safePassages = safePassages;
    }

    public void handle( String target,
                        Request baseRequest,
                        HttpServletRequest request,
                        HttpServletResponse response ) throws IOException,
            ServletException
    {
        // Declare response encoding and types
        response.setContentType("text/html; charset=utf-8");

        // Declare response status code
        response.setStatus(HttpServletResponse.SC_OK);

        String schoolName = URLDecoder.decode(request.getQueryString());

        // Write back response
        List<SchoolDepositoryPair> pairs = safePassages.findNearbyDepositories(schoolName);
        SortedMap<Double, SchoolDepositoryPair> pairsByDistance = new TreeMap<>();

        for(SchoolDepositoryPair pair : pairs){
            pairsByDistance.put(pair.getDistance(), pair);
        }

        response.getWriter().print("<h1>Nearby Repositories to ");
        response.getWriter().print(schoolName);
        response.getWriter().println("</h1>");

        response.getWriter().println("<table border=1>");
        response.getWriter().println("<tr>");
        response.getWriter().println("<td>Distance (meters)</td><td>Depository Name</td>");
        response.getWriter().println("<td>Depository Address</td>");
        response.getWriter().println("</tr>");

        for(Map.Entry<Double, SchoolDepositoryPair> pair : pairsByDistance.entrySet()){
            School school = pair.getValue().getSchool();
            Depository depository = pair.getValue().getDepository();
            response.getWriter().println("<tr>");
            response.getWriter().println("<td>" + pair.getKey() + "</td>");
            response.getWriter().println("<td>" + depository.getName() + "</td>");
            response.getWriter().println("<td>" + depository.getAddress() + "</td>");

            response.getWriter().println("</tr>");

        }
        response.getWriter().println("</table>");

        // Inform jetty that this request has now been handled
        baseRequest.setHandled(true);
    }


}
