package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.MemberController;
import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	private BoardDAO bdao;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public List<BoardVO> getlist() {
		log.info("list check 2");
		return bdao.listall();
	}

	@Override
	public int register(BoardVO bvo) {
		log.info("register check 2");
		return bdao.insert(bvo);
	}

	@Override
	public BoardVO selectOne(int bno) {
		log.info("selectOne check 2");
		return bdao.selectOne(bno);
	}

	@Override
	public int update(BoardVO bvo) {
		log.info("update check 2");
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info("remove check 2");
		CommentServiceImpl csv = new CommentServiceImpl();
		
		int isOk = csv.removeAll(bno);
		return isOk > 0 ? bdao.remove(bno) : 0;
	}

	@Override
	public int count(BoardVO bvo) {
		log.info("count check 2");
		return bdao.countOne(bvo);
	}

	@Override
	public int getPageCnt() {
		return bdao.selectCount();
	}

	@Override
	public List<BoardVO> getListPage(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.selectList(pgvo);
	}

	@Override
	public List<BoardVO> getlist(String email) {
		// TODO Auto-generated method stub
		return bdao.mylist(email);
	}

	@Override
	public int modify(BoardVO bvo) {
		// TODO Auto-generated method stub
		return bdao.update(bvo);
	}

	@Override
	public String getFileName(int bno) {
		// TODO Auto-generated method stub
		return bdao.selectimageFile(bno);
	}


}
