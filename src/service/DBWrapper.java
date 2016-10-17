package service;

import dal.MYSQLDriver;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Kasper on 17/10/2016.
 */
public class DBWrapper {
    private static MYSQLDriver dbDriver;

    public DBWrapper(MYSQLDriver dbDriver){
        this.dbDriver = dbDriver;

    }

    public static ResultSet getRecords (String table, Map params, int limit){
        String sql = "SELECT * FROM " + table;
        sql = buildWhere(params, sql);

        return dbDriver.executeSQL(sql);

    }

    private static String buildWhere(Map<String, String> params, String sql){
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



}
