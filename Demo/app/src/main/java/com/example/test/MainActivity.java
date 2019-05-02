package com.example.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.Random;
/**
 * created by 103style  2019/5/1 22:29
 */
public class MainActivity extends AppCompatActivity {

    private final String SQL_PASSWORD = "12312sda";

    private SQLiteDatabase mSQLiteDatabase;

    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase.loadLibs(this);
        SQLiteDbHelper helper = new SQLiteDbHelper(getApplicationContext());
        mSQLiteDatabase = helper.getWritableDatabase(SQL_PASSWORD);

        show = findViewById(R.id.show);

        findViewById(R.id.insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertStudents();
            }
        });

        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryStudents();
            }
        });
    }

    private void queryStudents() {

        // 相当于 select * from students 语句
        Cursor cursor = mSQLiteDatabase.query(SQLiteDbHelper.TABLE_STUDENT, null,
                "cls_id > ? and id >= 1", new String[]{"3"},
                null, null, null, null);

        StringBuilder res = new StringBuilder();
        // 不断移动光标获取值
        while (cursor.moveToNext()) {
            // 直接通过索引获取字段值
            int stuId = cursor.getInt(0);

            // 先获取 name 的索引值，然后再通过索引获取字段值
            String stuName = cursor.getString(cursor.getColumnIndex("name"));
            res.append("id:" + stuId + "  name:" + stuName + "\n");
        }

        show.setText(res.toString());
        // 关闭光标
        cursor.close();
    }


    public void insertStudents() {
        for (int i = 0; i < 5; i++) {
            ContentValues values = studentToContentValues(mockStudent(i));
            mSQLiteDatabase.insert(SQLiteDbHelper.TABLE_STUDENT, null, values);
        }
    }

    // 构建 student 对象
    private Student mockStudent(int i) {
        Student student = new Student();
        student.id = i;
        student.name = "user-" + i;
        student.tel_no = String.valueOf(new Random().nextInt(200000));
        student.cls_id = new Random().nextInt(5);
        return student;
    }

    // 将 student 对象的值存储到 ContentValues 中
    private ContentValues studentToContentValues(Student student) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", student.id);
        contentValues.put("name", student.name);
        contentValues.put("tel_no", student.tel_no);
        contentValues.put("cls_id", student.cls_id);
        return contentValues;
    }
}
