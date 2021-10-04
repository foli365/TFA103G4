package com.members.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class MemberService {

	private MembersDAO_interface dao;

	public MemberService() {
		// TODO Auto-generated constructor stub
		dao = new MembersDAO();
	}

	public MembersVO addMember(String name, String phone, String email) {
		MembersVO membersVO = new MembersVO();
		membersVO.setName(name);
		membersVO.setPhone(phone);
		membersVO.setEmail(email);
		dao.insert(membersVO);
		return membersVO;
	}

	public MembersVO updateMembers(Integer memberId, String name, String phone, String email, Integer membership, Integer memberStatus,
			String thumbnail, String address) {
		MembersVO membersVO = new MembersVO();
		membersVO.setName(name);
		membersVO.setAddress(address);
		membersVO.setEmail(email);
		membersVO.setMembership(membership);
		membersVO.setMemberStatus(memberStatus);
		membersVO.setPhone(phone);
		try {
			membersVO.setThumbnail(getPictureByteArray(thumbnail));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		membersVO.setMemberId(memberId);
		dao.update(membersVO);
		return membersVO;
	}

	public MembersVO findByPrimaryKey(Integer memberId) {
		return dao.findByPrimaryKey(memberId);
	}

	public List<MembersVO> getAll() {
		if (dao.getAll() != null) {
			return dao.getAll();			
		} else {
			System.out.println("¨ú­È¥X²{¿ù»~");
			return null;
		}
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
