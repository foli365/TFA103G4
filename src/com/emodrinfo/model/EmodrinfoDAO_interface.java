package com.emodrinfo.model;

import java.util.List;

public interface EmodrinfoDAO_interface {
	public void insert(EmodrinfoVO emodrinfoVO);
	public void update(EmodrinfoVO emodrinfoVO);
	public void delete(Integer emodr_infoid);
	public EmodrinfoVO findByPrimaryKey(Integer emodr_infoid);
	public List<EmodrinfoVO> getAll();
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmodrinfoVO> getAll(Map<String, String[]> map); 

}
