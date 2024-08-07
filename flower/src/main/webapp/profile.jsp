<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.flowerorder.model.Users"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="stylesheet" href="css/style.css"> <!-- Ensure this path is correct -->
    <style>
        .user-property p {
            background-color: white;
            padding: 10px;
            margin: 5px 0;
            font-size: 1.2em; /* Increase font size */
            border-radius: 5px;
        }
    </style>
</head>
<body>

<jsp:include page="header.jsp" />

<section class="team">
    <h2>Profile</h2>
    <c:if test="${user != null}">
        <div class="team-member user-property">
            <h3>Username</h3>
            <p>${user.username}</p>
        </div>
        <div class="team-member user-property">
            <h3>Email</h3>
            <p>${user.email}</p>
        </div>
        <div class="team-member user-property">
            <h3>Phone Number</h3>
            <p>${user.phoneNumber}</p>
        </div>
        <div class="team-member user-property">
            <h3>First Name</h3>
            <p>${user.firstName}</p>
        </div>
        <div class="team-member user-property">
            <h3>Last Name</h3>
            <p>${user.lastName}</p>
        </div>
        <div class="team-member user-property">
            <h3>Address</h3>
            <p>${user.address}</p>
        </div>
    </c:if>
    <c:if test="${user == null}">
        <div class="team-member">
            <p>Profile information not available.</p>
        </div>
    </c:if>
</section>

<jsp:include page="footer.jsp" />

</body>
</html>
