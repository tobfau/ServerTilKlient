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

    public  DBWrapper(MYSQLDriver dbDriver){
        this.dbDriver = dbDriver;

    }

    public ResultSet getRecords (String table, Map params, int limit){
        String sql = "SELECT * FROM " + table;


    }

    private String buildWhere(Map<String, String> params, String sql){
        StringBuilder builder = new StringBuilder(sql);

        if(!params.isEmpty()){
            builder.append("WHERE ");
            //for (Map.Entry<String, String> entry : params.entrySet())
            Iterator iterator = params.entrySet().iterator();


            while(iterator.hasNext())
            {
                Map.Entry<String, String> entry = iterator.next();
                builder.append(iterator.next().
                builder.append(" = ");
                builder.append(entry.getValue());
                if(params.entrySet().)
                builder.append(" AND ");
            }
            builder.append(";");
        }
        return
    }



}
