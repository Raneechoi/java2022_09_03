package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {
	
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	private SqlSession sql;
	private final String NS = "MemberMapper.";
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public MemberVO selectOne(MemberVO mvo) {
		log.info("login check 3");
		return sql.selectOne(NS+"login",mvo);
	}

	@Override
	public int insert(MemberVO mvo) {
		log.info("join check 3");
		int isOK = sql.insert(NS+"reg",mvo);
		if(isOK > 0) {
			sql.commit();
		}
		return isOK;
	}

	@Override
	public int update(String email) {
		log.info("lastLogin check 3");
		int isOK = sql.update(NS+"last", email);
		if(isOK > 0) sql.commit();
		return isOK;
	}

	@Override
	public List<MemberVO> listall() {
		log.info("list check 3");
		return sql.selectList(NS+"list");
	}

	@Override
	public int update(MemberVO mvo) {
		log.info("modify check 3");
		int isOK = sql.update(NS+"modify",mvo);
		if(isOK > 0) sql.commit();
		return isOK;
	}

	@Override
	public MemberVO selectOne(String email) {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"getOne", email);
	}

	@Override
	public int remove(String email) {
		// TODO Auto-generated method stub
		int isOK = sql.delete(NS+"remove",email);
		if(isOK > 0) sql.commit();
		return isOK;
	}

}
