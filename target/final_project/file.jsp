<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Picture</title>
</head>

<body>
    <center>
        <h1>Upload File Form</h1>
        <form action="FileUpload" enctype="multipart/form-data" method="post">
            Enter File Name <input type="text" name="file_name"><br> Select
            <input type="file" name="file2" /><br>
            <input type="submit" value="upload" />
        </form>
        <%
                   String file_name=(String)request.getParameter("filename");
                   if(file_name!=null){
                       out.println(file_name+" File uploaded successfuly");
                     %><br>
            <img src="http://localhost:8080/uploaded_files/<%=file_name%>">
            <%	   
                       
                   }
                   %>
    </center>
</body>

</html>