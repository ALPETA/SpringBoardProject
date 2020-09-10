package com.spring.board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVO;
import com.spring.board.vo.PageMaker;
import com.spring.board.vo.PagingCriteria;

@Controller
@SessionAttributes("board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	// 글 목록 검색
	@RequestMapping("/getBoardList.do")
	public String getBoardList(PagingCriteria cri, Model model) {
		List<BoardVO> boardList = boardService.getBoardList(cri);

		int total = boardService.totalCnt();

		// Model 정보 저장
		model.addAttribute("boardList", boardList);
		model.addAttribute("paging", new PageMaker(cri, total));
		return "list";
	}

	// 글 상세 조회
	@RequestMapping("/getContent.do")
	public String getBoard(BoardVO vo, Model model, @ModelAttribute("cri") PagingCriteria cri) {
		model.addAttribute("board", boardService.getContent(vo));
		return "get";
	}

	// File download
	@RequestMapping(value = "fileDownload.do")
	public void fileDownload4(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String bFileName = request.getParameter("fileName");
		String realFilename = "";
		System.out.println(bFileName);

		try {
			String browser = request.getHeader("User-Agent");
			// 파일 인코딩
			if (browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
				bFileName = URLEncoder.encode(bFileName, "UTF-8").replaceAll("\\+", "%20");
			} else {
				bFileName = new String(bFileName.getBytes("UTF-8"), "ISO-8859-1");
			}
		} catch (UnsupportedEncodingException ex) {
			System.out.println("UnsupportedEncodingException");
		}
		realFilename = "C:\\imgs\\" + bFileName;
		System.out.println(realFilename);
		File file1 = new File(realFilename);
		if (!file1.exists()) {
			return;
		}

		// 파일명 지정
		response.setContentType("application/octer-stream");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + bFileName + "\"");
		try {
			OutputStream os = response.getOutputStream();
			FileInputStream fis = new FileInputStream(realFilename);

			int ncount = 0;
			byte[] bytes = new byte[512];

			while ((ncount = fis.read(bytes)) != -1) {
				os.write(bytes, 0, ncount);
			}
			fis.close();
			os.close();
		} catch (Exception e) {
			System.out.println("FileNotFoundException : " + e);
		}
	}

	// 글 쓰기
	@RequestMapping(value = "/insertBoard.do")
	public String insertBoard(BoardVO vo) throws IOException {
		// 파일 업로드 처리
		String bFileName = null;
		MultipartFile uploadFile = vo.getUploadFile();
		if (!uploadFile.isEmpty()) {
			String originalFileName = uploadFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(originalFileName); // 확장자 구하기
			UUID uuid = UUID.randomUUID(); // UUID 구하기
			bFileName = uuid + "." + ext;
			uploadFile.transferTo(new File("C:\\imgs\\" + bFileName));
		}
		vo.setbFileName(bFileName);
		boardService.insertBoard(vo);
		return "redirect:getBoardList.do";
	}

	// 글 쓰기 페이지 이동
	@RequestMapping("/moveInsertBoard.do")
	public String moveInsertBoard() throws Exception {
		return "register";
	}

	// 글 수정 페이지 이동
	@RequestMapping("/moveModifytBoard.do")
	public String moveModifytBoard(BoardVO vo, Model model) {
		model.addAttribute("board", boardService.moveModifytBoard(vo));
		return "modify"; // View 이름 리턴
	}

	// 글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo) {
		boardService.updateBoard(vo);
		return "redirect:getBoardList.do";
	}

	// 글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		boardService.deleteBoard(vo);
		return "redirect:getBoardList.do";
	}
}
