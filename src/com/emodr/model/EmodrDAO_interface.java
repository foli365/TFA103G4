package com.emodr.model;

import java.util.*;

public interface EmodrDAO_interface {
	public void insert(EmodrVO emodrVO);

	public void update(EmodrVO emodrVO);

	public void delete(Integer emodrid);

	public EmodrVO findByPrimaryKey(Integer emodrid);

	public List<EmodrVO> getAll();
	// �U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//	public List<EmodrVO> getAll(Map<String,String[]> map);
}
