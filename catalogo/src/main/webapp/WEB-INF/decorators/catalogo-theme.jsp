<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.jsp.PageContext" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<title><decorator:title default="Ideais Electronics"/></title>

	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
	<script type="text/javascript" src="<c:url value="/resources/carousel/jquery.bxslider/jquery.bxslider.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/javascript-home.js"/>"></script>
	
	<link rel="stylesheet" href="<c:url value="/resources/carousel/jquery.bxslider/jquery.bxslider.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/carousels-bxslider.css"/>">
    
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.paging.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/pagination.js"/>"></script>

    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>">
	<link rel="stylesheet" href="<c:url value="/resources/css/style-products-category.css"/>">

</head>
<body>

	<header id="main-header">
		<div class="container-header">
			<h1 class="logo"><a href="${pageContext.request.contextPath}">Ideais Electronics</a></h1>
			
			<div id="search-area">
				<form method="get" action="${pageContext.request.contextPath}/product/search" name="searchProduct">
					<div class="search-box">
						<input type="text" name="productName" id="input-search-text">
						<input type="submit" value="Pesquisar" id="input-search-submit" class="search-icon">
					</div>
				</form>
			</div>
			
			<div class="login-or-register">
			
				<c:if test="${customerName != null}">
					<span>Olá, ${customerName}!</span>
					<a href="${pageContext.request.contextPath}/customer/customerDetails">Minha conta</a>
					<a href="${pageContext.request.contextPath}/customer/authenticate/logout">Logout</a>
				</c:if>
				
				<c:if test="${customerName == null}">
					<span>Olá, visitante!</span>
					<a href="${pageContext.request.contextPath}/customer/register">Cadastre-se</a>
					<a href="${pageContext.request.contextPath}/customer/authenticate/loginForm">Entre</a>
				</c:if>
					
			</div>
			
			<div class="cart">
				<span class="shopping-cart"><a href="${pageContext.request.contextPath}/shoppingCart/">Carrinho</a></span>
				<span class="qtCart">${cartItemsQuantity}</span>
			</div>

			<nav id="main-nav" class="main-menu">
				<ul class="menu">
					<c:forEach items="${menuCategories}"  var="category">
						<li class="menu-item"><a href="${pageContext.request.contextPath}/category/${category.id}/product">${category.name}</a>
							<ul class="sub-menu">
								<c:forEach items="${category.subcategories}" var="subcategory">
									<li class="submenu-item"><a href="${pageContext.request.contextPath}/subcategory/${subcategory.id}/product">${subcategory.name}</a></li>
								</c:forEach>
							</ul>
						</li>
					</c:forEach> 
				</ul>
			</nav>		
		</div>
	</header>	
	
	<decorator:body />
	
	<c:import url="/WEB-INF/jsp/footer.jsp"/>
	
</body>
</html>