<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>  

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
    <head>  
        <base href="<%=basePath%>">  

        <title>My JSP 'city.jsp' starting page</title>  

        <meta http-equiv="pragma" content="no-cache">  
        <meta http-equiv="cache-control" content="no-cache">  
        <meta http-equiv="expires" content="0">      
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
        <meta http-equiv="description" content="This is my page">  
        <!-- 
        <link rel="stylesheet" type="text/css" href="styles.css"> 
        -->  

        <script type="text/javascript" src="jquery-3.2.1.js"></script>  


        <script type="text/javascript">
            $(document).ready(function () {
                //List the content of xml when open the webpage.
                $.ajax({url: "./data.xml",
                    success: function (xml) {
                        var dtable = $("<table border=\"1\"><tr><td>ID</td><td>DESCRIPTION</td></tr>");
                        $(xml).find("todo").each(function () {
                            var ids = $(this).children("id").text();
                            var des = $(this).children("description");
                            dtable.append("<tr><td>" + ids + "</td><td>" + des.text() + "</td></tr>");
                        });
                        dtable.append("</table>");
                        $("#dis").html(dtable);
                    }//success end
                });//ajax end

                //Rest the webpage, list all todos.
                $("#reset").click(function () {
                    $.ajax({url: "./data.xml",
                        success: function (xml) {
                            var keyword = $("#search").val();
                            var dtable = $("<table border=\"1\"><tr><td>ID</td><td>DESCRIPTION(search)</td></tr>");
                            $(xml).find("todo").each(function () {
                                dtable.append("<tr></tr>")
                                var des = $(this).children("description").text();
                                var ids = $(this).children("id").text();
                                dtable.append("<tr><td>" + ids + "</td><td>" + des + "</td></tr>");
                            });//find end
                            dtable.append("</table>");
                            $("#dis").html(dtable);
                        }//success end    
                    }); //serach change function
                }); //click end

                //Search the description with keywords
                $("#btns").click(function () {
                    $.ajax({url: "data.xml",
                        success: function (xml) {
                            var keyword = $("#search").val();
                            var dtable = $("<table border=\"1\"><tr><td>ID</td><td>DESCRIPTION(search)</td></tr>");
                            $(xml).find("todo").each(function () {
                                dtable.append("<tr></tr>")
                                var des = $(this).children("description").text();
//                                    dtable.append("<tr><td>" + "ids" + "</td><td>" + des + "</td></tr>")
                                if (des.indexOf(keyword) > -1) {
                                    var ids = $(this).children("id").text();
                                    dtable.append("<tr><td>" + ids + "</td><td>" + des + "</td></tr>");
                                }//if end
                            });//find end
                            dtable.append("</table>");
                            $("#dis").html(dtable);
                        }//success end    
                    }); //serach change function
                }); //click end

                //Change the layout of the table.
                $("#swLayout").change(function () {
                    var layout = $("#swLayout").val();
                    if (layout == "Default Layout") {
                        $("table").css({"color": "black", "background-color": "#FFFFFF"});
                    }//end of if
                    else {
                        $("table").css({"color": "white", "background-color": "#000000"});
                    }//end of else
                });//layout change end

            });//document ready end
        </script>  

    </head>  

    <body> 
        <p>
            <select id="swLayout" style="width:200">
                <option>Default Layout</option>
                <option>Energy Saving Layout</option>
        </p>
        <br />

        <h1>TODO INFORMATION MANAGEMENT</h1>

        <p>
            <input id="search" type="text" />
            <button id="btns" >Search</button>
        </p>
        <div id="dis">TODO LIST</div>
        <button id="reset" >Reset</button>


    </body>  
</html>  