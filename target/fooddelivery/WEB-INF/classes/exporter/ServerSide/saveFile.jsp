<%@page import="java.io.File"
%><%@page import="java.io.FileWriter"
%><%
    String data = request.getParameter("data");
    File file = new File(getServletContext().getRealPath("temp") + File.separator + request.getParameter("filename"));
    FileWriter writer = new FileWriter(file);
    writer.write(data);
    writer.close();
%>