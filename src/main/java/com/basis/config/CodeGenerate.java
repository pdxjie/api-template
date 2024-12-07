package com.basis.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;
import java.sql.Types;

/**
 * @Author: IT 派同学
 * @Date: 2024/12/7
 * @Description: MP 代码生成器
 */
public class CodeGenerate {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/init?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8",
                        "root", "root")
                .globalConfig(builder -> builder
                        .author("IT 派同学")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                        .commentDate("yyyy-MM-dd")
                        .enableSwagger()
                )
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .strategyConfig(builder ->
                        builder.addInclude("xx_permission", "xx_role", "xx_role_permission", "xx_user", "xx_user_role") // 设置需要生成的表名
                                .addTablePrefix("xx_") // 设置过滤表前缀
                )
                .packageConfig(builder -> builder
                        .parent("com.basis")
                        .entity("com.basis.model.entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                )
                .strategyConfig(builder -> builder
                        .entityBuilder()
                        .enableLombok()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
