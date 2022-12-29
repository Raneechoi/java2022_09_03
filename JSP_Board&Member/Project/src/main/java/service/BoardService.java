package service;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardService {

	List<BoardVO> getlist();

	int register(BoardVO bvo);

	BoardVO selectOne(int bno);

	int update(BoardVO bvo);

	int remove(int bno);

	int count(BoardVO bvo);

	int getPageCnt();

	List<BoardVO> getListPage(PagingVO pgvo);

	List<BoardVO> getlist(String email);

	int modify(BoardVO bvo);

	String getFileName(int bno);



}
