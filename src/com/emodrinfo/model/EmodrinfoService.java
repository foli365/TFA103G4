package com.emodrinfo.model;

import java.util.List;

public class EmodrinfoService {

	private EmodrinfoDAO_interface dao;

	public EmodrinfoService() {
		dao = new EmodrinfoDAO();
	}

	public EmodrinfoVO addEmodrinfo(Integer emodr_id, Integer product_no, Integer qty, Integer price, String comm) {

		EmodrinfoVO emodrinfoVO = new EmodrinfoVO();

		emodrinfoVO.setEmodr_id(emodr_id);
		emodrinfoVO.setProduct_no(product_no);
		emodrinfoVO.setQty(qty);
		emodrinfoVO.setPrice(price);
		emodrinfoVO.setComm(comm);

		dao.insert(emodrinfoVO);

		return emodrinfoVO;
	}

	public EmodrinfoVO updateEmodrinfo(Integer emodr_infoid, Integer emodr_id, Integer product_no, Integer qty,
			Integer price, String comm) {

		EmodrinfoVO emodrinfoVO = new EmodrinfoVO();

		emodrinfoVO.setEmodr_id(emodr_id);
		emodrinfoVO.setProduct_no(product_no);
		emodrinfoVO.setQty(qty);
		emodrinfoVO.setPrice(price);
		emodrinfoVO.setComm(comm);
		emodrinfoVO.setEmodr_infoid(emodr_infoid);

		dao.update(emodrinfoVO);

		return emodrinfoVO;
	}

	public void deleteEmodrinfo(Integer emodr_infoid) {
		dao.delete(emodr_infoid);
	}

	public EmodrinfoVO getOneEmodrinfo(Integer emodr_infoid) {
		return dao.findByPrimaryKey(emodr_infoid);
	}

	public List<EmodrinfoVO> getAll() {
		return dao.getAll();
	}

}
