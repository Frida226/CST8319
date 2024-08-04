<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Wishlist</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        /* 为 admin.jsp 页面定义的特定样式 */
        nav {
            background-color: #f0f8ff ; /* 浅蓝色背景 */
            color: #333; /* 深色文字 */
        }
        nav a {
            color: #333; /* 深色文字 */
            background-color: #f0f8ff; /* 浅蓝色背景 */
            padding: 10px;
            border-radius: 4px;
        }
        nav a:hover {
            background-color: #e0e0e0; /* 鼠标悬停时的颜色 */
        }
    </style>
</head>
<body>
    <h1>Your Wishlist</h1>
    <nav>
        <ul>
            <li><a href="manageInventory.jsp">Manage Inventory</a></li>
            <li><a href="processOrders.jsp">Process Orders</a></li>
            <li><a href="viewReports.jsp">View Reports</a></li>
            <li><a href="handleReturns.jsp">Handle Returns</a></li>
            <li><a href="addAdmin.jsp">Add New Admin</a></li>
        </ul>
    </nav>
    <div class="wishlist">
        <h2>Wishlist Items</h2>
        <c:if test="${empty wishlist}">
            <p>Your wishlist is empty.</p>
        </c:if>
        <c:if test="${not empty wishlist}">
            <ul>
                <c:forEach var="product" items="${wishlist}">
                    <li>
                        <img src="${product.image}" alt="${product.name}" width="100">
                        <div>
                            <h3>${product.name}</h3>
                            <p>Price: ${product.price}</p>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
    </div>

    <div class="add-to-wishlist">
        <h2>Add a Product to Your Wishlist</h2>
        <form action="wishlist" method="post">
            <label for="product_id">Product ID:</label>
            <input type="text" id="product_id" name="product_id" required>
            <br>
            <label for="product_name">Product Name:</label>
            <input type="text" id="product_name" name="product_name" required>
            <br>
            <label for="product_price">Product Price:</label>
            <input type="text" id="product_price" name="product_price" required>
            <br>
            <label for="product_image">Product Image URL:</label>
            <input type="text" id="product_image" name="product_image" required>
            <br>
            <button type="submit">Add to Wishlist</button>
        </form>
    </div>
</body>
</html>
