<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="AI가 생성한 영화 포스터와 줄거리를 만나보세요!">
    <title>🎬 영화 포스터 & 줄거리 생성기</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/style.css">
</head>
<body>

<h1>🎬 영화 포스터 & 줄거리 생성기</h1>

<form method="post" id="movieForm">

    <select name="genre" required>
        <option value="">영화 장르 선택</option>
        <option value="SF">SF</option>
        <option value="공포">공포</option>
        <option value="드라마">드라마</option>
        <option value="액션">액션</option>
        <option value="코미디">코미디</option>
        <option value="로맨스">로맨스</option>
    </select>

    <input type="text" name="idea" placeholder="아이디어 (예: 우주 전쟁)" required>

    <button type="submit" onclick="showLoading()">🎥 생성하기</button>
</form>
<div id="loading" style="display: none;">
    <div class="spinner"></div>
</div>

<script>
    document.getElementById('movieForm').addEventListener('submit', function(event) {
        event.preventDefault();  // 폼 제출 막기
        document.getElementById('loading').style.display = 'flex';  // 스피너 보이기

        // 폼 검증이 필요하다면 여기서 실행

        // 폼을 제출
        setTimeout(() => {
            this.submit();
        }, 100);
    });
</script>

</body>
</html>
