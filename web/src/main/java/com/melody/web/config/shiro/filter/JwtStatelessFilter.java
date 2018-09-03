package com.melody.web.config.shiro.filter;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.melody.result.ErrorCode;
import com.melody.result.JsonApi;
import com.melody.web.config.shiro.JwtStatelessToken;
import com.melody.web.config.shiro.JwtUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt无状态过滤器
 *
 * @author konghang
 */
public class JwtStatelessFilter extends RestPathMatchingFilter {

    private final String OPTIONS = "options";
    private final String NULL = "null";
    private final String AUTHORIZATION = "Authorization";

    /**
     * isAccessAllowed 和下边的 onAccessDenied 在 RestPathMatchingFilter 中的 onPreHandle 方法中被调用
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        return subject.isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String method = httpRequest.getMethod();
        // 过滤跨域的options请求
        if (OPTIONS.equalsIgnoreCase(method)) {
            return true;
        }
        Subject subject = getSubject(request, response);
        // 获取Authorization字段
        String authorization = httpRequest.getHeader(AUTHORIZATION);
        if (authorization != null && !NULL.equalsIgnoreCase(authorization)) {
            try {
                // 提交给realm进行登入，如果错误他会抛出异常并被捕获
                String username = JwtUtil.getUsername(authorization);
                JwtStatelessToken token = new JwtStatelessToken(username, authorization);
                subject.login(token);
                return true;
            } catch (UnknownAccountException uae) {
                authorizationFail(response, ErrorCode.USER_UNKNOWN_ACCOUNT);
            } catch (IncorrectCredentialsException ice) {
                String message = ice.getMessage();
                if (ErrorCode.TOKEN_SIGNATURE_ERROR.getErrorMsg().equals(message)) {
                    authorizationFail(response, ErrorCode.TOKEN_SIGNATURE_ERROR);
                } else if (ErrorCode.TOKEN_EXPIRED.getErrorMsg().equals(message)) {
                    authorizationFail(response, ErrorCode.TOKEN_EXPIRED);
                } else if (ErrorCode.TOKEN_INVALID_CLAIM.getErrorMsg().equals(message)) {
                    authorizationFail(response, ErrorCode.TOKEN_INVALID_CLAIM);
                } else if (ErrorCode.TOKEN_ERROR.getErrorMsg().equals(message)) {
                    authorizationFail(response, ErrorCode.TOKEN_ERROR);
                } else {
                    authorizationFail(response, ErrorCode.USER_ERROR_PASSWORD);
                }
            } catch (LockedAccountException lae) {
                authorizationFail(response, ErrorCode.USER_ACCOUNT_LOCKED);
            } catch (ExcessiveAttemptsException eae) {
                authorizationFail(response, ErrorCode.USER_ERROR_PASSWORD_MUCH);
            } catch (AuthenticationException ae) {
                authorizationFail(response, ErrorCode.USER_ERROR_ACCOUNT_OR_PASSWORD);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                authorizationFail(response, ErrorCode.USER_UNAUTHORIZED);
            }
        } else {
            authorizationFail(response, ErrorCode.USER_UNAUTHORIZED);
        }
        return false;
    }

    /**
     * 将请求返回到 /401
     */
    private void authorizationFail(ServletResponse response, ErrorCode errorCode) throws Exception {
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("application/json;charset=UTF-8");
        servletResponse.setHeader("Access-Control-Allow-Origin", "*");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(JsonApi.isFail(errorCode)));
    }
}
