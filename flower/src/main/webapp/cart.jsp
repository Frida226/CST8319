
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.flowerorder.model.Cart"%>
<%@ page import="com.flowerorder.model.Products"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart</title>
    <link rel="stylesheet" href="css/style.css"> <!-- Ensure this path is correct -->
</head>

<style>
    .cart {
        width: 80%;
        padding: 20px;
        margin: 0 auto; /* Centers the section horizontally */
        margin-bottom: 90px;
    }

    .product-container {
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
    }

    .box {
        box-sizing: border-box;
        border: 1px solid #ccc;
        padding: 10px;
        text-align: center;
    }

    .product-image {
        width: 200px;
        height: 200px;       
    }

    .btn-remove {
        background-color: #f44336;
        color: white;
        border: none;
        padding: 10px;
        cursor: pointer;
    }

    .btn-remove:hover {
        background-color: #d32f2f;
    }

    /* Styles for the form */
    form {
        margin-top: 10px;
        padding: 5px;
        background-color: #f9f9f9;
        border: 1px solid #ddd;
        border-radius: 8px;
    }

    label {
        display: block;
        margin-bottom: 8px;
        font-weight: bold;
    }

    input[type="text"], select {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
    }

    input[type="submit"] {
        background-color: #4CAF50; /* Soft green */
        color: white;
        padding: 8px 5px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: #45a049;
    }
    
	.button-container {
   		text-align: center;
	}
    /* Styles for the Return to Home button */
    .btn {
        display: inline-block;
        margin-top: 20px;
        padding: 12px 20px;
        background-color: #2196F3; /* Blue */
        color: white;
        text-align: center;
        text-decoration: none;
        border-radius: 4px;
    }

    .btn:hover {
        background-color: #1976D2;
    }
</style>

<body>

<jsp:include page="header.jsp" />

<section class="cart">
    <h1 style="text-align: left;">Your Cart</h1>
    <div class="product-container">
        <c:forEach var="item" items="${cartItems}">
		    <div class="box">
		        <img src="${item.product_image}" alt="${item.product_name}" class="product-image">
		        <div class="name">${item.product_name}</div>
		        <div class="price">$${item.price}</div>
		        <div class="quantity">
		            <form action="Cart" method="post">
		                <input type="hidden" name="action" value="updateCart">
		                <input type="hidden" name="product_id" value="${item.product_id}">
		                <input type="number" name="quantity" value="${item.quantity}" min="1" class="qty" style="margin-left: 85px;">
		                <input type="submit" value="Update Quantity" class="btn-update">
		            </form>
		        </div>
                <form action="Cart" method="post">
                    <input type="hidden" name="action" value="removeFromCart">
                    <input type="hidden" name="product_id" value="${item.product_id}">
                    <button type="submit" class="btn-remove">Remove</button>
                </form>
            </div>
        </c:forEach>
    </div>
    
    <!-- New Form for Creating an Order -->
	<form action="order" method="post">
	    <input type="hidden" name="action" value="create">
	    
	    <!-- Shipping Address -->
	    <label for="shippingAddress">Shipping Address:</label>
	    <input type="text" id="shippingAddress" name="shippingAddress" required>
	    
	    <!-- Payment Method -->
	    <label for="paymentMethod">Payment Method:</label>
	    <select id="paymentMethod" name="paymentMethod" required>
	        <option value="creditCard">Credit Card</option>
	        <option value="paypal">PayPal</option>
	        <option value="bankTransfer">Bank Transfer</option>
	    </select>
	    
	    <!-- Place Order Button -->
	    <input type="submit" value="Place Order">
	</form>
    <div class="button-container">
    	<a href="index.jsp" class="btn">Return to Home</a>
	</div>

</section>


<jsp:include page="footer.jsp" />

</body>
</html>
