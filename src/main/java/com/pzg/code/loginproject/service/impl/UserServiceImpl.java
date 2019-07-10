package com.pzg.code.loginproject.service.impl;


import com.pzg.code.loginproject.entity.Authority;
import com.pzg.code.loginproject.entity.Organization;
import com.pzg.code.loginproject.entity.Role;
import com.pzg.code.loginproject.entity.User;
import com.pzg.code.loginproject.mapper.AuthorityMapper;
import com.pzg.code.loginproject.mapper.OrganizationMapper;
import com.pzg.code.loginproject.mapper.RoleMapper;
import com.pzg.code.loginproject.mapper.UserMapper;
import com.pzg.code.loginproject.service.UserService;
import com.pzg.code.loginproject.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName :  UserServiceImpl
 * @Author : PZG
 * @Date : 2019-07-10   13:54
 * @Description :
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public UserVo getLoginUserVo(String userName) {
        User user = new User();
        user.setUserName(userName);
        user = userMapper.selectOne(user);
        Organization organization = new Organization();
        organization.setOgId(user.getOgId());
        organization = organizationMapper.selectOne(organization);
        Role role = new Role();
        role.setRoleId(user.getRoleId());
        role = roleMapper.selectOne(role);
        String authId = role.getAuthId();
        Authority authority = new Authority();
        authority.setAuthId(authId);
        List<Authority> authorityList = authorityMapper.select(authority);
        UserVo userVo = new UserVo();
        userVo.setRole(role);
        userVo.setUser(user);
        userVo.setOrganization(organization);
        userVo.setAuthorityList(authorityList);
        return userVo;
    }

    @Override
    public void outLogin(HttpServletRequest request, HttpServletResponse response) {
        // 获取Cookies数组
        Cookie[] cookies = request.getCookies();
        // 迭代查找并清除Cookie
        for (Cookie cookie : cookies) {
            if ("access_token".equals(cookie.getName())) {
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
}
