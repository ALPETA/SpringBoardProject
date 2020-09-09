<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<script src="resources/jQuery/jquery-3.4.1.min.js"></script>
<%@include file="./includes/header.jsp"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board List</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				게시글 목록
				<button id='regBtn' type="button" class="btn btn-xs pull-right"
					onclick="location.href =  'moveInsertBoard.do';">새로운 글 작성</button>
			</div>

			<!-- /.panel-heading -->
			<div class="panel-body">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<td>게시물 번호</td>
							<td>제목</td>
							<td>작성자</td>
							<td>날짜</td>
							<td>조회수</td>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${!empty boardList}">
							<c:forEach items="${boardList }" var="board">
								<tr>
									<td>${board.bId }</td>
									<td align="left"><a href="${board.bId }">
											${board.bTitle }</a></td>
									<td>${board.bName }</td>
									<td><fmt:formatDate value="${board.bDate }" /></td>
									<td>${board.bHit }</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5">등록된 글이 없습니다.</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
<br>

			<div id="pagingDiv">
			<c:if test="${paging.prev}">
				<a href="${paging.startPage - 1 }">이전</a>
			</c:if>
			<c:forEach var="num" begin="${paging.startPage}" end="${paging.endPage }">
				&nbsp;<a href="${num }">${num }</a>&nbsp;
			</c:forEach>
			<c:if test="${paging.next}">
				<a id="next" href="${paging.endPage + 1 }">다음</a>
			</c:if>
	</div>
	
	<form id="pagingFrm" name="pagingForm" action="getBoardList.do" method="get">
		<input type="hidden" id="pageNum" name="pageNum" value="${paging.cri.pageNum }">
		<input type="hidden" id="amount" name="amount" value="${paging.cri.amount }">
	</form>
			</div>


		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->

<script type="text/javascript">
	$(document).ready(function(){
		
		//페이지 번호 이동
		$('#pagingDiv a').click(function(e){
			e.preventDefault();
			$('#pageNum').val($(this).attr("href"));
			pagingForm.submit();
			
		});
		
		//게시글에 pageNum넘기기
		$('table a').click(function(e){
			e.preventDefault();
			var html = "<input type='hidden' name='bId' value='"+$(this).attr("href")+"'>";
			$('#pagingFrm').append(html);
			$('#pagingFrm').attr("action","getContent.do");
			$('#pagingFrm').submit();
		});
	});
</script>




<%@include file="./includes/footer.jsp"%>
