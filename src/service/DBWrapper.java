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

    public ResultSet getRecords (String table, String[] attributes, Map whereStmts, Map joinStmts, int limit){

        String sql = "SELECT ";

        sql = appendAttributes(sql, attributes, table);


        if(joinStmts != null ){
            sql = joinOn(joinStmts, sql);
        }

        if(whereStmts != null){
            sql = buildWhere(whereStmts, sql);
        }

        //lav lige en builder senere
        sql += ";";
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

        }

        return builder.toString();
    }

    private String appendAttributes(String sql, String[] attributes, String table) {
        StringBuilder builder = new StringBuilder(sql);

        if(attributes.length == 0) {

            builder.append(" * ");

        } else {
            for(int i=0;i<attributes.length;i++) {
                builder.append(attributes[i]);
                if (i != attributes.length -1){
                    builder.append(", ");
                }
            }
            builder.append(" FROM ");
            builder.append(table);

        }

        return builder.toString();
    }

}
