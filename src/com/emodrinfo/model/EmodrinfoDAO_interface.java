package com.emodrinfo.model;

import java.util.List;

public interface EmodrinfoDAO_interface {
	public void insert(EmodrinfoVO emodrinfoVO);
	public void update(EmodrinfoVO emodrinfoVO);
	public void delete(Integer emodr_infoid);
	public EmodrinfoVO findByPrimaryKey(Integer emodr_infoid);
	public List<EmodrinfoVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmodrinfoVO> getAll(Map<String, String[]> map); 

}
