package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.MemberController;
import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	
	private MemberDAO mdao;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login check 2");
		return mdao.selectOne(mvo);
	}

	@Override
	public int register(MemberVO mvo) {
		log.info("register check 2");
		return mdao.insert(mvo);
	}

	@Override
	public int lastLogin(String email) {
		log.info("lastLogin check 2");
		return mdao.update(email);
	}

	@Override
	public List<MemberVO> getlist() {
		log.info("list check 2");
		return mdao.listall();
	}

	@Override
	public int modify(MemberVO mvo) {
		log.info("modify check 2");
		return mdao.update(mvo);
	}

	@Override
	public MemberVO selectOne(String email) {
		// TODO Auto-generated method stub
		return mdao.selectOne(email);
	}

	@Override
	public int update(MemberVO mvo) {
		// TODO Auto-generated method stub
		return mdao.update(mvo);
	}

	@Override
	public int remove(String email) {
		// TODO Auto-generated method stub
		return mdao.remove(email);
	}
}
