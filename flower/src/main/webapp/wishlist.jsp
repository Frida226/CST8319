<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.flowerorder.model.Wishlist"%>
<%@ page import="com.flowerorder.model.Products"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Wishlist</title>
	<link rel="stylesheet" href="css/style.css">
</head>
	
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f9f9f9;
    }

    .wishlist {
        padding: 20px;
    }

    .wishlist h1 {
        text-align: center;
        color: #333;
    }

    .product-container {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        gap: 20px;
        padding: 20px 0;
    }

    .product {
        background-color: #fff;
        border: 1px solid #ddd;
        border-radius: 5px;
        width: 300px;
        padding: 15px;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        text-align: center;
    }

    .product-image {
        width: 100%;
    	height: 300px;
   		object-fit: cover;
    }

    .price {
        font-size: 18px;
        color: #666;
        margin-bottom: 10px;
    }

    .name {
        font-size: 20px;
        color: #333;
        margin-bottom: 10px;
    }

    .stock {
        font-size: 16px;
        color: #888;
        margin-bottom: 10px;
    }

    .btn-remove {
        background-color: #e74c3c;
        color: #fff;
        padding: 10px 20px;
        border: none;
        border-radius: 3px;
        cursor: pointer;
    }

    .btn-remove:hover {
        background-color: #c0392b;
    }

    .btn {
        display: inline-block;
        padding: 10px 20px;
        background-color: #3498db;
        color: #fff;
        text-align: center;
        text-decoration: none;
        border-radius: 3px;
        margin-top: 20px;
        display: block;
        width: 200px;
        margin: 20px auto;
    }

    .btn:hover {
        background-color: #2980b9;
    }
</style>

<body>

	<jsp:include page="header.jsp" />

	<section class="wishlist">
		<h1>Your Wishlist</h1>
		<div class="product-container">
			<c:forEach var="item" items="${wishlistItems}">
				<div class="product">
					<img src="${item.product_image}" alt="${item.product_name}"
						class="product-image">
					<div class="price">$${item.price}/-</div>
					<div class="name">${item.product_name}</div>
					<div class="stock">In Stock: ${item.stock}</div>
					<!-- Assuming stock is passed to JSP -->
					<form action="wishlist" method="post">
						<input type="hidden" name="action" value="removeFromWishlist">
						<input type="hidden" name="product_id" value="${item.product_id}">
						<button type="submit" class="btn-remove">Remove</button>
					</form>
				</div>
			</c:forEach>
		</div>
		<a href="index.jsp" class="btn">Return to Home</a>
	</section>

	<jsp:include page="footer.jsp" />

</body>
</html>
