<%@page import="java.io.File"
%><%@page import="java.io.FileInputStream"
%><%@page import="javax.servlet.ServletOutputStream"
%><%
  String filename = request.getParameter("filename");
  String filetype = request.getParameter("filetype");
  response.setContentType("application/octet-stream");
  response.setHeader("Content-Disposition","attachment;filename=\"" + filename + "." + filetype + "\"");
  String source = getServletContext().getRealPath("temp") + File.separator + filename;
  File file = new File(source);
  FileInputStream fileIn = new FileInputStream(file);
  ServletOutputStream outputStream = response.getOutputStream();
  
  byte[] outputByte = new byte[4096];
  int byteRead;
  while((byteRead=fileIn.read(outputByte,0,4096))!=-1) {
    outputStream.write(outputByte,0,byteRead);
  }
  fileIn.close();
  outputStream.flush();
  outputStream.close();
  file.delete();
%>