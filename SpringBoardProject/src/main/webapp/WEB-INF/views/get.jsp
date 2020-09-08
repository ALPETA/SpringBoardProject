<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="./includes/header.jsp"%>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">글 상세</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">

			<div class="panel-heading">글 상세</div>
			<!-- /.panel-heading -->
			<div class="panel-body">

				<div class="form-inline form-group">
					<label>게시물 번호</label> <input class="form-control" name='bId'
						value='<c:out value="${board.bId}"/>' readonly="readonly">
					<label>조회수</label> <input class="form-control" name='bHit'
						value='<c:out value="${board.bHit}"/>' readonly="readonly">
				</div>

				<div class="form-group">
					<label>제목</label> <input class="form-control" name='bTitle'
						value='<c:out value="${board.bTitle }"/>' readonly="readonly">
				</div>

				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="3" name='bContent'
						readonly="readonly"><c:out value="${board.bContent}" /></textarea>
				</div>

				<div class="form-group">
					<label>작성자</label> <input class="form-control" name='bName'
						value='<c:out value="${board.bName}"/>' readonly="readonly">
				</div>



				<button
					onclick="location.href =  'moveModifytBoard.do?bId=${board.bId}';"
					class="btn btn-default">수정하기</button>
				<button
					onclick="location.href =  'deleteBoard.do?bId=${board.bId}';"
					class="btn btn-default">삭제하기</button>
				<button onclick="location.href =  'getBoardList.do';"
					class="btn btn-info">목록으로</button>

			</div>
			<!--  end panel-body -->

		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->



<%@include file="./includes/footer.jsp"%>
