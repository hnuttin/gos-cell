package org.gameofservices.cell;

import org.apache.commons.httpclient.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PreviousStatusServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(PreviousStatusServlet.class);

    public PreviousStatusServlet() {
        LOGGER.info("PreviousStatusServlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final boolean previousStatus = CellContext.CELL_STATUS_PROVIDER.getPreviousStatus(Integer.parseInt(request.getParameter("generation")));
            if (previousStatus) {
                response.setStatus(HttpStatus.SC_OK);
            } else {
                response.setStatus(HttpStatus.SC_NOT_FOUND);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
