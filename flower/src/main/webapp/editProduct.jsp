<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Edit Product</h1>
    <form action="ProductServlet" method="post">
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
    </form>
</body>
</html>
