<%
	if(session.getAttribute("name")==null){
		response.sendRedirect("login.jsp");
	} else if (request.getAttribute("products") == null) {
        response.sendRedirect("home");
    }
%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.flowerorder.model.Products" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<jsp:include page="header.jsp" />

<section class="home">
    <div class="content">
        <h3>Seasonal Specials</h3>
        <p>Experience the Artistry of Floral Design with Our Handpicked Bouquets and Seasonal Specials.</p>
        <a href="special.jsp" class="btn">Discover More</a>
    </div>
</section>

<section class="products">
    <h1 class="title">Latest Products</h1>
    <div class="box-container">
        <%
            List<Products> products = (List<Products>) request.getAttribute("products");
            if (products != null && !products.isEmpty()) {
                for (Products product : products) {
        %>
		<form action="wishlist" method="POST" class="box">
            <a href="view_page.jsp?pid=<%= product.getProduct_id() %>" class="fas fa-eye"></a>
            <div class="price">$<%= product.getPrice() %>/-</div>
            <img src="uploaded_img/<%= product.getImage_url() %>" alt="" class="image">
            <div class="name"><%= product.getName() %></div>
            <input type="number" name="product_quantity" value="1" min="0" class="qty">
            <input type="hidden" name="product_id" value="<%= product.getProduct_id() %>">
            <input type="hidden" name="product_name" value="<%= product.getName() %>">
            <input type="hidden" name="product_price" value="<%= product.getPrice() %>">
            <input type="hidden" name="product_image" value="<%= product.getImage_url() %>">
            
<!--             <input type="submit" value="Add to Wishlist" class="option-btn">
            <input type="submit" value="Add to Cart" class="btn"> -->
            
            <input type="submit" value="Add to Wishlist" class="option-btn addToWishlist">
            <input type="submit" value="Add to Cart" class="btn addToCart">
        </form>
        <%
                }
            } else {
        %>
        <p class="empty">No products added yet!</p>
        <%
            }
        %>
    </div>
    <div class="more-btn">
        <a href="shop.jsp" class="option-btn">Load More</a>
    </div>
</section>

<section class="home-contact">
    <div class="content">
        <h3>Have Any Questions?</h3>
        <p>For any inquiries or additional information, please do not hesitate to contact us. Our team is ready to assist you with any questions you may have.</p>
        <a href="contact.jsp" class="btn">Contact Us</a>
    </div>
</section>

<jsp:include page="footer.jsp" />

<!--added script to trigger add addToWishlist & addToCart actions  -->
<script>
    document.addEventListener('click', function(event) {
        if (event.target.classList.contains('addToWishlist')) {
            event.target.closest('form').action = 'wishlist';
        } else if (event.target.classList.contains('addToCart')) {
            event.target.closest('form').action = 'Cart';
        }
    });
</script>

</body>
</html>
