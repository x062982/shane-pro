package ${package.Service};

import ${package.Entity}.${entity};
<#if cfg.isExtend>import ${superServiceClassPackage};
<#else>

import java.util.List;

</#if>
/**
* <p>
* ${table.comment!} 服务类
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#elseif cfg.isExtend>public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

}
<#else>
public interface ${table.serviceName} {

    /**
    * 插入一条记录进${table.comment!}
    * @param entity    ${table.comment!}实体对象
    * @return 返回插入的主键id 0：失败；> 0：成功
    */
    @PostMapping("/save-${cfg.tableLevel2}")
    Long save${cfg.tableLevel2?cap_first}(@RequestBody ${entity} entity);

    /**
    *  根据id更新${table.comment!}的信息
    * @param entity    ${table.comment!}实体对象
    * @return 0：失败；1：成功
    */
    @PostMapping("/update-${cfg.tableLevel2}")
    Integer upd${cfg.tableLevel2?cap_first}ById(@RequestBody ${entity} entity);

    /**
    *  根据id删除一个${table.comment!}的信息
    * @param id    编号
    * @return 0：失败；1：成功
    */
    @PostMapping("/delete-${cfg.tableLevel2}-by-id")
    Integer del${cfg.tableLevel2?cap_first}ById(@RequestParam Long id);

    /**
    * 根据id查询一个${table.comment!}的信息
    * @param id 编号
    * @return 一个${table.comment!}的信息
    */
    @GetMapping("/get-${cfg.tableLevel2}-by-id")
    ${entity} get${cfg.tableLevel2?cap_first}ById(@RequestParam Long id);

    /**
    * 获取所有的${table.comment!}的信息
    * @return 所有的${table.comment!}的信息
    */
    @GetMapping("/get-all-${cfg.tableLevel2}")
    List<${entity}> get${cfg.tableLevel2?cap_first}s();

}
</#if>