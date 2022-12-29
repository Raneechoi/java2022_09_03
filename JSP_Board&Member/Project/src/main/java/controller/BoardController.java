package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.MemberVO;
import domain.PagingVO;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;


@WebServlet("/bor/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    private RequestDispatcher rdp;
    private String destPage; //목적지 주소 기록 변수
    private int isOK;
    private String savePath;  // 파일경로를 저장할 변수
    private final String UTF8 = "utf-8";  // 인코딩 설정시 필요함
    
    private BoardService bsv;

    public BoardController() {
        bsv = new BoardServiceImpl();
    }


	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/")+1);
		
		log.info(">>> URI > "+uri);
		log.info(">>> Path > "+path);
		
		switch (path) {
		case "list":
			try {
				HttpSession ses = req.getSession();
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				String email = mvo.getEmail();
				List<BoardVO> list = bsv.getlist(email);
				req.setAttribute("list", list);
				destPage = "/board/list.jsp";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "register":
			try {
				log.info("register check 1");
				destPage = "/board/register.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "insert":
			try {
				// 파일을 업로드할 물리적인 경로 설정(업로드 할때 설정)
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				log.info("저장위치 : "+savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);  // 저장할 위치를 file객체로 지정
				fileItemFactory.setSizeThreshold(2*1024*1024);  // 파일 저장을 위한 임시 메모리 저장 용량 설정 : byte 단위
				
				BoardVO bvo = new BoardVO();
				// multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환해주는 역할
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = fileUpload.parseRequest(req);
				for(FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "title":
						bvo.setTitle(item.getString(UTF8));  // 인코딩형식을 담아서 변환
						break;
						
					case "writer":
						bvo.setWriter(item.getString(UTF8));
						break;
						
					case "content":
						bvo.setContent(item.getString(UTF8));
						break;
						
					case "image_file":
						// 이미지가 있는지 없는지 체크
						if(item.getSize() > 0) {  // 데이터의 크기를 바이트 단위로 리턴
							String fileName = item.getName()  // 경로를 포함한 파일 이름
									.substring(item.getName().lastIndexOf("/")+1);  // 파일 이름만 분리
							// 시스템의 현재시간_파일명
							fileName = System.currentTimeMillis()+"_"+fileName;
							File UploadFilePath = new File(fileDir + File.separator+fileName);
							log.info("파일 경로+이름 : "+UploadFilePath);
							
							// 저장
							try {
								item.write(UploadFilePath);  // 자바 객체를 디스크에 쓰기
								bvo.setImage_file(fileName);
								
								// 썸네일 작업 : 리스트 페이지에서 트래픽 과다사용 방지
								Thumbnails.of(UploadFilePath)
								.size(75, 75)
								.toFile(new File(fileDir + File.separator+"th_"+fileName));
							} catch (Exception e) {
								log.info(">>> file writer on disk fail");
								e.printStackTrace();
							}
						}
						break;
					}
				}
				isOK = bsv.register(bvo);
				log.info(">>> insert > " + (isOK > 0 ? "OK":"fail"));
				
//				String title = req.getParameter("title");
//				String writer = req.getParameter("writer");
//				String content = req.getParameter("content");
//				log.info("insert check 1");
//				isOK = bsv.register(new BoardVO(title, writer, content));
//				log.info(isOK > 0 ? "성공" : "실패");
				destPage = "pageList";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "detail":
			try {
				int bno = Integer.parseInt(req.getParameter("bno"));
				// service에게 호출 시 read_count +1하고(update), 디테일 값을 호출
				BoardVO co = bsv.selectOne(bno);
				co = new BoardVO(bno, co.getRead_count());
				log.info("detail check 1");
				isOK = bsv.count(co);
				BoardVO bvo = bsv.selectOne(bno);
				log.info("detail check 4");
				req.setAttribute("bvo", bvo);
				destPage = "/board/detail.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "modify":
			try {
				int bno = Integer.parseInt(req.getParameter("bno"));
				log.info("modify check 1");
				BoardVO bvo = bsv.selectOne(bno);
				log.info("modify check 4");
				req.setAttribute("bvo", bvo);
				destPage = "/board/modify.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "update":
			try {
				savePath = getServletContext().getRealPath("/_fileUpload");
				File fileDir = new File(savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);
				fileItemFactory.setSizeThreshold(2*1024*1024);  // 임시메모리공간 부여
				
				BoardVO bvo = new BoardVO();  // BoardVO 객체 생성
				
				// fileItemFactory(경로)를 싣고  fileUpload 생성
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				log.info(">>> update 준비 ");
				
				// List로 객체를 생성 req의 자료를 upload에 싣고
				List<FileItem> itemList = fileUpload.parseRequest(req);
				
				// 파일 구분
				String old_file = null;
				
				for(FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "bno":
						bvo.setBno(Integer.parseInt(item.getString(UTF8)));
						break;
						
					case "title":
						bvo.setTitle(item.getString(UTF8));
						break;
						
					case "content":
						bvo.setContent(item.getString(UTF8));
						break;
						
					case "image_file":
						// old_file 이전 파일
						old_file = item.getString(UTF8);
						break;
						
					case "new_file":
						if(item.getSize() > 0) {  // 새로운 파일이 등록 됨.
							if(old_file != null) {  // 삭제삭업
								// 파일 핸들러를 이용하여 기존파일을 삭제
								FileHandler fileHandler = new FileHandler();
								isOK = fileHandler.deleteFile(old_file, savePath);
							}
							// 경로가 포함된 전체경로와 파일이름 생성
							String fileName = item.getName().substring(item.getName().lastIndexOf("/")+1);
							log.info(">>> fileName : "+fileName);  // 새 파일의 이름
							//실제 저장될 파일이름을 만들었음
							fileName = System.currentTimeMillis()+"_"+fileName;  
							File uploadFilePath = new File(fileDir + File.separator+fileName);
							try {
								item.write(uploadFilePath);  // 자바 객체를 디스크에 쓰기
								bvo.setImage_file(fileName);
								log.info(">>> upload img_file : " + (bvo.getImage_file()));
								
								// 썸네일 작업
								Thumbnails.of(uploadFilePath).size(75, 75).toFile(new File(fileDir+File.separator+"th_"+fileName));
								
							} catch (Exception e) {
								log.info(">>> File Writer on disk Fail");
								e.printStackTrace();
							}
							
						}else {  // 새로운 파일이 없다면 기존파일을 bvo 객체에 담기
							bvo.setImage_file(old_file);
						}
						break;
					}
				}
				isOK = bsv.modify(bvo);
				log.info(">>> update : " + (isOK > 0? "OK" : "FAIL"));
				
//				int bno = Integer.parseInt(req.getParameter("bno"));
//				String title = req.getParameter("title");
//				String content = req.getParameter("content");
//				log.info("update check 1");
//				isOK = bsv.update(new BoardVO(bno, title, content));
//				log.info(isOK > 0 ? "성공" : "실패");
				destPage = "pageList";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "remove":
			try {
				int bno = Integer.parseInt(req.getParameter("bno"));
				// 이미지 파일 이름 불러오기
				String imageFileName = bsv.getFileName(bno);  // 삭제할 파일 이름 
				FileHandler fileHandler = new FileHandler();
				savePath = getServletContext().getRealPath("/_fileUpload");
				isOK = fileHandler.deleteFile(imageFileName, savePath);
				log.info(">>> fileDelete : "+ (isOK > 0 ? "OK" : "FAIL"));
				isOK = bsv.remove(bno);
				log.info("allDelete : "+(isOK > 0 ? "OK":"FAIL"));
				destPage = "pageList";
				
			} catch (Exception e) {
				log.info("remove error");
				e.printStackTrace();
			}
			break;
			
		case "pageList":
			try {
				PagingVO pgvo = new PagingVO();
				int totCount = bsv.getPageCnt();  // 전체 카운트 호출
				List<BoardVO> list = bsv.getListPage(pgvo);  //limit이용한 한 페이지 리스트 호출
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				req.setAttribute("list", list);  // 한 페이지에 나와야 하는 리스트 보내기
				req.setAttribute("pgh", ph);   // 페이지 정보 보내기
				System.out.println("totCount 1"+totCount);
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("paging error");
				e.printStackTrace();
			}
			break;
			
		case "page":
			try {
				int pageNo = Integer.parseInt(req.getParameter("pageNo"));
				int qty = Integer.parseInt(req.getParameter("qty"));
				System.out.println("pageNo"+pageNo);
				PagingVO pgvo = new PagingVO(pageNo, qty);
				
				int totCount = bsv.getPageCnt();  // 전체 카운트 호출
				List<BoardVO> list = bsv.getListPage(pgvo);  //limit이용한 한 페이지 리스트 호출
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				req.setAttribute("list", list);  // 한 페이지에 나와야 하는 리스트 보내기
				req.setAttribute("pgh", ph);   // 페이지 정보 보내기
				destPage = "/board/list.jsp";
				
			} catch (Exception e) {
				log.info("subpage error");
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
		
		// 목적지 주소 값 세팅
		rdp = req.getRequestDispatcher(destPage);
		// 정보 싣고 보내기
		rdp.forward(req, res);
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		service(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		service(req, res);
	}

}
