package com.example;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

public class TableBuilder extends HttpServlet {
    int accesses = 0;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // RequestDispatcher rd = request.getRequestDispatcher("hello.jsp");
        // rd.forward(request, response);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter(); //necessary for writing out 

        CRUDoperations Database = new CRUDoperations();
        ArrayList<Trip> TripRecord = Database.GetAllTrips(); //Gets all trips that are recorded.
        DistanceCalculator DistanceUpdater = new DistanceCalculator();

        if ((request.getParameterMap().containsKey("cost"))) {
            try {
                Double Cost = Double.parseDouble(request.getParameter("cost"));

                DistanceUpdater.GenerateDistance(Database, TripRecord, Cost);
            } catch (Exception e) {
                out.println(e);
            }
        }

        out.println("<div style='margin: auto; width: fit-content'>");
            out.println("<form action='/demo/hello'>");
            out.println("<label for='cost'>Amount Spent:</label><br>");
            out.println("<input type='text' id='cost' name='cost'><br>");
            out.println("<input type='submit' value='Submit'>");
        out.println("</form>");

        out.println("<table> <tr> <th>TripID</th> <th>StartTime</th> <th>EndTime</th> <th>Distance</th> <th>Cost</th> </tr>");
        for (int i = TripRecord.size() - 1; !(i < 0); i--) {
            Trip CurrentTrip = TripRecord.get(i);
            out.println("<tr> <td>"+CurrentTrip.GetTripId()+"</td> <td>"+CurrentTrip.GetStartTrip()+"</td> <td>"+CurrentTrip.GetEndTrip()+"</td> <td>"+CurrentTrip.GetDistance()+"</td> <td>"+CurrentTrip.getCost()+"</td> </tr>");
        }
        out.println("</table>");

        out.println("</div>");
    }
}