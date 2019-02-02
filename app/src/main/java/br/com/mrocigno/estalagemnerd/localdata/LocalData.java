package br.com.mrocigno.estalagemnerd.localdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LocalData extends SQLiteOpenHelper {


    private static final String NOME_BANCO = "alicization.db";
    private static final String[][][] tbls =
            {
                    {
                            {"saved_mangas", "table"},
                            {"id", "integer primary key autoincrement"},
                            {"name", "text"},
                            {"link", "text"},
                            {"cover", "text"},
                            {"num_pages", "integer"},
                            {"saved", "integer"},
                            {"webid", "integer"}
                    },
                    {
                            {"downloaded_mangas", "table"},
                            {"webid", "integer primary key"},
                            {"description", "text"},
                            {"name", "text"},
                            {"link", "text"},
                            {"cover", "text"}
                    },
                    {
                            {"downloaded_chapters", "table"},
                            {"webid", "integer primary key"},
                            {"name", "text"},
                            {"num_pages", "integer"},
                            {"link", "text"},
                            {"id_manga", "integer"}
                    },
                    {
                            {"downloaded_pages", "table"},
                            {"webid", "integer primary key"},
                            {"id_chapter", "integer"},
                            {"link_page", "text"},
                            {"local_path", "text"},
                            {"num_page", "integer"}
                    }
            };
    private static final int VERSAO = 3;

    public LocalData(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
        Log.e("TESTEEE", "Iniciado");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(String[][] tables:tbls){
            String createSQL = "";
            for(String[] columns:tables){
                if(columns[1].equals("table")){
                    createSQL += "CREATE TABLE " + columns[0] + " (";
                    continue;
                }
                createSQL += columns[0] + " " + columns[1] + ", ";
            }
            createSQL = createSQL.substring(0, createSQL.length() -  2) + ");";

            Log.e("TESTEEE", createSQL);

            db.beginTransaction();
            try {
                execute(db, createSQL);
                db.setTransactionSuccessful();
            } catch (Exception e) {

            } finally {
                db.endTransaction();
            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS saved_mangas");
        db.execSQL("DROP TABLE IF EXISTS downloaded_mangas");
        db.execSQL("DROP TABLE IF EXISTS downloaded_chapters");
        db.execSQL("DROP TABLE IF EXISTS downloaded_pages");
        onCreate(db);
    }

    public void execute(SQLiteDatabase db, String sql){
        if (sql.trim().length()>0)
            db.execSQL(sql);
    }

    public void insert(String table, ContentValues cv){
        SQLiteDatabase db = getReadableDatabase();
        try {
            db.insert(table, null, cv);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            db.close();
        }
    }

    public ArrayList<Map<String, String>> query(String SQL, String[] args){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(SQL, args);
        ArrayList<Map<String, String>> arrayList = new ArrayList<>();

        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                map.put(cursor.getColumnName(i), cursor.getString(i));
            }
            arrayList.add(map);
        }
        cursor.close();
        db.close();
        return arrayList;
    }

    public void update(String table, ContentValues cv, String whereCondition){
        SQLiteDatabase db = getReadableDatabase();
        try {
            db.update(table, cv, whereCondition, null);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            db.close();
        }
    }

    public boolean checkIfThereIs(String table, String whereCondition){
        String selectQuery = "SELECT * FROM "+ table +" WHERE " + whereCondition;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        boolean response = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return response;
    }

}
