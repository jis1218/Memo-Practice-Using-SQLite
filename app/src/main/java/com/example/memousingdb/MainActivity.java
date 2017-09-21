package com.example.memousingdb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.memousingdb.domain.Memo;
import com.example.memousingdb.domain.MemoDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/*
안드로이드 sqlite 사용하기
1. db파일을 직접 코드로 생성
2. 로컬에서 만든 파일을 assets에 담은 후 복사 붙여넣기
> 우편번호처럼 기반 데이터가 필요한 db일 경우
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCreate, btnRead, btnUpdate, btnDelete;
    TextView textView;
    EditText editTitle, editContent;
    MemoDAO dao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnCreate.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        dao = new MemoDAO(this);
    }


    public void initView() {
        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        textView = (TextView) findViewById(R.id.textView);
        editContent = (EditText) findViewById(R.id.editContent);
        editTitle = (EditText) findViewById(R.id.editTitle);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnCreate:
                create();
                break;
            case R.id.btnRead :
                read();
                break;
            case R.id.btnUpdate :
                break;
            case R.id.btnDelete :
                break;
        }

    }

    public void create(){
        // 1. 화면에서 입력된 값을 가져온다.
        String title = editTitle.getText().toString();
        String content = editContent.getText().toString();

        // 2. 쿼리를 만든다.
        String query = "insert into memo(title, content, n_date)" + " values('" + title + "', '" + content + "', datetime('now', 'localtime'))";
        MemoDAO dao = new MemoDAO(this);
        dao.create(query);
        editTitle.setText("");
        editContent.setText("");
    }

    public void read(){
        MemoDAO dao = new MemoDAO(this);
        ArrayList<Memo> data = dao.read();
        textView.setText(" ");
        for(Memo memo : data){
            textView.append(memo.toString());
        }
    }

    @Override
    protected void onDestroy() {
        if(dao!=null){
            dao.close();
        }
        super.onDestroy();
    }
}


//       // 1. 데이터베이스 연결
//       DBHelper helper = new DBHelper(this);
//         SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
//        // 2. 데이터 넣기
//        long currentTime = System.currentTimeMillis();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        String date = sdf.format(currentTime);
//        String insertQuery = "insert into memo(title, content, n_date)" +
//                " values('제목테스트', '내용입니다', '" + date + "')"; //date 대신 sqlite의 datetime()을 가져올 수 있다.
//
//        sqLiteDatabase.execSQL(insertQuery);
//
//        String selectQuery = "select * from memo";
//        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
//        while(cursor.moveToNext()){
//            //컬럼 인덱스를 가져오는 명렁어가 따로 있음
//            int c_index = cursor.getColumnIndex("id");
//            int title_index = cursor.getColumnIndex("title");
//            int id = cursor.getInt(0);
//            String title = cursor.getString(1);
//            String content = cursor.getString(2);
//            String n_date = cursor.getString(3);
//            Log.d("데이터 확인", "=================");
//            System.out.printf("id=%d, title=%s, content=%s, + n_date=%s", id, title, content, n_date);
//        }
