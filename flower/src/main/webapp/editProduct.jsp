<% if (request.getAttribute("message") != null) { %>
    <div style="color: green;"><%= request.getAttribute("message") %></div>
<% } %>
<% if (request.getAttribute("errorMessage") != null) { %>
    <div style="color: red;"><%= request.getAttribute("errorMessage") %></div>
<% } %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <link rel="stylesheet" href="css/style.css">
        <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-top: 20px;
        }

        form {
            width: 60%;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
        }

        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"], .btn {
            background-color: #FF9800; 
            color: white;
  /*           padding: 10px 20px; */
            padding: 12px 24px;  /* Adjust padding to make the button larger */
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-size: 16px;/* Increase the font size */
        }

        input[type="submit"]:hover, .btn:hover {
            background-color:#e68900; 
        }

/*         a {
            text-decoration: none;
            color: #4CAF50;
            margin-left: 20px;
            font-size: 1em;
        }

        a:hover {
            color: #45a049;
        } */
        
	 	.return-btn {
            display: block;
            margin: 40px auto 0px; /* locate at bottom */
            background-color: #007BFF; /* blue BG */
            color: white; 
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            text-align: center;
            text-decoration: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
            font-size: 16px;
            width: auto; /* Set width to auto */
            display: inline-block; /* Make buttons inline */            
        }

        .return-btn:hover {
            background-color: #0056b3;
        }
        
    </style>
</head>
<body>
    <h1>Edit Product</h1>
    <form action="manageInventory" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="product_id" value="${product.product_id}">

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${product.name}" required>

        <label for="description">Description:</label>
        <input type="text" id="description" name="description" value="${product.description}" required>

        <label for="price">Price:</label>
        <input type="number" id="price" name="price" value="${product.price}" step="0.01" required>

        <label for="category">Category:</label>
        <input type="text" id="category" name="category" value="${product.category}" required>

        <label for="image_url">Image URL:</label>
        <input type="text" id="image_url" name="image_url" value="${product.image_url}" required>

        <label for="stock">Stock:</label>
        <input type="number" id="stock" name="stock" value="${product.stock}" required>

        <input type="submit" value="Update Product">
        <a href="manageInventory.jsp" class="return-btn">Return to Manage Inventory</a>
    </form>
</body>
</html>
