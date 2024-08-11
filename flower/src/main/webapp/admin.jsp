<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
     <link rel="stylesheet" href="css/style.css"> 

        <style>
        /* set style for 'admin.jsp' */
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            font-size: 36px;

            background-color: #81C784;
            color: white;
            padding: 15px;
            border-radius: 15px; 
            margin-top: 0px;
            margin-bottom: 0;
        }

        nav {
            background-color: #333;
            overflow: hidden;
        }

        nav ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
        }

        nav ul li {
            margin: 0 15px;
        }

        nav ul li a {
            color: white;
            text-decoration: none;
            padding: 14px 20px;
            display: block;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        nav ul li a:hover {
            background-color: #575757;
        }

        nav ul li a.active {
            background-color: #4CAF50;
        }
        
                /* Style for 'return' button */
        .btn-return {
/*             display: block;
            margin: 40px auto 20px; */
    		position: fixed; 
    		bottom: 20px; /* 20px to bottom */
    		left: 50%; /* horizone middle */
    		transform: translateX(-50%); /* Modify to be middle */
 			width: 100%; 
            background-color: #007BFF; /* Blue BG */
            color: white; 
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            transition: background-color 0.3s ease;
            width: auto; /* Set width to auto */
            display: inline-block; /* Make buttons inline */ 
        }

        .btn-return:hover {
            background-color: #0056b3; /* Dark blue */
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
    
	<a href="login.jsp" class="btn-return">Return to Login</a>
</body>
</html>
