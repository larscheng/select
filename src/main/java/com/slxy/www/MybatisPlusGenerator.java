//package com.slxy.www;
//
//import com.baomidou.mybatisplus.annotations.IdType;
//import com.baomidou.mybatisplus.generator.AutoGeneratorMy;
//import com.baomidou.mybatisplus.generator.ConfigGeneratorMy;
//
//public class MybatisPlusGenerator {
//
//    public static void main(String[] args) {
//        ConfigGeneratorMy cg = new ConfigGeneratorMy();
//
//        // 配置 MySQL 连接
//        cg.setDbUrl("jdbc:mysql://127.0.0.1:3306/slxy-select");
//        cg.setDbUser("root");
//        cg.setDbPassword("root");
//        cg.setDbDriverName("com.mysql.jdbc.Driver");
//
//        // 配置包名
//        String[] table = {"select_topic","select_subject","select_score_per","select_process_control","select_major","select_department","select_bug_log"};
//        cg.setTableNames(table);
//        cg.setBuliderModel(true);
//        cg.setResultMap(true);
//        cg.setFileOverride(true);
//        cg.setEntityPackage("com.slxy.www.domain.po");
//        cg.setMapperName("I%sMapper");
//        cg.setMapperPackage("com.slxy.www.dao");
//        cg.setServiceImplName("%sService");
//        cg.setServicePackage(null);
//        cg.setXmlPackage("com.slxy.www.dao");
//        cg.setServiceImplPackage("com.slxy.www.service");
//        cg.setControllerPackage("com.slxy.www.web");
//
//        // 配置表主键策略
//        cg.setIdType(IdType.AUTO);
//
//        // 配置保存路径
//        cg.setSaveDir(System.getProperty("user.dir") + "/src/test/java/");
//        // 其他参数请根据上面的参数说明自行配置，当所有配置完善后，运行AutoGenerator.run()方法生成Code
//        // 生成代码
//        AutoGeneratorMy.run(cg);
//    }
//}
