<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
     <link rel="stylesheet" href="css/style.css"> 
         <style>
        /* 为 admin.jsp 页面定义的特定样式 */
        nav {
            background-color: #f0f8ff ; /* 浅蓝色背景 */
            color: #333; /* 深色文字 */
            padding: 10px； 
            border-radius: 8px
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
    <h1>Admin Dashboard</h1>
    <nav>
        <ul>
            <li><a href="manageInventory.jsp">Manage Inventory</a></li>
            <li><a href="processOrders.jsp">Process Orders</a></li>
            <li><a href="viewReports.jsp">View Reports</a></li>
            <li><a href="handleReturns.jsp">Handle Returns</a></li>
   			<li><a href="addAdmin.jsp">Add New Admin</a></li>
        </ul>
    </nav>
</body>
</html>
