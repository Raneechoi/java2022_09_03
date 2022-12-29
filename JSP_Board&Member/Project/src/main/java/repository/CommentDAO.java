package repository;

import java.util.List;

import domain.CommentVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> selectList(int bno);

	int deleteList(int cno);

	int modify(CommentVO cvo);

	int deleteAll(int bno);

}
