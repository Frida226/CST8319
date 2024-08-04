<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.flowerorder.model.Wishlist"%>
<%@ page import="com.flowerorder.model.Products"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Wishlist</title>
    <link rel="stylesheet" href="css/style.css"> <!-- Ensure this path is correct -->
</head>
<body>

<jsp:include page="header.jsp" />

<section class="wishlist">
    <h1>Your Wishlist</h1>
    <div class="product-container">
        <c:forEach var="item" items="${wishlistItems}">
            <div class="product">
                <img src="uploaded_img/${item.productImage}" alt="${item.productName}" class="product-image">
                <div class="product-details">
                    <h3>${item.productName}</h3>
                    <p>$${item.productPrice}</p>
                </div>
                <form action="removeFromWishlist" method="post">
                    <input type="hidden" name="product_id" value="${item.productId}">
                    <button type="submit" class="btn-remove">Remove</button>
                </form>
            </div>
        </c:forEach>
    </div>
</section>

<jsp:include page="footer.jsp" />

</body>
</html>
