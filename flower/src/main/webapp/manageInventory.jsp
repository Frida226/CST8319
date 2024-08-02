<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.flowerorder.model.Products" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Inventory</title>
    <link rel="stylesheet" href="css/style.css">
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
                    <form action="manageInventory" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="product_id" value="<%= product.getProduct_id() %>">
                        <input type="submit" value="Delete">
                    </form>
                    <button onclick="populateEditForm(<%= product.getProduct_id() %>)">Edit</button>
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
</body>
</html>
