<% if (request.getAttribute("message") != null) { %>
    <div style="color: green;"><%= request.getAttribute("message") %></div>
<% } %>
<% if (request.getAttribute("errorMessage") != null) { %>
    <div style="color: red;"><%= request.getAttribute("errorMessage") %></div>
<% } %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.flowerorder.model.Products" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Inventory</title>
    <link rel="stylesheet" href="css/style.css">
        <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
        }



        /* Main heading*/
        h1 {
            text-align: center;
            font-size: 36px; /* larger font  */

            background-color: #81C784;
            color: white;
            padding: 15px;
            border-radius: 8px; /* round corner */
            margin-top: 20px;
        }

        /* Sub heading */
       h2 {
    		text-align: center;
   			font-size: 22px; 
		    color: #ffffff; 
		    background-color: #5cb85c; 
		    padding: 8px 16px; 
		    border-radius: 5px;
		    font-family: 'Arial', sans-serif; 
		    margin-top: 20px; 
		    display: inline-block;
		    margin-left:80px
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
/*             background-color: #4CAF50; */
            background-color: #007BFF; /* blue */
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover, .btn:hover {
         /*    background-color: #45a049; */
         	background-color: #0056b3; /* dark blue when hover */
        }

        /* Modify 'View All Product Items' button style */
        .btn-info {
		    text-align: center;
		    font-size: 18px;
		    color: #ffffff; 
		    background-color: #5cb85c; 
		    padding: 10px 20px; 
		    border: none;
		    border-radius: 5px; 
		    font-family: 'Arial', sans-serif; 
		    margin-top: 10px;
		    display: inline-block; 
		    cursor: pointer; 
		    text-decoration: none; 
		    transition: background-color 0.3s ease; 
		    width: auto; 
		    margin-left:80px
		}

		.btn-info:hover {
		    background-color: #4cae4c; 
		}
        
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        /* Moddify style of 'Delete' button */
        input[type="submit"][value="Delete"] {
            background-color: #FF0000; /* red */
            color: white; 
            padding: 0; 
            font-size: 1em; /* Modify font size */ 
        }

        input[type="submit"][value="Delete"]:hover {
            background-color: #CC0000; /* dark red */
        }

        .edit-btn {
            background-color: #2196F3;
            color: white;
            text-align: center;
            padding: 0.5px 10px;
            border: none;
            border-radius: 4px;
            text-decoration: none;
        }

        .edit-btn:hover {
            background-color: #0b7dda;
        }
        
        /* Add style of 'return button' */
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
    		margin-bottom: 60px;
   		}       
    </style>
</head>
<body>
    <h1>Manage Inventory</h1>

    <!-- Add Product Form -->
    <h2>Add New Product</h2>
    <form action="manageInventory" method="post">
        <input type="hidden" name="action" value="add">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required>
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" step="0.01" required>
        <label for="category">Category:</label>
        <input type="text" id="category" name="category" required>
        <label for="image_url">Image URL:</label>
        <input type="text" id="image_url" name="image_url" required>
        <label for="stock">Stock:</label>
        <input type="number" id="stock" name="stock" required>
        <input type="submit" value="Add Product">
    </form>

    <!-- Existing Products -->
   <!--  <h2>Current Inventory</h2> -->
   <a href="manageInventory?action=view"
   class="btn btn-info">View All Product Items</a>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Category</th>
                <th>Image</th>
                <th>Stock</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<Products> listProducts = (List<Products>) request.getAttribute("products");
            if (listProducts != null) {
                for (Products product : listProducts) {
            %>
            <tr>
                <td><%= product.getName() %></td>
                <td><%= product.getDescription() %></td>
                <td><%= product.getPrice() %></td>
                <td><%= product.getCategory() %></td>
                <td><img src="<%= product.getImage_url() %>" alt="Product Image" style="width: 50px; height: auto;"></td>
                <td><%= product.getStock() %></td>
                <td>
                    <form action="manageInventory" method="post" onsubmit="return confirm('Are you sure you want to delete this product?');">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="product_id" value="<%= product.getProduct_id() %>">
                        <input type="submit" value="Delete">
                    </form>
                    <form>
                   <%--  <button onclick="populateEditForm(<%= product.getProduct_id() %>)">Edit</button> --%>
                    <a href="manageInventory?action=edit&product_id=<%= product.getProduct_id() %>" class="edit-btn">Edit</a>
                    </form>
                </td>
            </tr>
            <% 
                }
            }
            %>
        </tbody>
    </table>

     <script>
        function populateEditForm(productId) {
            // JavaScript function to populate and show an edit form
            // This could also be implemented by showing a modal or separate page
        }
    </script>
    <div class="button-container">
   		<a href="admin.jsp" class="return-btn">Return to Admin Dashboard</a>
   	</div>
</body>
</html>
