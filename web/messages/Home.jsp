<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SuccessfulLogin</title>
    </head>
    <body>
        
        <center>
            <h1><%= request.getAttribute("Home") %></h1>
        </center>

      
        <form action="logout" method="POST">
            <input type="submit" value="logout">
        </form>
    </body>
</html>
