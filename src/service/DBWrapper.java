package service;

import dal.MYSQLDriver;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Kasper on 17/10/2016.
 */
public class DBWrapper {
    private MYSQLDriver dbDriver;

    public DBWrapper(MYSQLDriver dbDriver){
        this.dbDriver = dbDriver;

    }

    public ResultSet getRecords (String table, Map params, Map joins, int limit){
        String sql = "SELECT * FROM " + table;

        if(joins != null) {
            sql = joinOn(joins, sql);
        }

        sql = buildWhere(params, sql);

        return dbDriver.executeSQL(sql);

    }

    private String buildWhere(Map<String, String> params, String sql){
        StringBuilder builder = new StringBuilder(sql);

        if(!params.isEmpty()){
            builder.append(" WHERE ");
            //for (Map.Entry<String, String> entry : params.entrySet())
            Iterator iterator = params.entrySet().iterator();
            while(iterator.hasNext())
            {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
                builder.append(entry.getKey());
                builder.append(" = ");
                builder.append(entry.getValue());

                //Tjek om der er flere entries i params
                if(iterator.hasNext()){
                    builder.append(" AND ");
                }
            }
            builder.append(";");
        }
        return builder.toString();
    }

    private String joinOn(Map<String, String> joins, String sql){
        StringBuilder builder = new StringBuilder(sql);

        if(!joins.isEmpty()){
            Iterator iterator = joins.entrySet().iterator();
            while(iterator.hasNext())
            {
                builder.append(" JOIN ");
                Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
                builder.append(entry.getKey());
                builder.append(" ON ");
                builder.append(entry.getValue());
            }
            builder.append(";");
        }

        return builder.toString();
    }


}
