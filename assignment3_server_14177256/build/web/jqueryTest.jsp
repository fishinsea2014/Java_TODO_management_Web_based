<%-- 
    Document   : index
    Created on : 7/06/2017, 8:35:49 AM
    Author     : QSG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TODOS Information Management</title>
    </head>
    <script src="jquery-3.2.1.js"></script>
    <script type="text/javascript">
        function testjquery()
        {
            var user_name=$("#test").attr("value");
            alert(user_name);
        }
    </script>
    <body>
        <input id="test" value="jquery">
        <input type="button" value="click me" onclick="testjquery()">
    </body>
</html>
