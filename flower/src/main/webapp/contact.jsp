<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Contact Us</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>

<jsp:include page="header.jsp" />

<div class="main-contact">
    <section class="contact1">
        <div class="container">
            <div class="contact-content">
                <div class="contact-form">
                    <h2 class="form-title">Contact Us</h2>
                    <form method="post" action="contact" class="contact-form">
                        <div class="form-group">
                            <label for="name"></label> 
                            <input type="text" name="name" id="name" placeholder="Your Name" required/>
                        </div>
                        <div class="form-group">
                            <label for="email"></label> 
                            <input type="email" name="email" id="email" placeholder="Your Email" required/>
                        </div>
                        <div class="form-group">
                            <label for="subject"></label> 
                            <input type="text" name="subject" id="subject" placeholder="Subject" required/>
                        </div>
                        <div class="form-group">
                            <label for="message"></label>
                            <textarea name="message" id="message" rows="5" placeholder="Your Message" required></textarea>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="send" id="send" class="form-submit" value="Send Message" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>

