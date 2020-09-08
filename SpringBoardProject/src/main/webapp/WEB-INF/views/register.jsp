<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="./includes/header.jsp"%>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">새로운 글 작성</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">

			<div class="panel-heading">새로운 글 작성</div>
			<!-- /.panel-heading -->
			<div class="panel-body">

				<form role="form" action="insertBoard.do" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label>제목</label> <input class="form-control" name='bTitle'>
					</div>

					<div class="form-group">
						<label>내용</label>
						<textarea class="form-control" rows="3" name='bContent'></textarea>
					</div>

					<div class="form-group">
						<label>작성자</label> <input class="form-control" name='bName'>
					</div>
					<div class="form-group">
						<label>파일 업로드</label> <input type="file" class="form-control"
							name='uploadFile' />
					</div>
					<button type="submit" class="btn btn-default">저장</button>
					<button type="reset" class="btn btn-default">초기화</button>
					<button onclick="location.href =  'getBoardList.do';"
						class="btn btn-info">취소하기</button>
				</form>

			</div>
			<!--  end panel-body -->

		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->
<%@include file="./includes/footer.jsp"%>
