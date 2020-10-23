package com.shanezhou.pro.service.impl;

import com.shanezhou.pro.entity.AccountVO;
import com.shanezhou.pro.entity.RoleVO;
import com.shanezhou.pro.entity.SecurityUser;
import com.shanezhou.pro.entity.UserDTO;
import com.shanezhou.pro.service.IAuthService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.security.auth.login.AccountExpiredException;
import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.CredentialExpiredException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ShaneZhou
 * @since 2020/9/29 周二
 */
@Component("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    //@Autowired
    //private IAuthService authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<UserDTO> userList;

    @PostConstruct
    public void init() {
        String password = passwordEncoder.encode("123456");

        List<String> roleList = new ArrayList<>();
        Collections.addAll(roleList, "ADMIN");
        List<String> roleTest = new ArrayList<String>(){
            {
                add("TEST");
            }
        };
        userList = new ArrayList<>();
        userList.add(new UserDTO(1L, "shane", password, 1, roleList));
        userList.add(new UserDTO(2L, "test", password, 1, roleTest));
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDTO> findUserList = userList.stream()
                .filter(item -> item.getUsername().equals(username))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(findUserList)) {
            throw new UsernameNotFoundException("用户账户或者密码错误");
        }
        SecurityUser securityUser = new SecurityUser(findUserList.get(0));
        if (!securityUser.isEnabled()) {
            throw new DisabledException("账户未启用");
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException("账户被锁定");
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException("账户已过期");
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialExpiredException("账户验证过期");
        }
        return securityUser;


        //AccountVO accountVO = authService.getAccountByName(username);
        //RoleVO role = authService.getRoleById(accountVO.getRoleId());
        //List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //authorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
        //// 线上环境应该通过用户名查询数据库获取加密后的密码
        //String password = passwordEncoder.encode(accountVO.getPassword().toString());
        //
        //return new User(username, password, authorities);


    }

}
