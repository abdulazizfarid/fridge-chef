package com.example.fridge_enhancethechefwithin;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;

public class DBHandler extends SQLiteOpenHelper{
    public static final int dbVersion = 1;
    public static final String dbPath = "/data/data/com.example.fridge_enhancethechefwithin/databases/";
    public static final String dbName ="Fridge";
    SQLiteDatabase myDatabase;
    private final Context mContext;

    public DBHandler(Context context) {
        super(context, dbName, null, dbVersion );
        this.mContext = context;
        try {
            createDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copyDatabase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = mContext.getAssets().open(dbName);// + ".db");

        // Path to the just created empty db
        String outFileName = dbPath + dbName;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void createDatabase() throws IOException{
        boolean exists = checkDatabase();
        if(!exists) {
            try {
                this.getReadableDatabase();
                this.close();
                copyDatabase();
            } catch (Exception exc){
                exc.printStackTrace();
            } /*catch (IOException mIOException){
                mIOException.printStackTrace();
                throw new Error("Error copying DB");
            } */finally {
                this.close();
            }
        }
    }

    @Override
    public synchronized void close(){
        if(myDatabase != null)
            myDatabase.close();
        SQLiteDatabase.releaseMemory();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {}

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {}

    private boolean checkDatabase(){
        try{
            final String mPath = dbPath + dbName;
            final File file = new File(mPath);
            if (file.exists()) return true;
            else return false;
        } catch (SQLiteException e){
            e.printStackTrace();
            return false;
        }
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

    public Cursor getAuth(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        try{
            Cursor cursor = DB.rawQuery("SELECT * FROM Users WHERE email = '" + email + "'", null);
            return cursor;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void signup(String email, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        try{
            DB.execSQL("INSERT INTO Users (email, password) VALUES (?,?)",new Object[]{email, password});
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Cursor getFavorites(String email){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT recipe_name, ingredients, instructions FROM Recipes WHERE recipe_id IN (SELECT recipeId FROM UserFavorites WHERE userEmail = ?)", new String[]{email});
        return cursor;
    }
}
