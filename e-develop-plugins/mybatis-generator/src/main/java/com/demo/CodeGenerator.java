package com.demo;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.github.yulichang.base.MPJBaseService;
import com.github.yulichang.base.MPJBaseServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 * 状态码发电机
 *
 * @author zhanghui
 * @date 2023/10/25 12:28:04
 */
public class CodeGenerator {
    public static void main(String[] args) {
        System.out.println("------mybatis-plus代码生成器------");
        System.out.println("------version：3.5.2------");

        // 数据库地址 jdbc:postgresql://xxxx:5432/postgres", "postgres", "postgres"
//        String mysql = "jdbc:mysql://127.0.0.1:3306/liteflow-2.12.3?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
        String mysql = "jdbc:mysql://ipv4.yuze4.me:39093/aws_db?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
        String postgresql = "jdbc:postgresql://localhost:5432/liteflow";
        // 数据库名

        // 数据库用户名
        String dbUsername = "root";
        // 数据库密码
//        String dbPassword = "root";
        String dbPassword = "PjuwkHUjG7yWg304boEH";

        System.err.println(System.getProperty("user.dir") + "/src/main/java");
        FastAutoGenerator.create(mysql, dbUsername, dbPassword)

                // 全局配置
                .globalConfig(builder -> builder
                        // 禁止打开输出目录
                        .disableOpenDir()
                        // 设置输出目录
                        .outputDir(System.getProperty("user.dir") + "/src/main/java")
                        // 设置作者信息
                        .author("ZhangHui")
                        // 开启 swagger 模式
                        .enableSwagger()
                        // 实体类中时间策略
                        .dateType(DateType.ONLY_DATE)
                        // 生成日期
                        .commentDate("yyyy-MM-dd")
                )

                // 包配置
                .packageConfig(builder -> builder
                        // 父包名
                        .parent("com.liuli")
                        // 父包模块名 注释即为无
                        .moduleName(scanner("模块名"))
                        // 将Mapper xml生成到resources目录下
                        .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir")+"/src/main/resources/mappers"))
                )

                // 策略配置
                .strategyConfig((scanner, builder) -> {
                    // 需要生成得表
                    builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                            // 开启大写命名
                            // .enableCapitalMode()
                            // 开启跳过视图
                            // .enableSkipView()
                            // 增加过滤表前缀
                            .addTablePrefix("pl_")
                            // 增加过滤表后缀
//                            .addTableSuffix("_n")
                            // 增加过滤字段前缀
//                            .addFieldPrefix("")
                            // 增加过滤字段后缀
//                            .addFieldSuffix("")

                            // 实体策略配置
                            .entityBuilder()
                            // 禁用生成 serialVersionUID
//                            .disableSerialVersionUID()
                            // 开启链式模型
                            // .enableChainModel()
                            // 开启 lombok 模型
//                            .enableLombok()
                            // 开启生成实体时生成字段注解
                            .enableTableFieldAnnotation()
                            // 乐观锁字段名(数据库)
//                            .versionColumnName("version")
                            // 乐观锁属性名(实体)
//                            .versionPropertyName("version")
                            // 逻辑删除字段名(数据库)
//                            .logicDeleteColumnName("deleted")
                            // 逻辑删除属性名(实体)
//                            .logicDeletePropertyName("deleted")
                            // 	数据库表映射到实体的命名策略 -- 下划线转驼峰命名
//                            .naming(NamingStrategy.underline_to_camel)
                            // 数据库表字段映射到实体的命名策略 -- 下划线转驼峰命名
//                            .columnNaming(NamingStrategy.underline_to_camel)

                            // 阿里巴巴开发规范之创建时间、更新时间 交由mybatis-plus处理，如若交给数据库处理，则取消此设置
                            // .addTableFills(new Column("create_time", FieldFill.INSERT), new Column("update_time", FieldFill.INSERT_UPDATE))
                            // mapper 策略配置
                            .mapperBuilder()
                            // 设置父类
                            .superClass("com.github.yulichang.base.MPJBaseMapper")
                            // 格式化 mapper 文件名称
                            .formatMapperFileName("%sMapper")
                            // 格式化 xml 实现类文件名称
                            .formatXmlFileName("%sMapper")
                            // 开启 @Mapper 注解
                            .enableMapperAnnotation()
                            // 生成通用的resultMap
                            .enableBaseResultMap()

                            // service 策略配置
                            .serviceBuilder()
                            .superServiceClass(MPJBaseService.class)
                            .superServiceImplClass(MPJBaseServiceImpl.class)
                            // 格式化 service 接口文件名称
//                            .formatServiceFileName("%sService")
                            // 格式化 service 实现类文件名称
//                            .formatServiceImplFileName("%sServiceImp")

                            // controller 策略配置
                            .controllerBuilder()
                            //公共从controller父类
//                            .superClass("")
                            // 格式化文件名称
                            .formatFileName("%sController")
                            //开启驼峰转连字符
                            .enableHyphenStyle()
                            ;
                            // 开启生成@RestController 控制器
//                            .enableRestStyle();
                })
                // 以下为解决实体类data注解，若不需要则可以注释
                // ------------------开始-------------------
//                .templateConfig(builder -> {
//                    // 实体类使用我们自定义模板 -- 模板位置
//                    builder.entity("templates/myEntity.java");
//                })
                .templateEngine(new VelocityTemplateEngine())
                // ------------------结束-------------------
                // 开始处理
                .execute();
    }
    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StrUtil.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}

