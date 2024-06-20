package kr.co.koreait.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.koreait.beans.ContentBean;
import kr.co.koreait.mapper.TopMenuMapper;

@Repository // Bean 등록
public class TopMenuDao {
	
	@Autowired
	private TopMenuMapper topmenuMapper;
	
	
	public List<ContentBean>getTopMenuList() {
		
		List<ContentBean> topmenuList = topmenuMapper.getTopMenuList();
		
		return topmenuList;
		
	} 
}
