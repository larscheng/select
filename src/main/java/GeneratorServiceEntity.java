/**
 * @author zhengql
 * @description
 * @className GeneratorServiceEntity
 * @create 2018年01月05日  9:05
 */

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 *     测试生成代码
 * </p>
 *
 */

public class GeneratorServiceEntity {

    public static void main(String[] args) {
        String packageName = "com.slxy.www";
        generateByTables(packageName, "select_topic");
    }

    private static void generateByTables(String packageName, String... tableNames){
        // 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)
                .setAuthor("zhengql")
                .setOutputDir(System.getProperty("user.dir") + "/src/test/java/")
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setEnableCache(false)
                .setFileOverride(true);

        //数据源配置
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/slxy-select?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";
        String drivername = "com.mysql.jdbc.Driver";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername(username)
                .setPassword(password)
                .setDriverName(drivername);

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                .setDbColumnUnderline(false)
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组



        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                ).execute();
    }
}

