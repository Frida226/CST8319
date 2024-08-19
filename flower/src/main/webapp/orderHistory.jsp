<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.flowerorder.model.Orders" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .history-container {
            background-color: #fff;
            margin: 50px auto;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            border-radius: 8px;
        }
        h1 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table th, table td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        table th {
            background-color: #f8f9fa;
        }
        table td {
            background-color: #fff;
        }
        table tr:hover {
            background-color: #f1f1f1;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 14px;
            color: #ffffff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            text-align: center;
            text-decoration: none;
            margin-top: 20px;
        }
        .btn:hover {
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
    <h1>Your Orders History</h1>
    <table border="1">
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
            <%  /* back to OrderDaoImpl->List<Orders> getOrdersByUserId to get correct structure*/
            List<Orders> orders = (List<Orders>) request.getAttribute("orders");
            if (orders != null) {
            	out.println("Number of orders in JSP: " + orders.size()); /* debugging */
                for (Orders order : orders) {
            %>
            <tr>
                <td><%= order.getOrder_id() %></td>
                <td><%= order.getTotal_price() %></td>
                <td><%= order.getOrder_status() %></td>
                <td><%= order.getShipping_address() %></td>
                <td><%= order.getPayment_method() %></td>
                <td><%= order.getCreated_at() %></td>
                <td>
					<form action="order" method="get">
						<!-- original is <form action="orderDetails.jsp"... -->
						<input type="hidden" name="action" value="details"> <input
							type="hidden" name="order_id" value="<%=order.getOrder_id()%>">
						<input type="submit" value="View Details" class="btn btn-primary">
					</form>
					<form action="order" method="post"
						onsubmit="return confirm('Are you sure you want to delete this order?');">
						<input type="hidden" name="action" value="delete"> <input
							type="hidden" name="order_id" value="<%=order.getOrder_id()%>">
						<input type="submit" value="Delete" class="btn btn-danger">
					</form>
					
<%--                     <form action="repeatOrderServlet" method="post">
                        <input type="hidden" name="order_id" value="<%= order.getOrder_id() %>">
                        <input type="submit" value="Repeat Order" class="btn btn-secondary">
                    </form>
                    <form action="downloadInvoiceServlet" method="get">
                        <input type="hidden" name="order_id" value="<%= order.getOrder_id() %>">
                        <input type="submit" value="Download Invoice" class="btn btn-info">
                    </form>
                    <form action="leaveFeedback.jsp" method="get">
                        <input type="hidden" name="order_id" value="<%= order.getOrder_id() %>">
                        <input type="submit" value="Leave Feedback" class="btn btn-success">
                    </form> --%>
                    
                </td>               
            </tr>
            <% 
                }
            } else { 
            	out.println("Orders attribute is null."); /* debugging */
            %>
            <tr>
                <td colspan="7">No orders found.</td> <!-- Added a message if there are no orders -->
            </tr>
            <% 
            }
            %>
        </tbody>
    </table>
    <div class="button-container">
    	<a href="index.jsp" class="btn btn-secondary">Back to Home</a>
	</div>
<jsp:include page="footer.jsp" />
</body>
</html>
