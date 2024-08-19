<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Floral Care Tips</title>
<link rel="stylesheet" href="css/style.css"> <!-- Ensure this path is correct -->
</head>
<body>

<jsp:include page="header.jsp" />

<div class="main-care-tips">
    <section class="care-tips">
        <div class="container">
            <div class="care-content">
                <div class="care-info">
                    <h2 class="tips-title">Floral Care Tips</h2>
                    <div class="tip-detail">
                        <h3>Basic Flower Care</h3>
                        <p>Cut the stems at a 45-degree angle under running water.</p>
                        <p>Use a clean vase and fresh water mixed with flower food.</p>
                        <p>Remove leaves below the water line to prevent bacterial growth.</p>
                    </div>
                    <div class="tip-detail">
                        <h3>Specific Flower Care Guides</h3>
                        <p><strong>Roses:</strong> Keep roses in a cool place and avoid direct sunlight. Change the water every two days.</p>
                        <p><strong>Lilies:</strong> Remove anthers to prevent pollen staining and keep the water level consistent.</p>
                        <p><strong>Orchids:</strong> Water sparingly, allowing the potting mix to dry out between watering.</p>
                    </div>
                    <div class="tip-detail">
                        <h3>Seasonal Flower Care</h3>
                        <p><strong>Spring:</strong> Focus on early bloomers like tulips and daffodils.</p>
                        <p><strong>Summer:</strong> Keep summer flowers hydrated and in indirect sunlight.</p>
                        <p><strong>Fall and Winter:</strong> Use warmer water for tropical flowers and reduce watering frequency.</p>
                    </div>
                    <div class="tip-detail1">
    					<h3>Image Tutorials</h3>
   						<img src="http://localhost:8080/flower/images/guides.jpg" alt="Flower Care Video Guides" >
       				</div>
                </div>
            </div>
        </div>
    </section>
</div>

<jsp:include page="footer.jsp" />

</body>
</html>
