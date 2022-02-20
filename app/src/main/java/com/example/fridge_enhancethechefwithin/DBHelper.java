package com.example.fridge_enhancethechefwithin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static String dbname="Fridge";

    public DBHelper( Context context) {
        super(context, dbname, null, 1 );

    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        //DB.execSQL("CREATE TABLE Fruits (F_ID int PRIMARY KEY, name TEXT)");
        //DB.execSQL("CREATE TABLE Meat (M_ID int PRIMARY KEY, name TEXT)");
        //DB.execSQL("CREATE TABLE Sample (id int PRIMARY KEY, name TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        //DB.execSQL("DROP TABLE IF EXISTS Fruits");
        //DB.execSQL("DROP TABLE IF EXISTS Meat");
        //DB.execSQL("DROP TABLE IF EXISTS Sample");

    }


    public Cursor getID(){
        SQLiteDatabase DB = this.getWritableDatabase();
        //Cursor cursor = DB.rawQuery("SELECT ingredient_id FROM ingredient where name IN ('" + Home.Selected.get(0) + "','" + Home.Selected.get(1) + "');" ,null);
        int n = Home.Selected.size();
        Cursor cursor=null;
        if (!Home.Selected.isEmpty()) {
            String query = "SELECT ingredient_id FROM ingredients where name IN (";
            for (int i = 0; i < n; i++) {
                if (i < (n - 1)) {
                    query = query + "'" + Home.Selected.get(i) + "',";
                } else if (i == (n - 1)) {
                    query = query + "'" + Home.Selected.get(i) + "');";
                }
            }
            cursor = DB.rawQuery(query,null);
            return cursor;
        }
        //query = query + "SELECT ingredient_id FROM ingredient where name IN (" + query + ");";
        cursor = DB.rawQuery("SELECT name from ingredients where ingredient_ID='999'",null);
        return cursor;
    }
    /*public Cursor getRecipes(){
        SQLiteDatabase DB = this.getWritableDatabase();
        int n = Home.SelectedID.size();
        Cursor cursor=null;
        if (!Home.SelectedID.isEmpty()) {
        String query = "select count(*) as rowcount , r.recipe_id, r.recipe_name, r.instructions\n" +
                "from \n" +
                "    Recipes r\n" +
                "    inner join recipe_ingredient ri\n" +
                "    on ri.rec_id = r.recipe_id\n" +
                "where ri.ing_id IN \n(";
                for (int i = 0; i < n; i++) {
                    if (i < (n - 1)) {
                        query = query + "'" + Home.SelectedID.get(i) + "',";
                    } else if (i == (n - 1)) {
                        query = query + "'" + Home.SelectedID.get(i) + "')\n";
                    }
                }
                query = query + "AND \n" +
                "r.recipe_id IN (select rec_id \n" +
                "from recipe_ingredient\n" +
                "group by rec_id\n" +
                "having count(*) >= " + n + ")\n" +
                "GROUP BY r.recipe_name\n" +
                "having rowcount >= " + n + ";";
            cursor = DB.rawQuery(query,null);
            return cursor;
        }
        cursor = DB.rawQuery("SELECT name from ingredients where ingredient_ID='999'",null);
        return cursor;
    }*/
    public Cursor getRecipes(){
        SQLiteDatabase DB = this.getWritableDatabase();
        int n = Home.SelectedID.size();
        Cursor cursor=null;
        if (!Home.SelectedID.isEmpty()) {
            DB.execSQL("DROP VIEW IF EXISTS T1;");
            DB.execSQL("DROP VIEW IF EXISTS T2;");
            DB.execSQL("DROP VIEW IF EXISTS T9119;");
            String viewT99 = "CREATE VIEW T9119 AS SELECT * FROM Recipes";
            String viewT1 = //"DROP VIEW IF EXISTS T1;\n" +
                    "CREATE VIEW T1\n" +
                    "AS\n" +
                    "select  rec_id, count(*) as NumIngrd \n" +
                    "from recipe_ingredient\n" +
                    "group by rec_id;";
            String viewT2 =// "DROP VIEW IF EXISTS T2;\n" +
                    "CREATE VIEW T2\n" +
                    "As \n" +
                    "select count(*) as NumMatches , r.recipe_id, r.ingredients, r.recipe_name, r.instructions\n" +
                    "from \n" +
                    "    Recipes r\n" +
                    "    inner join recipe_ingredient ri\n" +
                    "    on ri.rec_id = r.recipe_id\n" +
                    "where ri.ing_id IN (";
            for (int i = 0; i < n; i++) {
                if (i < (n - 1)) {
                    viewT2 = viewT2 + "'" + Home.SelectedID.get(i) + "',";
                } else if (i == (n - 1)) {
                    viewT2 = viewT2 + "'" + Home.SelectedID.get(i) + "')\n";
                }
            }
            viewT2 = viewT2 + "group by r.recipe_id;";

            DB.execSQL(viewT99);
            //DB.rawQuery("DROP VIEW IF EXISTS T1;",null);
            //DB.rawQuery(viewT1,null);
            //DB.rawQuery(viewT2,null);
            DB.execSQL(viewT1);
            DB.execSQL(viewT2);
            //DB.rawQuery(viewT99,null);
            String query = "SELECT recipe_name, instructions, ingredients FROM\n" +
                    "T1 INNER JOIN T2\n" +
                    "ON T1.rec_id = T2.recipe_id\n" +
                    "WHERE NumMatches=NumIngrd;";
            //String query1="SELECT recipe_name, instructions, ingredients FROM T2";
            //cursor = DB.rawQuery(query1,null);
            cursor = DB.rawQuery(query,null);
            //cursor = DB.rawQuery("SELECT name from ingredients where ingredient_ID='999'",null);
            return cursor;
        }
        cursor = DB.rawQuery("SELECT name from ingredients where ingredient_ID='999'",null);

        return cursor;
    }
}
