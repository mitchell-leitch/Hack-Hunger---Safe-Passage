package web;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import query.SafePassages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
        List<String> schoolNames = safePassages.getSchoolNames();

        Collections.sort(schoolNames);

        response.getWriter().println("<h1>School names</h1>");
        response.getWriter().print("<ul>");
        for(String name : schoolNames){
            response.getWriter().println("<li>" + name + "</li>");
        }
        response.getWriter().println("</ul>");

        // Inform jetty that this request has now been handled
        baseRequest.setHandled(true);
    }


}
