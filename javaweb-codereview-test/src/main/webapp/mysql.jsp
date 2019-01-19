<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%
    String sql = "select post_content from sys_posts where post_id =" + request.getParameter("id");
    String url = "jdbc:mysql://localhost:3306/anbai-zone?autoReconnect=true&zeroDateTimeBehavior=round&useUnicode=true&characterEncoding=UTF-8&useOldAliasMetadataBehavior=true&useOldAliasMetadataBehavior=true";
    String username = "root";
    String password = "root";
    Class.forName("com.mysql.jdbc.Driver");

    out.println("<p>" + sql + "</p>");

    Connection connection = DriverManager.getConnection(url, username, password);
    PreparedStatement pstt = connection.prepareStatement(sql);
    ResultSet rs = pstt.executeQuery();

    while (rs.next()) {
        out.println("<font color='red'>" + rs.getObject(1) + "</font>");
    }

    rs.close();
    pstt.close();
%>