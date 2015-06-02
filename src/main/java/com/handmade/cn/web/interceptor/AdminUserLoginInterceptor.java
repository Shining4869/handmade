package com.handmade.cn.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.handmade.cn.conf.DataConfig;
import com.handmade.cn.utils.URLUtils;

/**
 * @author zgdcool
 * @version 2014年10月22日 下午3:59:23
 * 
 *          _ooOoo_ o8888888o 88" . "88 (| -_- |) O\ = /O ____/`---'\____ .' \\|
 *          |// `. / \\||| : |||// \ / _||||| -:- |||||- \ | | \\\ - /// | | |
 *          \_| ''\---/'' | | \ .-\__ `-` ___/-. / ___`. .' /--.--\ `. . __ .""
 *          '< `.___\_<|>_/___.' >'"". | | : `- \`.;`\ _ /`;.`/ - ` : | | \ \
 *          `-. \_ __\ /__ _/ .-` / /
 *          ======`-.____`-.___\_____/___.-`____.-'====== `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 佛祖保佑 永无BUG 佛曰:
 *          写字楼里写字间，写字间里程序员； 程序人员写程序，又拿程序换酒钱。 酒醒只在网上坐，酒醉还来网下眠； 酒醉酒醒日复日，网上网下年复年。
 *          但愿老死电脑间，不愿鞠躬老板前； 奔驰宝马贵者趣，公交自行程序员。 别人笑我忒疯癫，我笑自己命太贱； 不见满街漂亮妹，哪个归得程序员？
 */
public class AdminUserLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		if (isNeedLogin(handler, URLUtils.getCurrentUrl(request)) && session.getAttribute(DataConfig.SESSION_ADMIN_USER) == null) {
			response.sendRedirect(request.getContextPath()
					+ "/admin/login.html");
			return false;
		}

		return true;
	}

	public Boolean isNeedLogin(Object obj, String s) {
		if (ClassUtils.isAssignableValue(HandlerMethod.class, obj)) {
			HandlerMethod hm = (HandlerMethod) obj;
			if (AnnotationUtils.findAnnotation(hm.getBeanType(),
					AdminUserLogin.class) != null
					|| hm.getMethodAnnotation(AdminUserLogin.class) != null) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
}
