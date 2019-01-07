<%="Hello World."%>
<hr/>
<%=request.getParameter("password")%>
<hr/>
<%!
    boolean login(String password) {
        return "023".equals(password);
    }
%>

<%
    String password = request.getParameter("password");

    out.println(password);

    if (login(password)) {
        out.println("Hello");
    } else {
        out.println("World~");
    }
%>
