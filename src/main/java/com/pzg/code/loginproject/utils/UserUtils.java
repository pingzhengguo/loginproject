package com.pzg.code.loginproject.utils;



import com.pzg.code.loginproject.vo.UserVo;

import javax.servlet.http.HttpSession;


public class UserUtils {

    public static UserVo getLoginUser(HttpSession session) {
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser == null) {
            return null;
        } else {
            return (UserVo) loginUser;
        }
    }


}
