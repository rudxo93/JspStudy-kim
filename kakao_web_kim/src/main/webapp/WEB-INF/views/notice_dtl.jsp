<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>뉴스</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/mainNav.css">
    <link rel="stylesheet" href="css/notice_dtl.css">
</head>
<body>
    <div class="container">
        <jsp:include page="include/index_include/index_header.jsp"></jsp:include>
        <main>
            <div class="notice_dtl_header">
                공지사항 글 작성
            </div>

            <div class="notice_dtl_main">
                <form>
                    <ul class="nd_title_ul">
                        <li class="nd_title_li">제목</li>
                        <li><input type="text"></li>
                    </ul>
                    <ul class="nd_info_ul">
                        <li>작성자</li>
                        <li>관리자</li>
                        <li>작성일</li>
                        <li>2021-10-14</li>
                    </ul>
                    <ul class="nd_content_ul">
                        <li>
                            <textarea name="" id=""></textarea>
                        </li>
                    </ul>

                    <button>작성완료</button>
                    <button>다시쓰기</button>
                </form>
            </div>
        </main>
        <footer>

        </footer>
    </div>
    <script src="https://kit.fontawesome.com/c3df4d7d1c.js" crossorigin="anonymous"></script>
</body>
</html>