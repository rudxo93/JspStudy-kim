<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Web_Study</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body>
    <div class="container">
		<jsp:include page="include/header_include.jsp" />
        <main>
			<h1>메인 페이지</h1>
			<form action="forwardEx.jsp" method="get">
				<label>아이디</label>
				<input type="text" name="id"> 
				<label>비밀번호</label>
				<input type="password" name="pwd">
				<input type="submit" value="전송"> 
			</form>
        </main>
        <jsp:include page="include/footer_include.jsp" />
    </div>

    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/066978621d.js" crossorigin="anonymous"></script>
</body>

</html>