package com.example.memousingdb;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 정인섭 on 2017-09-21.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "sqlite.db";
    private static final int DB_VERSION = 2;

    private static DBHelper instance = null;

//    //속도를 빠르게 하기 위해...
//    public static DBHelper getInstance(Context context){
//        if(instance==null){
//            instance = new DBHelper(context);
//        }
//        return instance;
//    }



    public DBHelper(Context context){
        super(context, DB_NAME,  null, DB_VERSION);
        //super에서 넘겨받은 database가 생성되어있는지 확인한 후
        // 1. 없으면 onCreate 호출
        // 2. 있으면 버전을 체크해서 생성되어 있는 DB보다 버전이 높으면 onUpgrade() 호출
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // 최초 생성할 테이블을 정의
        // 데이터베이스가 업데이트되면
        // 모든 히스토리가 쿼리에 반영되어 있어야 한다.
        String createQuery = "CREATE TABLE `memo` ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "`title` TEXT, `content` TEXT, `n_date` TEXT )";
        //쿼리를 실행해서 테이블을 생성한다.
        sqLiteDatabase.execSQL(createQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // 변경된 버전과 현재 버전을 비교해서 히스토리를 모두 반영해야 한다.

    }
}

// 앱 전체의 하나만 new 가 되어야 할 때 사용하는 패턴
// 생성자를 private으로 막아놓으면
//class Singleton{
//    //접근 가능한 통로를 한개만 열어준다.
//    //인스턴스를 한개 저장하는 저장소
//    private static Singleton instance = null;
//
//    public static Singleton getInstance(){
//        if(instance==null){
//            instance = new Singleton();
//        }
//        return instance;
//    }
//    private Singleton(){
//
//    }
//
//}