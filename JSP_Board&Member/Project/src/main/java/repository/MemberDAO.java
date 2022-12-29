package repository;

import java.util.List;

import domain.MemberVO;

public interface MemberDAO {

	MemberVO selectOne(MemberVO mvo);

	int insert(MemberVO mvo);

	int update(String email);

	List<MemberVO> listall();

	int update(MemberVO mvo);

	MemberVO selectOne(String email);

	int remove(String email);

}
