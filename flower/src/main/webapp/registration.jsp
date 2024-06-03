<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">

<title>Sign Up For Flower Shop</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

<input type="hidden" id="status" value="<%=request.getAttribute("registerStatus")%>">
	<div class="main">

		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>
					
						<form method="post" action="register" class="register-form"
							id="register-form" onsubmit="return validateForm();">
							<div class="form-group">
								<label for="name"></label> 
								<input type="text" name="name" id="name" placeholder="Enter Your Name" required/>
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
							<div class="form-group">
								<label for="contact"></label>
								<input type="tel" name="contact" id="contact"
									placeholder="xxx-xxx-xxxx" required/>
							</div>
							<div class="form-group">
								<input type="checkbox" name="agree-term" id="agree-term"
									class="agree-term" required/> 
								<label for="agree-term" class="label-agree-term">
									<span><span></span></span>
									I agree all statements in Terms of service
								</label>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signup" id="signup"
									class="form-submit" value="Register" />
							</div>
						</form>
					</div>
					<div class="signup-image">
						<figure>
							<img src="images/signup-image.jpg" alt="sign up image">
						</figure>
						<a href="login.jsp" class="signup-image-link">I am already member</a>
					</div>
				</div>
			</div>
		</section>
	</div>	
	
<script>
    function validateForm() {
        var name = document.getElementById("name").value;
        var email = document.getElementById("email").value;
        var password = document.getElementById("pass").value;
        var rePassword = document.getElementById("re_pass").value;
        var contact = document.getElementById("contact").value;

        if (name.trim() == "") {
            alert("Please enter your name.");
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

        var contactPattern = /^\d{3}\d{3}\d{4}$/;
        if (!contactPattern.test(contact)) {
            alert("Please enter a valid phone number in the format xxx-xxx-xxxx.");
            return false;
        }
        
        alert("Account Created Successfully!");
        return true;
    }
</script>
	
</body>
</html>