package web;

import entity.School;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import query.SafePassages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class SchoolNameHandler extends AbstractHandler {

    private final SafePassages safePassages;

    public SchoolNameHandler(SafePassages safePassages){
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

        // Write back response
        List<School> schools = safePassages.getSchoolNames();
        SortedMap<String, School> schoolsByName = new TreeMap<>();

        for(School school : schools){
            schoolsByName.put(school.getName(), school);
        }


        response.getWriter().println("<h1>School names</h1>");

        response.getWriter().print("<ul>");
        for(Map.Entry<String, School> pair : schoolsByName.entrySet()){
            response.getWriter().println("<li>" + pair.getValue().getName() + "</li>");
        }
        response.getWriter().println("</ul>");

        // Inform jetty that this request has now been handled
        baseRequest.setHandled(true);
    }


}
