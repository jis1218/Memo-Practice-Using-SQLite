package com.example.memousingdb.domain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.memousingdb.DBHelper;

import java.util.ArrayList;

/**
 * Created by 정인섭 on 2017-09-21.
 */
/*
DAO Data Access Object
데이터 조작을 담당
 */
public class MemoDAO {

    // 1. 데이터베이스에 연결
    // 2. 조작
    // 3. 연결을 해제
    // 이 과정이 모든 메서드에 다 들어가야 한다.

    // 사용 예)
    // MemoDAO dao = new MemoDAO();         1. DAO 객체 생성
    // String query = "insert into..."      2. Query 생성
    // dao.create(query);                   3. 쿼리 실행
    DBHelper helper;
    public MemoDAO(Context context){
        helper = new DBHelper(context);
    }

    //C 생성
    public void create(String query){
        // 1. 데이터베이스에 연결

        SQLiteDatabase connection = helper.getReadableDatabase();

        // 2. 조직
        connection.execSQL(query);

        // 3. 연결을 해제
        connection.close();
        close();


    }
    //R 읽기
    public ArrayList<Memo> read(){
        String query = "select id, title, content, n_date from memo";
        ArrayList<Memo> list = new ArrayList<>();

        // 1. 데이터베이스에 연결
        SQLiteDatabase connection = helper.getReadableDatabase();
        // 2. 조직
        Cursor cursor = connection.rawQuery(query, null);
        while(cursor.moveToNext()){
            Memo memo = new Memo();
            memo.id = cursor.getInt(0);
            memo.title = cursor.getString(1);
            memo.content = cursor.getString(2);
            memo.n_date = cursor.getString(3);
            list.add(memo);
        }
        // 3. 연결을 해제
        connection.close();
        close();

        return list;
    }
    //U 수정
    public void update(String query){
        // 1. 데이터베이스에 연결

        SQLiteDatabase connection = helper.getWritableDatabase();

        // 2. 조직
        connection.execSQL(query);

        // 3. 연결을 해제
        connection.close();
        close();
    }
    //D 삭제
    public void delete( String query){
        // 1. 데이터베이스에 연결

        SQLiteDatabase connection = helper.getWritableDatabase();

        // 2. 조직
        connection.execSQL(query);

        // 3. 연결을 해제
        connection.close();
        close();
    }

    public void close(){
        helper.close();
    }
}
