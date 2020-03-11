package com.lyscharlie.biz.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyscharlie.biz.entity.UserDO;
import com.lyscharlie.biz.mapper.UserMapper;
import com.lyscharlie.biz.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

}
