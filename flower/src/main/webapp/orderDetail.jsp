<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }
    .confirmation-container {
        background-color: #fff;
        margin: 50px auto;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        max-width: 800px;
        border-radius: 8px;
    }
    h1, h3 {
        color: #333;
    }
    p {
        color: #555;
    }
    .btn {
        display: inline-block;
        padding: 10px 20px;
        color: #fff;
        background-color: #007bff;
        border: none;
        border-radius: 5px;
        text-decoration: none;
        margin-top: 20px;
    }
    .btn-secondary {
        background-color: #6c757d;
    }
    .btn:hover {
        background-color: #0056b3;
    }
    .btn-secondary:hover {
        background-color: #5a6268;
    }
</style>
</head>
<body>
<jsp:include page="header.jsp" />
    <div class="confirmation-container">
        <h1>Order Confirmation</h1>
        <p>Thank you for your purchase! Your o	rder has been placed successfully.</p>

        <h3>Order Details</h3>
<%--         <p>Order ID: <strong><%= request.getAttribute("order_id") %></strong></p> 	<!-- not orderId but order_id?? -->
        <p>Total Price: <strong>$<%= request.getAttribute("totalPrice") %></strong></p>
        <p>Shipping Address: <strong><%= request.getAttribute("shippingAddress") %></strong></p>
        <p>Payment Method: <strong><%= request.getAttribute("paymentMethod") %></strong></p> --%>
        
        <p>Order ID: <strong>${order_id}</strong></p>
        <p>Total Price: <strong>$${totalPrice}</strong></p>
        <p>Shipping Address: <strong>${shippingAddress}</strong></p>
        <p>Payment Method: <strong>${paymentMethod}</strong></p>
        
		<!-- add more order Items details for checking -->
        <h3>Order Items</h3>
        <ul>
            <c:forEach var="item" items="${orderItems}">
                <li>
                    <strong>${item.product_name}</strong> - Quantity: ${item.quantity}, Price: $${item.price}
                </li>
            </c:forEach>
        </ul>

        <p>We will send you an email confirmation shortly with the order details.</p>

        <a href="cart.jsp" class="btn btn-primary">Back to Cart</a>
        <a href="index.jsp" class="btn btn-secondary">Back to Home</a>

		<form action="order" method="get" style="display: inline;">
		    <input type="hidden" name="action" value="history">
		    <input type="submit" value="Check Order History" class="btn btn-info">
		</form>
        <form action="SendOrderEmailServlet" method="post">
            <input type="hidden" name="order_id" value="${order_id}">
            <input type="submit" class="btn btn-success" value="Checkout and Send Email">
        </form>
        
    </div>
<jsp:include page="footer.jsp" />
</body>
</html>
