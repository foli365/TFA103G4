package com.members.model;

import java.util.List;

public interface MembersDAO_interface {
	public void insert(MembersVO membersVO);
    public void update(MembersVO membersVO);
    public MembersVO findByPrimaryKey(Integer memberId);
    public List<MembersVO> getAll();
}
