<%@ page import="org.apache.commons.io.IOUtils" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    out.println("123");
    Collection<Part> parts = request.getParts();

    for (Part part : parts) {
        if (part.getSubmittedFileName() != null) {
            out.println(part.getName() + ":" + part.getSubmittedFileName() + "<br/>");
        } else {
            out.println(part.getName() + ":" + IOUtils.toString(part.getInputStream()) + "<br/>");
        }
    }
%>