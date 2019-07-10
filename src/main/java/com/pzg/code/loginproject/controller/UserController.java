package com.pzg.code.loginproject.controller;

import com.pzg.code.loginproject.entity.User;
import com.pzg.code.loginproject.mapper.UserMapper;
import com.pzg.code.loginproject.utils.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName :  UserController
 * @Author : PZG
 * @Date : 2019-07-10   17:50
 * @Description :
 */
@Api(tags = {"UserController"})
@RestController
@RequestMapping("/userController")
public class UserController {
    @Autowired
    private UserMapper userMapper;


    @ApiOperation(value = "受登录的限制", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getAllTestUser", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getAllTestUser() {
        List<User> users = userMapper.selectAll();
        return ResultInfo.success().build(users);
    }


    @ApiOperation(value = "不受登录的限制", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/anon/getAllTestUser", method = RequestMethod.GET)
    @ResponseBody
    public ResultInfo getAnonAllTestUser() {
        List<User> users = userMapper.selectAll();
        return ResultInfo.success().build(users);
    }
}
