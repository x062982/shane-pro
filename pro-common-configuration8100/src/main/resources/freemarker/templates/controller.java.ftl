package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>import org.springframework.web.bind.annotation.RestController;
<#else>import org.springframework.stereotype.Controller;
</#if><#if superControllerClassPackage??>import ${superControllerClassPackage};
</#if>
/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>@RestController
<#else>@Controller
</#if>@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else><#if superControllerClass??>public class ${table.controllerName} extends ${superControllerClass} {
<#else>public class ${table.controllerName} {

    @Autowired
    private ${table.serviceName} ${cfg.tableLevel2};

    @PostMapping("/${cfg.tableLevel2}")
    public void create${cfg.tableLevel2}(@RequestBody @Valid AccountVO vo) {
        boolean isSave = ${cfg.tableLevel2}.save(convertEntity(vo));
        if (!isSave) {
            throw new APIException("创建账户失败！");
        }
    }
</#if>
}
</#if>