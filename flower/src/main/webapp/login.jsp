<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">

<title>Sign In For Flower Shop</title>
<script src="javascript/script.js"></script>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<input type="hidden" id="status" value="<%=request.getAttribute("loginStatus")%>">
	<div class="main">

		<section class="sign-in">
			<div class="container">
				<div class="signin-content">
					<div class="signin-image">
						<figure>
							<img src="images/signin-image.jpg" alt="sign in image">
						</figure>
						<a href="registration.jsp" class="signup-image-link">Create an account</a>
					</div>
					<div class="signin-form">
						<h2 class="form-title">Sign in</h2>
						<form method="post" action="login" class="register-form"
							id="login-form">
							<div class="form-group">
								<label for="username"></label> 
								<input type="text" name="username" id="username"
									placeholder="Your Name" />
							</div>
							<div class="form-group">
								<label for="password"></label> 
								<input type="password" name="password" id="password"
									placeholder="Password" />
							</div>
							<div class="form-group">
								<input type="checkbox" name="remember-me" id="remember-me"
									class="agree-term" /> 
									<label for="remember-me" class="label-agree-term">
									<span><span></span></span>Remember me</label>
							</div>
							<div class="form-group form-button">
								<input type="submit" name="signin" id="signin"
									class="form-submit" value="Log in" />
							</div>
						</form>					
					</div>
				</div>
			</div>
		</section>
	</div>

</body>
</html>