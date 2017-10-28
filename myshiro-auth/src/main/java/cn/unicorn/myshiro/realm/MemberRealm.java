package cn.unicorn.myshiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import cn.unicorn.myshiro.realm.service.IMemberService;
import cn.unicorn.myshiro.realm.service.impl.MemberServiceImpl;
import cn.unicorn.myshiro.realm.vo.Member;

public class MemberRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.err.println("2.用户授权处理(doGetAuthorizationInfo)");
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.err.println("1.进行用户认证处理(doGetAuthorizationInfo)");
		IMemberService memberService = new MemberServiceImpl();
		String mid = (String) token.getPrincipal();
		Member member = memberService.get(mid);
		if (member == null) {
			throw new UnknownAccountException("账户" + mid + "不存在");
		}
		String password = new String((char[]) token.getCredentials());
		if (!member.getPassword().equals(password)) {
			throw new IncorrectCredentialsException("错误的密码");
		}
		if (member.getLocked().equals(1)) {
			throw new LockedAccountException(mid + "被锁定");
		}
		return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), "memberRealm");
	}

}
