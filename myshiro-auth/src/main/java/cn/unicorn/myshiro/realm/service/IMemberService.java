package cn.unicorn.myshiro.realm.service;

import cn.unicorn.myshiro.realm.vo.Member;

public interface IMemberService {
	/**
	 * 根据用户编号获取一个用户的信息,用户是否存在,锁定状态,密码验证都交给Realm完成
	 * @param mid 用户的iD
	 * @return 如果用户信息存在则以VO的心形式返回,如果不存在返回null
	 */
	public Member get(String mid);
}
