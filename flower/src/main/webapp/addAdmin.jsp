<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register New Admin</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        /* Set bg color for whole page*/
        body {
            background-color: #f4f7f6;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        /* Modify main container style */
        .main {
            width: 100%;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        /* Modify form container */
        .container {
            width: 80%;
            max-width: 1200px;
            margin: 0 auto;
            background: #fff;
            padding: 30px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        /* Form part */
        .signup-content {
            display: flex;
            justify-content: space-between;
/*             align-items: center;
            width: 100%; */
         	padding: 20px;
    		background-color: #f7f7f7;
    		border-radius: 10px;
        }

        .signup-form {
            width: 50%;
        }

        .signup-form h2 {
            font-size: 28px;
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }

        .form-group {
            margin-bottom: 15px;
            position: relative;
        }

        .form-group label {
            position: absolute;
            left: 10px;
            top: -10px;
            font-size: 12px;
            color: #666;
            background: #fff;
            padding: 0 5px;
        }

        .form-group input {
            width: 100%;
            padding: 10px 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        .form-group input:focus {
            border-color: #66afe9;
            outline: none;
        }

        .form-button {
            text-align: center;
        }

        .form-submit {
            background: #28a745;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .form-submit:hover {
            background: #218838;
        }

        .signup-image {
            width: 45%;
            text-align: center;
        }

        .signup-image img {
            max-width: 100%;
            border-radius: 8px;
        }

        .signup-image-link {
            display: inline-block;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
        }

        .signup-image-link:hover {
            color: #0056b3;
        }
    </style>
    
    
</head>
<body>
<div class="main">
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title">Register New Admin</h2>
                    <form method="post" action="addAdmin" class="register-form" id="register-form" onsubmit="return validateForm();">
                        <div class="form-group">
                            <label for="username"></label>
                            <input type="text" name="username" id="username" placeholder="Enter Username" required/>
                        </div>
                        <div class="form-group">
                            <label for="email"></label>
                            <input type="email" name="email" id="email" placeholder="example@gmail.com" required/>
                        </div>
                        <div class="form-group">
                            <label for="pass"></label>
                            <input type="password" name="pass" id="pass" placeholder="Password" required/>
                        </div>
            			<div class="form-group">
                            <label for="re-pass"></label>
                            <input type="password" name="re_pass" id="re_pass"
                                placeholder="Repeat your password" required/>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup" class="form-submit" value="Register Admin" />
                        </div>
                    </form>
                </div>
                <div class="signup-image">
                    <figure>
                        <img src="images/signup-image.jpg" alt="sign up image">
                    </figure>
                    <a href="login.jsp" class="signup-image-link">I am already a admin</a>
                </div>
            </div>
        </div>
    </section>
</div>

<script>
    function validateForm() {
        var username = document.getElementById("username").value;
        var email = document.getElementById("email").value;
        var password = document.getElementById("pass").value;
        var rePassword = document.getElementById("re_pass").value;

        if (username.trim() === "") {
            alert("Please enter a username.");
            return false;
        }

        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            alert("Please enter a valid email address.");
            return false;
        }

        if (password.length < 8) {
            alert("Password must be at least 8 characters long.");
            return false;
        }
        
        if (password != rePassword) {
            alert("Passwords do not match.");
            return false;
        }

        alert("Account Created Successfully!");
        return true; // Allow form submission if all checks pass
    }
</script>

</body>
</html>
