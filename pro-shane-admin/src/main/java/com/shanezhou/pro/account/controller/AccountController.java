package com.shanezhou.pro.account.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanezhou.pro.account.entity.AccountEntity;
import com.shanezhou.pro.account.entity.vo.AccountVO;
import com.shanezhou.pro.account.service.IAccountService;
import com.shanezhou.pro.exception.APIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ShaneZhou
 * @since 2020/9/30 周三
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/account")
    public void createAccount(@RequestBody @Valid AccountVO vo) {
        boolean isSave = accountService.save(convertEntity(vo));
        if (!isSave) {
            throw new APIException("创建账户失败！");
        }
    }

    @GetMapping("/account/{id}")
    public AccountVO getAccountById(@PathVariable("id") Long id) {
        AccountEntity accountEntity;
        accountEntity = accountService.getById(id);
        return convertVO(accountEntity);
    }

    @GetMapping("/account/username")
    public AccountVO getAccountByName(@RequestParam("username") String username) {
        AccountEntity accountEntity = accountService
                .getOne(new QueryWrapper<AccountEntity>()
                    .eq("USERNAME", username)
                    .eq("ROWNUM", 1));
        if (accountEntity == null) {
            throw new APIException("用户名：" + username + "不存在");
        }
        return convertVO(accountEntity);
    }

    @GetMapping("/accounts")
    public List<AccountVO> getAccounts() {
        List<AccountEntity> accountEntities = accountService.list(null);
        List<AccountVO> accountVOs = new ArrayList<>();
        accountEntities.forEach(entity -> {
            accountVOs.add(convertVO(entity));
        });
        return accountVOs;
    }


    public AccountVO convertVO(AccountEntity entity) {
        AccountVO vo = new AccountVO();
        if (entity != null) {
            vo.setId(entity.getId());
            vo.setUsername(entity.getUsername());
            vo.setPassword(entity.getPassword().toCharArray());
            //vo.setRoleId(entity.getRoleId());
        }
        return vo;
    }

    public AccountEntity convertEntity(AccountVO vo) {
        AccountEntity entity = new AccountEntity();
        if (vo != null) {
            entity.setId(vo.getId());
            entity.setUsername(vo.getUsername());
            entity.setPassword(new String(vo.getPassword()));
            //entity.setRoleId(vo.getRoleId());
        }
        return entity;
    }
}
