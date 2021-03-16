package com.shanezhou.pro.catalogue.service;

import com.shanezhou.pro.catalogue.entity.CataloguePagesEntity;

import java.util.List;

/**
* <p>
* 目录资源表 服务类
* </p>
*
* @author ZhouWX
* @since 2021-02-02
*/
public interface ICataloguePagesService {

    /**
    * 插入一条记录进目录资源表
    * @param entity    目录资源表实体对象
    * @return 返回插入的主键id 0：失败；> 0：成功
    */
    @PostMapping("/save-pages")
    Long savePages(@RequestBody CataloguePagesEntity entity);

    /**
    *  根据id更新目录资源表的信息
    * @param entity    目录资源表实体对象
    * @return 0：失败；1：成功
    */
    @PostMapping("/update-pages")
    Integer updPagesById(@RequestBody CataloguePagesEntity entity);

    /**
    *  根据id删除一个目录资源表的信息
    * @param id    编号
    * @return 0：失败；1：成功
    */
    @PostMapping("/delete-pages-by-id")
    Integer delPagesById(@RequestParam Long id);

    /**
    * 根据id查询一个目录资源表的信息
    * @param id 编号
    * @return 一个目录资源表的信息
    */
    @GetMapping("/get-pages-by-id")
    CataloguePagesEntity getPagesById(@RequestParam Long id);

    /**
    * 获取所有的目录资源表的信息
    * @return 所有的目录资源表的信息
    */
    @GetMapping("/get-all-pages")
    List<CataloguePagesEntity> getPagess();

}
