package cn.unicorn.myshiro.realm.service;

import java.util.Set;

import cn.unicorn.myshiro.realm.vo.Member;

public interface IMemberService {
	/**
	 * 根据用户编号获取一个用户的信息,用户是否存在,锁定状态,密码验证都交给Realm完成
	 * @param mid 用户的iD
	 * @return 如果用户信息存在则以VO的心形式返回,如果不存在返回null
	 */
	public Member get(String mid);
	
	/**
	 * 根据用户的编号查询出该用户对应的所有角色信息,查询的是member_role对应表
	 * @param mid
	 * @return
	 */
	public Set<String> listRoleByMember(String mid);
	
	/**
	 * 根据用户编号查询出对应的所有权限信息
	 * @param mid 用户编号
	 * @return 所有的权限信息
	 */
	public Set<String> listActionByMember(String mid);
}
