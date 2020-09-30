package com.shanezhou.pro.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanezhou.pro.entity.AccountEntity;
import com.shanezhou.pro.entity.ResultVO;
import com.shanezhou.pro.entity.vo.AccountVO;
import com.shanezhou.pro.exception.APIException;
import com.shanezhou.pro.service.IAccountService;
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

    public void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account")
    public ResultVO createAccount(@RequestBody @Valid AccountVO vo) {
        boolean isSave = accountService.save(convertEntity(vo));
        if (!isSave) {
            throw new APIException("创建账户失败！");
        }
        return new ResultVO("账户创建成功");
    }

    @GetMapping("/account/{id}")
    public AccountVO getAccountById(@PathVariable("id") Long id) {
        AccountEntity accountEntity = accountService.getById(id);
        return convertVO(accountEntity);
    }

    @GetMapping("/account/username")
    public AccountVO getAccountById(@RequestParam("username") String username) {
        AccountEntity accountEntity = accountService
                .getOne(new QueryWrapper<AccountEntity>()
                    .eq("USERNAME", username)
                    .eq("ROWNUM", 1));
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
            //vo.setPassword(entity.getPassword());
            vo.setRoleId(entity.getRoleId());
        }
        return vo;
    }

    public AccountEntity convertEntity(AccountVO vo) {
        AccountEntity entity = new AccountEntity();
        if (vo != null) {
            entity.setId(vo.getId());
            entity.setUsername(vo.getUsername());
            entity.setPassword(new String(vo.getPassword()));
            entity.setRoleId(vo.getRoleId());
        }
        return entity;
    }
}
