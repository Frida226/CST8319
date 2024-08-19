<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flower Shop</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"> 
    <style>
        /* Basic styling for the dropdown */
        .profile-dropdown {
            position: relative;
            display: inline-block;
        }

        .profile-dropdown-content {
            display: none;
            position: absolute;
            background-color: white;
            min-width: 120px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 1;
            border-radius: 5px;
        }

        .profile-dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            border-bottom: 1px solid #ddd;
        }

        .profile-dropdown-content a:last-child {
            border-bottom: none;
        }

        .profile-dropdown-content a:hover {
            background-color: #f1f1f1;
        }

        .profile-dropdown:hover .profile-dropdown-content {
            display: block;
        }

        .profile-dropdown:hover .icon-link {
            background-color: #f1f1f1;
            border-radius: 50%;
        }

        .icon-link {
            vertical-align: middle; /* Adjusts the vertical alignment */
            padding: 0 5px; /* Adds padding for better alignment */
        }

        nav a {
            display: inline-block;
            padding: 10px 15px;
            text-decoration: none;
            color: #333;
        }

        nav a.icon-link {
            padding: 0 10px;
        }
    </style>
</head>
<body>
<header>
    <h1>Flower Shop</h1>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="about.jsp">About</a>
        <a href="shop.jsp">Shop</a>
        
        <a href="contact.jsp">Contact</a>
        
        <!-- Profile dropdown -->
        <div class="profile-dropdown">
            <a href="Profile" class="icon-link">
                <i class="fas fa-user"></i>
            </a>
            <div class="profile-dropdown-content">
                <a href="Profile">Profile</a>
                <a href="Profile?action=Logout">Logout</a>
            </div>
        </div>
        
        <a href="wishlist.jsp" class="icon-link">
            <i class="fas fa-heart"></i> 
        </a>
<<<<<<< HEAD
        <a href="<%=request.getContextPath()%>/CartServlet"  class="icon-link">
=======
        <a href="Cart" class="icon-link">
>>>>>>> stash
            <i class="fas fa-shopping-cart"></i> 
        </a>
    </nav>
</header>
</body>
</html>
