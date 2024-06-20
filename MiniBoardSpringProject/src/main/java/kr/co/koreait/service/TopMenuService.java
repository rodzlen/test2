package kr.co.koreait.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.koreait.dao.TopMenuDao;
import kr.co.koreait.beans.ContentBean;

@Service
public class TopMenuService {
	@Autowired
	private	TopMenuDao TopMenuDao ;
	
	
	public List<ContentBean>getTopMenuList() {
		
		List<ContentBean> topmenuList = TopMenuDao.getTopMenuList();
		
		return topmenuList;
}}
