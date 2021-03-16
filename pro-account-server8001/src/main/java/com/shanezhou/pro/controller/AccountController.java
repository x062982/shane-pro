package com.shanezhou.pro.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shanezhou.pro.annotation.ApiIdempotent;
import com.shanezhou.pro.entity.AccountEntity;
import com.shanezhou.pro.entity.ResultVO;
import com.shanezhou.pro.entity.vo.AccountVO;
import com.shanezhou.pro.exception.APIException;
import com.shanezhou.pro.service.IAccountService;
import com.shanezhou.pro.service.IIdempotentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ShaneZhou
 * @since 2020/9/30 周三
 */
@RestController
@RequestMapping("/account")
@Api(tags = {"账户控制类"})
public class AccountController {

    @Autowired
    private IIdempotentService idempotentService;

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
        AccountEntity accountEntity = accountService.getById(id);
        return convertVO(accountEntity);
    }

    @GetMapping("/account/username")
    @ApiOperation(value = "根据用户名查询账户")
    @ApiImplicitParams(@ApiImplicitParam (name = "username", value = "用户名", dataType = "文本"))
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
    @ApiOperation(value = "查询所有的账户")
    public List<AccountVO> getAccounts(HttpServletRequest request) {
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
