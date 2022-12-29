package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;


@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    private RequestDispatcher rdp;
    private String destPage; //목적지 주소 기록 변수
    private int isOK; 
       
    private MemberService msv; // interface로 생성 - service패키지에

    public MemberController() {
    	msv = new MemberServiceImpl();
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
		case "login":
			destPage = "/member/login.jsp";
			break;
			
		case "login_auth":
			try {
				MemberVO mvo = msv.login(
						new MemberVO(
								req.getParameter("email"),
								req.getParameter("pwd")
								)); 
				log.info("login 객체 input");
				if(mvo != null) {
					//세션 가져오기. 연결된 세션이 없다면 새로 생성
					HttpSession ses = req.getSession();
					//ses로 mvo를 바인딩
					ses.setAttribute("ses", mvo);
					ses.setMaxInactiveInterval(10*60);  // 로그인 유지시간(10분)
				}else {
					req.setAttribute("msg_login", 0);
				}
				destPage="/";
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
			
		case "join":
			try {
				destPage = "/member/join.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "register":
			isOK = msv.register(new MemberVO(
					req.getParameter("email"),
					req.getParameter("pwd"),
					req.getParameter("nick_name")
					));
			log.info(">>> join > "+(isOK > 0 ?"ok" :"fail"));
			destPage="login";
			break;
			
		case "logout":
			// 연결된 세션이 있다면 해당 세션을 가져오기
			try {
				HttpSession ses = req.getSession();
				ses.invalidate();  // 세션 끊기
				// 로그인된 email을 주고 로그인 시간 update
				isOK = msv.lastLogin(req.getParameter("email"));
				log.info(">>> lastLogin > "+(isOK > 0 ?"ok" :"fail"));
				destPage="/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "list":
			try {
				List<MemberVO> list = msv.getlist();
				req.setAttribute("list", list);
				destPage="/member/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "modify":
			try {
				log.info("modify check 1");
				MemberVO mvo = msv.selectOne(req.getParameter("email"));
				req.setAttribute("mvo", mvo);
				
				log.info("modify check 2");
				destPage = "/member/update.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "update":
			try {
				MemberVO mvo = new MemberVO(req.getParameter("email"), req.getParameter("pwd"), req.getParameter("nick_name"));
				int isOk = msv.update(mvo);
				destPage = "/";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "remove":
			try {
				HttpSession ses = req.getSession();
				ses.invalidate();  // 세션 끊기
				// 로그인된 email을 주고 로그인 시간 update
				isOK = msv.remove(req.getParameter("email"));
				log.info(">>> remove > "+(isOK > 0 ?"ok" :"fail"));
				destPage="/";
				
			} catch (Exception e) {
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
