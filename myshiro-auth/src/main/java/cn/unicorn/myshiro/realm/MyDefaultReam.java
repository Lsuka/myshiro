package cn.unicorn.myshiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

/**
 * 自定义的Realm处理操作实现,主要提供认证的数据信息
 * 
 * @author UNICORN
 *
 */
public class MyDefaultReam implements Realm {

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {// 实现具体的认证处理
		String mid = (String) token.getPrincipal();// 获取用户名
		String password = new String((String) token.getCredentials());// 获取密码
		// 此时是对固定的信息进行认证处理,所有现在定义用户名为admin，hello
		if (!"admin".equals(mid)) {// 用户名不存在
			throw new UnknownAccountException("此账户不存在");
		}
		if (!"hello".equals(mid)) {// 密码不正确
			throw new UnknownAccountException("密码错误");
		}
		return new SimpleAuthenticationInfo(token.getPrincipal(), token.getCredentials(), this.getName());
	}

	@Override
	public String getName() {// 随意编写,主要是区分不同realm
		return "shiro-default-realm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;// 现在如果使用的是简单的用户名和密码认证
	}

}
