<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" href="styles.css">
</head>
<link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@500&family=Roboto&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #fef9f4;
            color: #333;
            margin: 0;
            padding: 0;
        }

        .dashboard-header {
            background-color: #6AB04C;
            color: white;
            padding: 1.5rem;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            font-family: 'Playfair Display', serif;
        }

        .dashboard-header h1 {
            margin: 0;
            font-size: 2.5rem;
        }

        .dashboard-nav {
            background-color: #2d3436;
            padding: 1rem;
        }

        .dashboard-nav ul {
            list-style: none;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: space-around;
        }

        .dashboard-nav li {
            margin: 0;
        }

        .dashboard-nav a {
            color: white;
            text-decoration: none;
            padding: 0.75rem 1.5rem;
            display: block;
            border-radius: 5px;
            transition: background-color 0.3s;
            font-family: 'Roboto', sans-serif;
        }

        .dashboard-nav a:hover {
            background-color: #636e72;
        }

        .dashboard-content {
            padding: 2rem;
            text-align: center;
            background-color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin: 2rem;
            border-radius: 15px;
            background-image: url('https://example.com/flower-background.png'); /* 用实际的花店背景图替换 */
            background-size: cover;
        }

        .dashboard-content p {
            font-size: 1.2rem;
            color: #6AB04C;
        }

        .dashboard-footer {
            text-align: center;
            padding: 1rem;
            background-color: #6AB04C;
            color: white;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        .btn-return {
            color: white;
            background-color: #e17055;
            padding: 0.75rem 1.5rem;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .btn-return:hover {
            background-color: #d35400;
        }
    </style>
<body>
    <header class="dashboard-header">
        <h1>Admin Dashboard</h1>
    </header>
    
    <nav class="dashboard-nav">
        <ul>
            <li><a href="manageInventory.jsp">Manage Inventory</a></li>
            <li><a href="admin?action=processOrders">Process Orders</a></li>
            <li><a href="viewReports.jsp">View Reports</a></li>
            <li><a href="handleReturns.jsp">Handle Returns</a></li>
            <li><a href="addAdmin.jsp">Add New Admin</a></li>
        </ul>
    </nav>
    
    <div class="dashboard-content">
        <p>Welcome to the Admin Dashboard. Please select an option from the menu to get started.</p>
        <!-- You can add more content or dashboard widgets here -->
    </div>
    
    <footer class="dashboard-footer">
        <a href="login.jsp" class="btn-return">Return to Login</a>
    </footer>
</body>
</html>
