<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Process Orders</title>
<style>
    /* Table styling */
    .order-table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 30px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Adding a subtle shadow for depth */
    }

    .order-table th, .order-table td {
        border: 1px solid #ddd;
        padding: 12px; /* Increased padding for better spacing */
        font-size: 0.95em; /* Slightly reduced font size for a cleaner look */
    }

    .order-table th {
        background-color: #4CAF50; /* Changed to a more vibrant color */
        color: white; /* White text on colored background */
        text-align: left;
        font-weight: 600; /* Bold text */
    }

    .order-table td {
        background-color: #f9f9f9; /* Light background for table rows */
    }

    /* Row hover effect */
    .order-table tr:hover td {
        background-color: #f1f1f1; /* Slightly darker background on hover */
    }

    /* User header styling */
    .user-header {
        margin-top: 30px;
        font-size: 1.4em; /* Increased font size */
        font-weight: bold;
        color: #333; /* Darker color for emphasis */
        border-bottom: 2px solid #4CAF50; /* Adding a bottom border */
        padding-bottom: 10px;
        margin-bottom: 10px;
    }

    /* Button styling */
    .btn-update, .btn-delete, .btn-view-details {
        background-color: #4CAF50; /* Green background */
        color: white;
        border: none;
        padding: 8px 16px; /* Increased padding for better clickability */
        margin-right: 5px;
        cursor: pointer;
        font-size: 0.9em;
        border-radius: 4px; /* Rounded corners */
        transition: background-color 0.3s ease; /* Smooth transition for hover effects */
    }

    .btn-update:hover {
        background-color: #45a049; /* Darker green on hover */
    }

    .btn-delete {
        background-color: #f44336; /* Red background for delete */
    }

    .btn-delete:hover {
        background-color: #e53935; /* Darker red on hover */
    }

    .btn-view-details {
        background-color: #2196F3; /* Blue background for view details */
    }

    .btn-view-details:hover {
        background-color: #1976D2; /* Darker blue on hover */
    }

    /* Form alignment inside table cells */
    .order-table td form {
        display: inline-block;
        margin: 0;
    }

    /* Select and input styling */
    select, input[type="text"] {
        padding: 6px;
        font-size: 0.95em;
        border: 1px solid #ccc;
        border-radius: 4px;
        width: 100%;
        box-sizing: border-box;
    }

    select {
        background-color: white;
    }

    input[type="text"]:focus, select:focus {
        outline: none;
        border-color: #4CAF50; /* Green border on focus */
    }
    
    .return-btn {
        display: block;
        margin: 40px auto 20px; /* locate at bottom */
        background-color: #007BFF; /* blue BG */
        color: white; 
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        text-align: center;
        text-decoration: none;
        cursor: pointer;
        transition: background-color 0.3s ease;
        width: auto; /* Set width to auto */
        display: inline-block; /* Make buttons inline */            
    }

    .return-btn:hover {
        background-color: #0056b3;
    }
	.button-container {
   			text-align: center;
    		margin-bottom: 120px;
   	}
</style>

</head>
<body>

<jsp:include page="header.jsp" />

<h1>Admin Dashboard - Process Orders</h1>

<c:forEach var="user" items="${users}">
    <div class="user-section">
        <div class="user-header">User: ${user.username} (ID: ${user.id})</div>

        <table class="order-table">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Total Price</th>
                    <th>Status</th>
                    <th>Shipping Address</th>
                    <th>Payment Method</th>
                    <th>Order Date</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${userOrdersMap[user.id]}">
                    <tr>
                        <td>${order.order_id}</td>
                        <td>$${order.total_price}</td>
                        <form action="admin" method="post" style="display:inline;">
                            <td>
                                <select name="order_status_${order.order_id}">
                                    <option value="Pending" ${order.order_status == 'Pending' ? 'selected' : ''}>Pending</option>
                                    <option value="Shipped" ${order.order_status == 'Shipped' ? 'selected' : ''}>Shipped</option>
                                    <option value="Delivered" ${order.order_status == 'Delivered' ? 'selected' : ''}>Delivered</option>
                                </select>
                            </td>
                            <td><input type="text" name="shipping_address_${order.order_id}" value="${order.shipping_address}" /></td>
                            <td>
                                <select name="payment_method_${order.order_id}">
                                    <option value="Credit Card" ${order.payment_method == 'Credit Card' ? 'selected' : ''}>Credit Card</option>
                                    <option value="PayPal" ${order.payment_method == 'PayPal' ? 'selected' : ''}>PayPal</option>
                                    <option value="Bank Transfer" ${order.payment_method == 'Bank Transfer' ? 'selected' : ''}>Bank Transfer</option>
                                </select>
                            </td>
                            <td>${order.created_at}</td>
                            <td>
                                <input type="hidden" name="action" value="updateOrder">
                                <input type="hidden" name="order_id" value="${order.order_id}">
                                <input type="submit" value="Update" class="btn-update">
                            </td>
                        </form>
                        <td>
                            <form action="admin" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="deleteOrder">
                                <input type="hidden" name="order_id" value="${order.order_id}">
                                <input type="submit" value="Delete" class="btn-delete">
                            </form>
                            <form action="order" method="get" style="display:inline;">
                                <input type="hidden" name="action" value="details">
                                <input type="hidden" name="order_id" value="${order.order_id}">
                                <input type="submit" value="View Details" class="btn-view-details">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:forEach>


<jsp:include page="footer.jsp" />
<div class="button-container">
	<a href="admin.jsp" class="return-btn">Return to Admin Dashboard</a>
</div>
</body>
</html>
