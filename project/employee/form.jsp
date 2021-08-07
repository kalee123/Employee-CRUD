<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Employee Demo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="form.css">
    </head>
    <body>
        <div class="container">
            <div class="p-5 text-center bg-light" id="title">
                <h1 class="mb-3"><%= request.getParameter("type") %> employee</h1>
            </div>
            <div class="form-container">
                <form action=<%= request.getParameter("type") %>  method="POST" class="contact-form row">
                    <h3>Basic details</h3>
                    <div class="form-group">
                        <label>* id</label>
                        <input type="text" id="id" class="form-control" name="id" readonly/>
                    </div>
                    <div class="form-group">
                        <label>* first name</label>
                        <input type="text" id="firstname" class="form-control" name="firstname" required />
                    </div>
                    <div class="form-group">
                        <label>* last name</label>
                        <input type="text" id="lastname" class="form-control" name="lastname" required/>
                    </div>
                    <div class="form-group">
                        <label>* gender</label><br>
                        <input type="radio" name="gender" value="male"/><span>male</span>
                        <input type="radio" name="gender" value="female"/><span>female</span>
                    </div>
                    <div class="form-group">
                        <label>* DOB</label>
                        <input type="date" id="dob" class="form-control" name="dob" required/>
                    </div>
                    <div class="form-group">
                        <label>* email</label>
                        <input type="email" id="email" class="form-control" name="email" required/>
                    </div>
                    <div class="form-group">
                        <label>* role</label>
                        <input type="text" id="role" class="form-control" name="role" required/>
                    </div>
        
                    <h3>Address</h3>
                    <div class="form-group">
                        <label>* door no</label>
                        <input type="number" id="door" class="form-control" name="doorNo" required/>
                    </div>
                    <div class="form-group">
                        <label>* Street</label>
                        <input type="text" id="street" class="form-control" name="street" required/>
                    </div>
                    <div class="form-group">
                        <label>* city</label>
                        <input type="text" id="city" class="form-control" name="city" required/>
                    </div>         
                    <div class="form-group button">
                        <button type="submit" class="btn btn-primary"><%= request.getParameter("type") %> </button>
                    </div>
                    
                </form>
            </div>
        </div>
    </body>
    <script src="script.js"></script>
</html>


