package com.emodr.model;

import java.util.*;

public interface EmodrDAO_interface {
	public void insert(EmodrVO emodrVO);

	public void update(EmodrVO emodrVO);

	public void delete(Integer emodrid);

	public EmodrVO findByPrimaryKey(Integer emodrid);

	public List<EmodrVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//	public List<EmodrVO> getAll(Map<String,String[]> map);
}
