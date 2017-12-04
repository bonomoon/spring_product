<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="robots" content="all,follow">
<meta name="googlebot" content="index,follow,snippet,archive">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Obaju e-commerce template">
<meta name="author" content="Ondrej Svestka | ondrejsvestka.cz">
<meta name="keywords" content="">

<title>쏘공나와</title>

<meta name="keywords" content="">

<link href='http://fonts.googleapis.com/css?family=Roboto:400,500,700,300,100' rel='stylesheet' type='text/css'>

<!-- styles -->
<link href="resources/css/font-awesome.css" rel="stylesheet">
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/animate.min.css" rel="stylesheet">
<link href="resources/css/owl.carousel.css" rel="stylesheet">
<link href="resources/css/owl.theme.css" rel="stylesheet">

<!-- theme stylesheet -->
<link href="resources/css/style.blue.css" rel="stylesheet" id="theme-stylesheet">

<!-- your stylesheet with modifications -->
<link href="resources/css/custom.css" rel="stylesheet">

<script src="resources/js/respond.min.js"></script>

<link rel="shortcut icon" href="favicon.png">
</head>
<body>
	<jsp:include page="_top.jsp"></jsp:include>
	<jsp:include page="_navbar.jsp"></jsp:include>


	<div id="all">

		<div id="content">
			<div class="container">

				<div class="col-md-12">

					<ul class="breadcrumb">
						<li><a href="#">게시판</a></li>
					</ul>

					<div class="row" id="error-page">
						<div class="col-md-12">
							<div class="box">
								<table class="table table-hover">
									<thead>
										<tr>
											<th>번호</th>
											<th>제목</th>
											<th>작성자</th>
											<th>날짜</th>
											<th>조회수</th>
										</tr>
									</thead>
									<tbody class="text-left">
										<c:forEach items="${boardList}" var="board">
											<tr onClick="location.href='board_detail?board_id=${board.id}'">
												<td>${board.id}</td>
												<td>${board.title}</td>
												<td>${board.author}</td>
												<td>${board.date}</td>
												<td>${board.hits}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>

								<hr />
								<a href="board_edit" class="btn btn-primary pull-right">글쓰기</a>


								<div class="text-center">
									<ul class="pagination">
										<li><a href="#">1</a></li>
<!-- 										<li><a href="#">2</a></li> -->
<!-- 										<li><a href="#">3</a></li> -->
<!-- 										<li><a href="#">4</a></li> -->
<!-- 										<li><a href="#">5</a></li> -->
									</ul>

								</div>

							</div>
						</div>
					</div>


				</div>
				<!-- /.col-md-9 -->
			</div>
			<!-- /.container -->
		</div>
		<!-- /#content -->
	</div>

	<jsp:include page="_footer.jsp"></jsp:include>
	<jsp:include page="_copyright.jsp"></jsp:include>

	<!-- /#all -->


	<!-- *** SCRIPTS TO INCLUDE ***
 _________________________________________________________ -->
	<script src="resources/js/jquery-1.11.0.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/jquery.cookie.js"></script>
	<script src="resources/js/waypoints.min.js"></script>
	<script src="resources/js/modernizr.js"></script>
	<script src="resources/js/bootstrap-hover-dropdown.js"></script>
	<script src="resources/js/owl.carousel.min.js"></script>
	<script src="resources/js/front.js"></script>

</body>
</html>