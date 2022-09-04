package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.LoginDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserService;
import com.heima.utils.common.AppJwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class ApUserServiceImpl extends ServiceImpl<ApUserMapper, ApUser> implements ApUserService {

    @Override
    public ResponseResult login(LoginDto loginDto) {
        //正常登录需要用户名密码
        if(!StringUtils.isEmpty(loginDto.getPhone()) && !StringUtils.isEmpty(loginDto.getPassword())){
            ApUser apUser = getOne(Wrappers.<ApUser>lambdaQuery().eq(ApUser::getPhone, loginDto.getPhone()));
            //用户名错误
            if(apUser==null){
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR,"用户名或密码错误");
            }
            //对比密码
            String password=loginDto.getPassword()+ apUser.getSalt();
            //密码错误
            if(!apUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR,"用户名或密码错误");
            }
            //返回jwt
            String token = AppJwtUtil.getToken(apUser.getId().longValue());
            Map<String,Object> map=new HashMap<>();
            map.put("token",token);
            //设置返回密码为空
            apUser.setPassword("");
            //设置盐为空
            apUser.setSalt("");
            map.put("user",apUser);
            return ResponseResult.okResult(map);
        }else {
            //游客登录
            String token = AppJwtUtil.getToken(0L);
            Map<String,Object> map=new HashMap<>();
            map.put("token",token);
            return ResponseResult.okResult(map);
        }
    }
}
