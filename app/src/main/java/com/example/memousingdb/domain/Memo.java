package com.example.memousingdb.domain;

/**
 * Created by 정인섭 on 2017-09-21.
 */

public class Memo {
    int id;
    String title;
    String content;
    String n_date;

    @Override
    public String toString() {
        return id + "|" + title + "|" + content + "|" + n_date + "\n";
    }
}
