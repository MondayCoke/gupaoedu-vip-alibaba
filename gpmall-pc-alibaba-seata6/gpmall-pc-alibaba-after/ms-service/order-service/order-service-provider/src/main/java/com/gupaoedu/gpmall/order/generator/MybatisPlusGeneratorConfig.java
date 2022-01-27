package com.gupaoedu.gpmall.order.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * 咕泡学院，只为更好的你
 * 咕泡学院-Mic微信: mic4096
 * https://ke.gupaoedu.cn
 **/
public class MybatisPlusGeneratorConfig {

    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG=new DataSourceConfig
            .Builder("jdbc:mysql://192.168.8.132:3306/order?useUnicode=true&characterEncoding=utf-8&useSSL=false","root","123456")
            .dbQuery(new MySqlQuery())
            .schema("order")
            .typeConvert(new MySqlTypeConvert())
            .keyWordsHandler(new MySqlKeyWordsHandler());
    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir");
        String outputDir = projectPath + "/ms-service/order-service/order-service-provider/src/main/java";
        // 代码生成器
        FastAutoGenerator.create(DATA_SOURCE_CONFIG).globalConfig((scanner, builder) -> {
            builder.fileOverride().outputDir(outputDir)//指定输出目录
                    .author("mic4096")//作者名
                    .dateType(DateType.TIME_PACK)//时间策略，DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                    .commentDate("yyyy-MM-dd")//注释日期，默认值: yyyy-MM-dd
                    .build();
        }).packageConfig((scanner, builder) -> {
            builder.parent("com.gupaoedu.gpmall.order")
                    .entity("dal.model")
                    .mapper("dal.mapper")
                    .build();
        }).templateConfig((scanner, builder) -> {
            //不需要生成xml、controller、service、serviceImpl
            builder.mapperXml(null).controller(null).service(null).serviceImpl(null).build();
        }).strategyConfig(builder -> {
            builder.enableCapitalMode()
                    .enableSkipView().addInclude(scanner("tcc_order").split(","))
                    .entityBuilder()
                    .enableLombok()
                    .naming(NamingStrategy.underline_to_camel)
                    .columnNaming(NamingStrategy.underline_to_camel)
                    .superClass("com.baomidou.mybatisplus.extension.activerecord.Model")
                    .build();
        }).templateEngine(new FreemarkerTemplateEngine()).execute();
    }
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
