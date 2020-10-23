package com.shanezhou.pro.account.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanezhou.pro.account.entity.AccountEntity;
import com.shanezhou.pro.account.mapper.AccountMapper;
import com.shanezhou.pro.account.service.IAccountService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author ZhouWX
 * @since 2020-09-30
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountEntity> implements IAccountService {

}
