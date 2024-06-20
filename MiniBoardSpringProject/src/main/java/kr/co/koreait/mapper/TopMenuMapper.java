package kr.co.koreait.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.koreait.beans.ContentBean;

@Mapper
public interface TopMenuMapper {

	@Select
	("select board_info_idx, board_info_name "+
			"from board_info_table "+
			"order by board_info_idx;")
	List<ContentBean>getTopMenuList(); // 쿼리문 날리는 것 
	// 리턴값이 클래스 단위이면  beans에서 getter setter 만들어야 한다
}


