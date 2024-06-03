package ai.chat2db.plugin.mysql.value;

import ai.chat2db.plugin.mysql.value.factory.MysqlValueProcessorFactory;
import ai.chat2db.spi.jdbc.DefaultValueProcessor;
import ai.chat2db.spi.model.JDBCDataValue;
import ai.chat2db.spi.model.SQLDataValue;

import java.util.Set;

/**
 * @author: zgq
 * @date: 2024年05月24日 21:02
 * <br>
 *  TODO:
 *      attribute: [zerofill] example tinyint[5] zerofill 34->00034
 */
public class MysqlValueProcessor extends DefaultValueProcessor {
    public static final Set<String> FUNCTION_SET = Set.of("now()", "default");

    @Override
    public String convertSQLValueByType(SQLDataValue dataValue) {
        if (FUNCTION_SET.contains(dataValue.getValue().toLowerCase())) {
            return dataValue.getValue();
        }
        return MysqlValueProcessorFactory.getValueProcessor(dataValue.getDateTypeName()).convertSQLValueByType(dataValue);
    }

    @Override
    public String convertJDBCValueByType(JDBCDataValue dataValue) {
        return MysqlValueProcessorFactory.getValueProcessor(dataValue.getType()).convertJDBCValueByType(dataValue);
    }

    @Override
    public String convertJDBCValueStrByType(JDBCDataValue dataValue) {
        return MysqlValueProcessorFactory.getValueProcessor(dataValue.getType()).convertJDBCValueStrByType(dataValue);
    }
}
