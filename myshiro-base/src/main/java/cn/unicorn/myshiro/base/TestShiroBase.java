package cn.unicorn.myshiro.base;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import junit.framework.TestCase;

public class TestShiroBase extends TestCase {
	private static final String USERNAME = "mldn";
	private static final String PASSWORD = "java";

	public void testAuth() {
		//定义安全管理的控制工厂类,现在的安全管理的信息都是通过配置文件定义出来的
		Factory<SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//通过SecurityManagerFactory创建一个SecurityManager对象
		SecurityManager securityManager = securityManagerFactory.getInstance();
		//将安全管理器配置到安全工具处理类之中,以后所有的认证检测由此工具类负责
		SecurityUtils.setSecurityManager(securityManager);
		//如果要进行认证肯定是要针对某一个用户实现的认证处理,而每一个用户都使用Subject描述
		Subject subject = SecurityUtils.getSubject();
		//随后现在如果要想进行认证处理,还需要将用户输入的认证信息设置到一个Token里面
		AuthenticationToken token = new UsernamePasswordToken(USERNAME, PASSWORD);
		//利用Subject进行Token认证处理,Subject之中包含有之前配置的ini文件的全部信息
		//登录成功之后会自动将用户名保存到自己的SessionManager之中
		subject.login(token);//登录认证,登录失败抛弃异常
		System.err.println(subject.getPrincipal());//获取用户名
	}
}
