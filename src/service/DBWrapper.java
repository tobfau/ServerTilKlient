package service;

import dal.MYSQLDriver;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Kasper on 17/10/2016.
 */
public class DBWrapper {
    //Test variabler
    private MYSQLDriver dbDriver;

    //Test constructor
    public DBWrapper(MYSQLDriver dbDriver){
        this.dbDriver = dbDriver;

    }

    //Main-metode til at teste metoder
    public static void main(String[] args) {
        MYSQLDriver dbDriver = new MYSQLDriver();
        DBWrapper dbWrapper = new DBWrapper(dbDriver);

        HashMap values = new HashMap();
        values.put("cbs_mail", "lol@cbs.dk");
        values.put("password", "1234");
        values.put("type", "Student");
        dbWrapper.insertIntoRecords("user", values);

    }


    /**
     *
     * En generisk metode der bygger en SELECT String op ud fra den data, der ønskes hentet.
     * Herved kan vi nøjes med at kalde denne ene metode, der bygger alle SELECT strenge op.
     *
     * @param table Tabellen data skal hentes fra.
     * @param attributes Et String Array med de attributter fra tabellen, der ønskes returneret. Indsend tom array, hvis hele tabellen ønskes.
     * @param whereStmts Et Map<K,V> der indeholder et selvvalgt antal set 'Keys' med specificeret 'Values' - "WHERE key = value". Map tillader flere specifikationer. Ønskes ingen WHERE, indsæt "null".
     * @param joinStmts Et Map<K,V> der indeholder et selvvalgt antal set 'Keys' med specificeret 'Values' - "JOIN key ON value". Map tillader flere joins. Ønkes ingen JOINS, indsæt "null"
     * @param limit En limitation, hvis der kun ønskes en bestemt mængde data returneret. IKKE håndteret endnu, indsæt derfor 0.
     * @return Returnerer et ResultSet med ønsket data.
     */
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

    /**
     * Metoden bruges kun af getRecords() til at bygge første del af SQL-statementet op.
     * Er der ingen specifikke attributter der ønskes returneret, returneres alle.
     * Ellers bygger den attributterne ind i SQL-Statementet og returnerer denne.
     *
     * @param sql Modtager det SQL-statement der er igang med at blive bygget op.
     * @param attributes Hvilke attributter i tabellen der ønskes returneret.
     * @param table Hvilken tabel der hentes data fra.
     * @return Returnerer en String med et SQL Statement, hvor der er bygget på til FROM "table".
     */
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


        }

        builder.append(" FROM ");
        builder.append(table);

        return builder.toString();
    }


    /**
     * Metoden bruges kun af getRecords() til at bygge anden del af SQL-statementet op.
     * Er der ingen joins ønsket, springes denne over. Ellers bygger den en JOIN med på SQL-statementet.
     *
     * @param joins Et Map<K,V> der indeholder et selvvalgt antal set 'Keys' med specificeret 'Values' - "JOIN key ON value". Map tillader flere joins. Ønkes ingen JOINS, indsæt "null"
     * @param sql Modtager det SQL-statement der er igang med at blive bygget op.
     * @return Returnerer en String med et SQL Statement, hvor der er bygget JOIN clauses på.
     */
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


    /**
     * Metoden bruges kun af getRecords() til at bygge tredje del af SQL-statementet op.
     * Skal der ikke specificeres et WHERE clause, springes denne over.
     * @param params Et Map<K,V> der indeholder en bestemt 'Value' til en specificeret 'Key' - "WHERE key = value". Map tillader flere specifikationer. Ønskes ingen WHERE, indsæt "null"
     * @param sql Modtager det SQL-statement der er igang med at blive bygget op.
     * @return Returnerer en String med et SQL statement, hvor der er bygget WHERE clauses på.
     */
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


    /**
     * En generisk metode der bygger en INSERT String op ud fra den data, der ønskes hentet.
     * Herved kan vi nøjes med at kalde denne ene metode, der bygger alle INSERT strenge op.
     * @param table Tabellen der skal INSERT i.
     * @param values Et Map<K,V> der indeholder et selvvalgt antal set 'Keys' med specificeret 'Values' - "INSERT INTO (key) VALUES ("value")".
     */
    public void insertIntoRecords(String table, Map<String, String> values){
        String sql = "INSERT INTO ";
        StringBuilder builder = new StringBuilder();
        builder.append(sql);
        builder.append(table);
        builder.append(appendValues(values));
        builder.append(";");

        dbDriver.insertSQL(builder.toString());
    }

    /**
     * Metoden bruges kun af insertIntoRecords() til at bygge VALUES clausen
     * @param values Et Map<K,V> der indeholder et selvvalgt antal set 'Keys' med specificeret 'Values' - "INSERT INTO (key) VALUES ("value")".
     * @return Returnerer en String med et SQL statement, hvor VALUES clausen er bygget på.
     */
    private String appendValues(Map<String, String> values){
        StringBuilder builder = new StringBuilder();
        builder.append(" (");

        for(Iterator iterator = values.entrySet().iterator(); iterator.hasNext();){
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            builder.append(entry.getKey());

            if(iterator.hasNext()){
                builder.append(", ");
            }
        }
        builder.append(")");
        builder.append(" VALUES ");
        builder.append(" (");

        for(Iterator iterator = values.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            builder.append("'");
            builder.append(entry.getValue());
            builder.append("'");

            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }

        builder.append(")");
        return builder.toString();

    }


    /**
     * Mangler stadig updateRecords() og dets private metoder, if any.
     */
    public void updateRecords(){


    }


    /**
     * Mangler stadig deleteRecords() og dets private metoder, if any.
     */
    public void deleteRecords(){

    }

}
