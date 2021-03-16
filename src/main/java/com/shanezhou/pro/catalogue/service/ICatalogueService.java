package com.shanezhou.pro.catalogue.service;

import com.shanezhou.pro.catalogue.entity.CatalogueEntity;

import java.util.List;

/**
* <p>
* 目录表 服务类
* </p>
*
* @author ZhouWX
* @since 2021-02-02
*/
public interface ICatalogueService {

    /**
    * 插入一条记录进目录表
    * @param entity    目录表实体对象
    * @return 返回插入的主键id 0：失败；> 0：成功
    */
    @PostMapping("/save-catalogue")
    Long saveCatalogue(@RequestBody CatalogueEntity entity);

    /**
    *  根据id更新目录表的信息
    * @param entity    目录表实体对象
    * @return 0：失败；1：成功
    */
    @PostMapping("/update-catalogue")
    Integer updCatalogueById(@RequestBody CatalogueEntity entity);

    /**
    *  根据id删除一个目录表的信息
    * @param id    编号
    * @return 0：失败；1：成功
    */
    @PostMapping("/delete-catalogue-by-id")
    Integer delCatalogueById(@RequestParam Long id);

    /**
    * 根据id查询一个目录表的信息
    * @param id 编号
    * @return 一个目录表的信息
    */
    @GetMapping("/get-catalogue-by-id")
    CatalogueEntity getCatalogueById(@RequestParam Long id);

    /**
    * 获取所有的目录表的信息
    * @return 所有的目录表的信息
    */
    @GetMapping("/get-all-catalogue")
    List<CatalogueEntity> getCatalogues();

}
