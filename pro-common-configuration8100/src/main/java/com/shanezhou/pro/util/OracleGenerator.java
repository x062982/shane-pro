package com.shanezhou.pro.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * @author ZhouWX
 * @createDate 2020/6/23
 */
public class OracleGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append(tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        // scanner.close();
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 可以输入多个表名，多个英文逗号分割，建议是输入一个
        String tableName = scanner("表名分三级,每级之间用“_”间隔\n" +
                "第一级为前缀，第二级为表类，第三级为具体实现\n" +
                "请输入表名：（示例：AAA_BBB_CCC）").toUpperCase();
        if (!tableName.contains("_") || tableName.endsWith("_")) {
            throw new IllegalArgumentException("表名不符合规则！");
        }

        String[] nameStr = tableName.toLowerCase().split("_");
        // 包名
        String packageName = "." + nameStr[1];
        // 表前缀
        String tablePrefix = nameStr[0]+ "_";
        // project
        String project = nameStr[0];
        // 名称 等级1
        String tableLevel1 = nameStr[1];
        // 名称 等级2
        String tableLevel2 = tableLevel1;
        // 序列名
        String seqName = "SEQ_" + tableName.replace(tablePrefix.toUpperCase(), "") + "_ID";
        if (nameStr.length > 2) {
            tableLevel2 = nameStr[2].toLowerCase();
        }
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("ZhouWX");
        gc.setEntityName("%sEntity");
        gc.setIdType(IdType.INPUT);
        gc.setOpen(false);
        gc.setFileOverride(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:oracle:thin:@10.0.14.20:1521:stdb");
        dsc.setDbType(DbType.ORACLE);
        dsc.setDriverName("oracle.jdbc.driver.OracleDriver");
        dsc.setUsername("foxdb");
        dsc.setPassword("foxdb");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // pc.setModuleName("station");
        pc.setParent("com.shanezhou.pro" + packageName);
        //pc.setEntity("entity");
        pc.setServiceImpl(pc.getService());
        mpg.setPackageInfo(pc);

        // 自定义属性配置
        String finalTableLevel = tableLevel2;
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
                Map<String, Object> map = new HashMap<>(6);
                if (gc.getIdType().equals(IdType.INPUT)) {
                    // 加入序列名
                    map.put("seqName", seqName);
                    map.put("tableLevel1", tableLevel1);
                    map.put("tableLevel2", finalTableLevel);
                    map.put("isExtend", false);
                    map.put("project", project);
                    // 包含的值：{ignore，notNull}
                    map.put("updateStrategy", "notNull");
                }
                this.setMap(map);
            }
        };

        List<FileOutConfig> focList = new ArrayList<>();
        //focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
        //    @Override
        //    public String outputFile(TableInfo tableInfo) {
        //        // 自定义输入文件名称
        //        return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
        //                + "/" + tableInfo.getEntityName().replace("Entity", "") +
        //                "Mapper" + StringPool.DOT_XML;
        //    }
        //});
        cfg.setFileOutConfigList(focList);
        // 自定义输入模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("freemarker/templates/entity.java");
        templateConfig.setMapper("freemarker/templates/mapper.java");
        templateConfig.setService("freemarker/templates/service.java");
        templateConfig.setServiceImpl("freemarker/templates/serviceImpl.java");
        templateConfig.setXml(null);
        //templateConfig.setController(null);

        mpg.setCfg(cfg);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        //String[] tableName = scanner("表名，多个英文逗号分割").split(",");
        strategy.setInclude(tableName);
        strategy.setTablePrefix(tablePrefix);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperServiceClass("");
        strategy.setSuperServiceImplClass("");
        strategy.setEntityTableFieldAnnotationEnable(true);
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        ConfigBuilder config = new ConfigBuilder(pc, dsc,
                strategy, templateConfig, gc);
        mpg.setConfig(config);
        mpg.execute();
    }

}
