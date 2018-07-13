# Java_TODO_management_Web_based
Create a web application that provides a simple web-based persistency service for contacts.
1. PART 1 – SERVER [8 marks]
a. In NetBeans, create a Java Web Application assignment3_server_<your
student id>
b. In this project, create a package
nz.ac.massey.cs.webtech.ass3.s_<your student id>.server
c. In this package, create a servlet TODOManagerServlet , and map this to the
URL /todos
d. The purpose of this servlet is to manage the persistency of todo data
submitted over the network. Objects are stored in an xml file data.xml within
the root folder of web application, XMLDecoder / XMLEncoder in java.beans
can be used for this purpose.
e. The following actions should be supported, a todo has an id (number) and a
description (text)
Todo information is to be encoded using json, for instance:
{“id”:”42”,”description”:”go to bed and sleep”} , sets of
ToDos should be encoded as json arrays.
2. PART 2 – CLIENT [8 marks]
a. In NetBeans, create a Java Application (note that this is not a Java Web
project!!) assignment3_client_<your student id>
b. In this project, create a package
nz.ac.massey.cs.webtech.ass3.s_<your student id>.client
c. In this project, create a Java class Todo with properties for id (type int )
and description (type String )
d. In the client package, create a class ToDoManager with methods as shown
in the above table in column 4. This class uses an http client to send the
actual requests to create (insert), update, delete or fetch contacts to the
server, using URLs and methods as defined in the table above. The server
address can be hardcoded in a static variable, e.g.
public static final String SERVER_URL =
“http://localhost:8084/”;
e. In the client package, implement a class TestToDoManager that contains
JUnit 4 tests to test the methods implemented in ToDoManager . There
should be at least one test for each method in ToDoManager .
3. PART 3 – WEB-UI [4 marks]
a. In the web application created in part 1, create a JSP page index.jsp .
b. This page should have the following features:
i. Todos are displayed in an html table
ii. A search box can be used to search todos. If a search term is entered
and a search button is pressed, the todos with descriptions not
matching the search term are removed or rendered invisible. This
must not involve any network request!
iii. A reset button is used to reset the table: if pressed, all contacts will be
displayed. This must not involve any network request!
iv. On top of the page, there is a combobox that can be used to switch
between a default layout (dark fonts on white background) and an
energy saving layout (white fonts on black background). This must not
involve any network request!
