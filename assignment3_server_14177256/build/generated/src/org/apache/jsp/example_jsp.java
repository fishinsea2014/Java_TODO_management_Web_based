package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class example_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("  \n");

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

      out.write("  \n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">  \n");
      out.write("<html>  \n");
      out.write("    <head>  \n");
      out.write("        <base href=\"");
      out.print(basePath);
      out.write("\">  \n");
      out.write("\n");
      out.write("        <title>My JSP 'city.jsp' starting page</title>  \n");
      out.write("\n");
      out.write("        <meta http-equiv=\"pragma\" content=\"no-cache\">  \n");
      out.write("        <meta http-equiv=\"cache-control\" content=\"no-cache\">  \n");
      out.write("        <meta http-equiv=\"expires\" content=\"0\">      \n");
      out.write("        <meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">  \n");
      out.write("        <meta http-equiv=\"description\" content=\"This is my page\">  \n");
      out.write("        <!-- \n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\"> \n");
      out.write("        -->  \n");
      out.write("\n");
      out.write("        <script type=\"text/javascript\" src=\"jquery-3.2.1.js\"></script>  \n");
      out.write("\n");
      out.write("\n");
      out.write("        <script type=\"text/javascript\">\n");
      out.write("            $(document).ready(function () {\n");
      out.write("                //List the content of xml when open the webpage.\n");
      out.write("                $.ajax({url: \"./data.xml\",\n");
      out.write("                    success: function (xml) {\n");
      out.write("                        var dtable = $(\"<table border=\\\"1\\\"><tr><td>ID</td><td>DESCRIPTION</td></tr>\");\n");
      out.write("                        $(xml).find(\"todo\").each(function () {\n");
      out.write("                            var ids = $(this).children(\"id\").text();\n");
      out.write("                            var des = $(this).children(\"description\");\n");
      out.write("                            dtable.append(\"<tr><td>\" + ids + \"</td><td>\" + des.text() + \"</td></tr>\");\n");
      out.write("                        });\n");
      out.write("                        dtable.append(\"</table>\");\n");
      out.write("                        $(\"#dis\").html(dtable);\n");
      out.write("                    }//success end\n");
      out.write("                });//ajax end\n");
      out.write("\n");
      out.write("                //Rest the webpage, list all todos.\n");
      out.write("                $(\"#reset\").click(function () {\n");
      out.write("                    $.ajax({url: \"./data.xml\",\n");
      out.write("                        success: function (xml) {\n");
      out.write("                            var keyword = $(\"#search\").val();\n");
      out.write("                            var dtable = $(\"<table border=\\\"1\\\"><tr><td>ID</td><td>DESCRIPTION(search)</td></tr>\");\n");
      out.write("                            $(xml).find(\"todo\").each(function () {\n");
      out.write("                                dtable.append(\"<tr></tr>\")\n");
      out.write("                                var des = $(this).children(\"description\").text();\n");
      out.write("                                var ids = $(this).children(\"id\").text();\n");
      out.write("                                dtable.append(\"<tr><td>\" + ids + \"</td><td>\" + des + \"</td></tr>\");\n");
      out.write("                            });//find end\n");
      out.write("                            dtable.append(\"</table>\");\n");
      out.write("                            $(\"#dis\").html(dtable);\n");
      out.write("                        }//success end    \n");
      out.write("                    }); //serach change function\n");
      out.write("                }); //click end\n");
      out.write("\n");
      out.write("                //Search the description with keywords\n");
      out.write("                $(\"#btns\").click(function () {\n");
      out.write("                    $.ajax({url: \"data.xml\",\n");
      out.write("                        success: function (xml) {\n");
      out.write("                            var keyword = $(\"#search\").val();\n");
      out.write("                            var dtable = $(\"<table border=\\\"1\\\"><tr><td>ID</td><td>DESCRIPTION(search)</td></tr>\");\n");
      out.write("                            $(xml).find(\"todo\").each(function () {\n");
      out.write("                                dtable.append(\"<tr></tr>\")\n");
      out.write("                                var des = $(this).children(\"description\").text();\n");
      out.write("//                                    dtable.append(\"<tr><td>\" + \"ids\" + \"</td><td>\" + des + \"</td></tr>\")\n");
      out.write("                                if (des.indexOf(keyword) > -1) {\n");
      out.write("                                    var ids = $(this).children(\"id\").text();\n");
      out.write("                                    dtable.append(\"<tr><td>\" + ids + \"</td><td>\" + des + \"</td></tr>\");\n");
      out.write("                                }//if end\n");
      out.write("                            });//find end\n");
      out.write("                            dtable.append(\"</table>\");\n");
      out.write("                            $(\"#dis\").html(dtable);\n");
      out.write("                        }//success end    \n");
      out.write("                    }); //serach change function\n");
      out.write("                }); //click end\n");
      out.write("\n");
      out.write("                //Change the layout of the table.\n");
      out.write("                $(\"#swLayout\").change(function () {\n");
      out.write("                    var layout = $(\"#swLayout\").val();\n");
      out.write("                    if (layout == \"Default Layout\") {\n");
      out.write("                        $(\"table\").css({\"color\": \"black\", \"background-color\": \"#FFFFFF\"});\n");
      out.write("                    }//end of if\n");
      out.write("                    else {\n");
      out.write("                        $(\"table\").css({\"color\": \"white\", \"background-color\": \"#000000\"});\n");
      out.write("                    }//end of else\n");
      out.write("                });//layout change end\n");
      out.write("\n");
      out.write("            });//document ready end\n");
      out.write("        </script>  \n");
      out.write("\n");
      out.write("    </head>  \n");
      out.write("\n");
      out.write("    <body> \n");
      out.write("        <p>\n");
      out.write("            <select id=\"swLayout\" style=\"width:200\">\n");
      out.write("                <option>Default Layout</option>\n");
      out.write("                <option>Energy Saving Layout</option>\n");
      out.write("        </p>\n");
      out.write("        <br />\n");
      out.write("\n");
      out.write("        <h1>TODO INFORMATION MANAGEMENT</h1>\n");
      out.write("\n");
      out.write("        <p>\n");
      out.write("            <input id=\"search\" type=\"text\" />\n");
      out.write("            <button id=\"btns\" >Search</button>\n");
      out.write("        </p>\n");
      out.write("        <div id=\"dis\">TODO LIST</div>\n");
      out.write("        <button id=\"reset\" >Reset</button>\n");
      out.write("\n");
      out.write("\n");
      out.write("    </body>  \n");
      out.write("</html>  ");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
