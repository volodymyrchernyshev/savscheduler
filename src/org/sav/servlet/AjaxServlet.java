package org.sav.servlet;

import org.sav.service.AjaxService;
import org.sav.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxServlet extends GenericServlet{

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String serviceName = request.getParameter("service");
        String action = request.getParameter("action");

        AjaxService service = ServiceFactory.getInstance().getService(serviceName);
        if(service == null){
            forward("ajaxFail.jsp", request, response);
        } else {
            Object result = service.invokeAction(action, request);
            processResult(result, request, response);
        }
    }

}
