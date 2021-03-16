package com.shanezhou.pro.navigation.service;

import com.shanezhou.pro.navigation.entity.NavigationSchemeEntity;

import java.util.List;

/**
* <p>
* 导航方案表 服务类
* </p>
*
* @author ZhouWX
* @since 2021-02-02
*/
public interface INavigationSchemeService {

    /**
    * 插入一条记录进导航方案表
    * @param entity    导航方案表实体对象
    * @return 返回插入的主键id 0：失败；> 0：成功
    */
    @PostMapping("/save-scheme")
    Long saveScheme(@RequestBody NavigationSchemeEntity entity);

    /**
    *  根据id更新导航方案表的信息
    * @param entity    导航方案表实体对象
    * @return 0：失败；1：成功
    */
    @PostMapping("/update-scheme")
    Integer updSchemeById(@RequestBody NavigationSchemeEntity entity);

    /**
    *  根据id删除一个导航方案表的信息
    * @param id    编号
    * @return 0：失败；1：成功
    */
    @PostMapping("/delete-scheme-by-id")
    Integer delSchemeById(@RequestParam Long id);

    /**
    * 根据id查询一个导航方案表的信息
    * @param id 编号
    * @return 一个导航方案表的信息
    */
    @GetMapping("/get-scheme-by-id")
    NavigationSchemeEntity getSchemeById(@RequestParam Long id);

    /**
    * 获取所有的导航方案表的信息
    * @return 所有的导航方案表的信息
    */
    @GetMapping("/get-all-scheme")
    List<NavigationSchemeEntity> getSchemes();

}
