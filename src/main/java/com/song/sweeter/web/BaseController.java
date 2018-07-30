package com.song.sweeter.web;

import com.song.sweeter.comm.Const;
import com.song.sweeter.comm.dto.UserDto;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseController {

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    protected UserDto getUserDto() {
        return (UserDto) getSession().getAttribute(Const.SESSION_KEY_USER);
    }
}
