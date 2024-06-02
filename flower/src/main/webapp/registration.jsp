<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">

<title>Sign Up For Flower Shop</title>
<script src="javascript/script.js"></script>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

<input type="hidden" id="status" value="<%=request.getAttribute("status")%>">
	<div class="main">

		<section class="signup">
			<div class="container">
				<div class="signup-content">
					<div class="signup-form">
						<h2 class="form-title">Sign up</h2>
					
						<form method="post" action="register" class="register-form"
							id="register-form">
							<div class="form-group">
								<label for="name"></label> 
								<input type="text" name="name" id="name" placeholder="Enter Your Name" />
							</div>
							<div class="form-group">
								<label for="email"></label> 
								<input type="email" name="email" id="email" placeholder="Enter Your Email" />
							</div>
							<div class="form-group">
								<label for="pass"></label> 
								<input type="password" name="pass" id="pass" placeholder="Password" />
							</div>
							<div class="form-group">
								<label for="re-pass"></label>
								<input type="password" name="re_pass" id="re_pass"
									placeholder="Repeat your password" />
							</div>
							<div class="form-group">
								<label for="contact"></label>
								<input type="text" name="contact" id="contact"
									placeholder="Phone number" />
							</div>
							<div class="form-group">
								<input type="checkbox" name="agree-term" id="agree-term"
									class="agree-term" /> 
								<label for="agree-term" class="label-agree-term">
									<span><span></span></span>
									I agree all statements in 
									<a href="#" class="term-service">Terms of service</a>
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
	
</body>
</html>