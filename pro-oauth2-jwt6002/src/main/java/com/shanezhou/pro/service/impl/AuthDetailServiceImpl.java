package com.shanezhou.pro.service.impl;

import com.shanezhou.pro.domain.AccountVO;
import com.shanezhou.pro.domain.RoleVO;
import com.shanezhou.pro.domain.User;
import com.shanezhou.pro.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author ShaneZhou
 * @since 2020/10/9 周五
 */
@Component("authDetailServiceImpl")
public class AuthDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IAuthService authService;

    private List<User> userList;

    //@PostConstruct
    //public void initData() {
    //    String password = passwordEncoder.encode("123456");
    //    userList = new ArrayList<>();
    //    userList.add(new User("shane", password,
    //            AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
    //    userList.add(new User("shane1", password,
    //            AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
    //    userList.add(new User("shane2", password,
    //            AuthorityUtils.commaSeparatedStringToAuthorityList("client")));
    //}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountVO account = authService.getAccountByName(username);
        RoleVO role = authService.getRoleById(account.getRoleId());
        User user = new User(account.getUsername(),
                passwordEncoder.encode(account.getPassword().toString()),
                AuthorityUtils.commaSeparatedStringToAuthorityList(role.getRoleCode()));
        return user;

        //List<User> users = userList.stream()
        //        .filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
        //if (!CollectionUtils.isEmpty(users)) {
        //    return users.get(0);
        //} else {
        //    throw new UsernameNotFoundException("用户名或密码错误");
        //}

        //return null;
    }
}
