<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register New Admin</title>
    <link rel="stylesheet" href="css/style.css">
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
                        <img src="images/admin-signup.jpg" alt="sign up image">
                    </figure>
                    <a href="login.jsp" class="signup-image-link">I am already a member</a>
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

        return true; // Allow form submission if all checks pass
    }
</script>

</body>
</html>
