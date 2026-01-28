package com.example.edumate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.regex.Pattern;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "edumate.db";
    private static final int DB_VERSION = 3;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTable = "CREATE TABLE users(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, phone TEXT, username TEXT UNIQUE, password TEXT)";
        db.execSQL(userTable);

        String subjectTable = "CREATE TABLE subjects(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)";
        db.execSQL(subjectTable);

        String topicTable = "CREATE TABLE topics(id INTEGER PRIMARY KEY AUTOINCREMENT, subject_id INTEGER, name TEXT, notes TEXT)";
        db.execSQL(topicTable);

        String questionTable = "CREATE TABLE questions(id INTEGER PRIMARY KEY AUTOINCREMENT, subject_id INTEGER, question TEXT, option_a TEXT, option_b TEXT, option_c TEXT, option_d TEXT, answer TEXT, difficulty TEXT)";
        db.execSQL(questionTable);

        String syllabusTable = "CREATE TABLE syllabus(id INTEGER PRIMARY KEY AUTOINCREMENT, subject_id INTEGER, unit_number INTEGER, unit_title TEXT, content TEXT)";
        db.execSQL(syllabusTable);

        String progressTable = "CREATE TABLE progress(id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, subject_id INTEGER, topics_completed INTEGER DEFAULT 0, quiz_score INTEGER DEFAULT 0, study_streak INTEGER DEFAULT 0, last_topic TEXT)";
        db.execSQL(progressTable);

        String qaTable = "CREATE TABLE qa_pairs(id INTEGER PRIMARY KEY AUTOINCREMENT, keywords TEXT, answer TEXT)";
        db.execSQL(qaTable);

        addSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS subjects");
        db.execSQL("DROP TABLE IF EXISTS topics");
        db.execSQL("DROP TABLE IF EXISTS questions");
        db.execSQL("DROP TABLE IF EXISTS syllabus");
        db.execSQL("DROP TABLE IF EXISTS progress");
        db.execSQL("DROP TABLE IF EXISTS qa_pairs");
        onCreate(db);
    }

    private void addSampleData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO subjects(name) VALUES('Mathematics')");
        db.execSQL("INSERT INTO subjects(name) VALUES('Physics')");
        db.execSQL("INSERT INTO subjects(name) VALUES('Chemistry')");
        db.execSQL("INSERT INTO subjects(name) VALUES('Computer Science')");

        db.execSQL("INSERT INTO topics(subject_id, name, notes) VALUES(1, 'Algebra', 'Algebra is a branch of mathematics dealing with symbols and the rules for manipulating those symbols. Key concepts include variables, expressions, equations, and functions.')");
        db.execSQL("INSERT INTO topics(subject_id, name, notes) VALUES(1, 'Calculus', 'Calculus is the mathematical study of continuous change. It has two major branches: differential calculus and integral calculus.')");
        db.execSQL("INSERT INTO topics(subject_id, name, notes) VALUES(2, 'Mechanics', 'Mechanics is the branch of physics dealing with motion and forces. Topics include kinematics, dynamics, and statics.')");
        db.execSQL("INSERT INTO topics(subject_id, name, notes) VALUES(2, 'Thermodynamics', 'Thermodynamics deals with heat, work, temperature, and energy. Laws of thermodynamics govern energy transfer.')");
        db.execSQL("INSERT INTO topics(subject_id, name, notes) VALUES(3, 'Organic Chemistry', 'Organic chemistry studies carbon-containing compounds. Key topics include hydrocarbons, functional groups, and reactions.')");
        db.execSQL("INSERT INTO topics(subject_id, name, notes) VALUES(4, 'Data Structures', 'Data structures organize and store data efficiently. Common types include arrays, linked lists, stacks, queues, trees, and graphs.')");

        db.execSQL("INSERT INTO questions(subject_id, question, option_a, option_b, option_c, option_d, answer, difficulty) VALUES(1, 'What is 2 + 2?', '3', '4', '5', '6', 'B', 'Easy')");
        db.execSQL("INSERT INTO questions(subject_id, question, option_a, option_b, option_c, option_d, answer, difficulty) VALUES(1, 'Derivative of x^2 is?', 'x', '2x', 'x^2', '2', 'B', 'Medium')");
        db.execSQL("INSERT INTO questions(subject_id, question, option_a, option_b, option_c, option_d, answer, difficulty) VALUES(2, 'Unit of Force is?', 'Joule', 'Newton', 'Watt', 'Pascal', 'B', 'Easy')");
        db.execSQL("INSERT INTO questions(subject_id, question, option_a, option_b, option_c, option_d, answer, difficulty) VALUES(4, 'Time complexity of binary search?', 'O(n)', 'O(log n)', 'O(n^2)', 'O(1)', 'B', 'Medium')");

        db.execSQL("INSERT INTO syllabus(subject_id, unit_number, unit_title, content) VALUES(1, 1, 'Introduction to Algebra', 'Variables, Constants, Expressions')");
        db.execSQL("INSERT INTO syllabus(subject_id, unit_number, unit_title, content) VALUES(1, 2, 'Linear Equations', 'Solving linear equations, Applications')");
        db.execSQL("INSERT INTO syllabus(subject_id, unit_number, unit_title, content) VALUES(2, 1, 'Kinematics', 'Motion, Velocity, Acceleration')");
        db.execSQL("INSERT INTO syllabus(subject_id, unit_number, unit_title, content) VALUES(2, 2, 'Dynamics', 'Forces, Newton Laws, Momentum')");
        db.execSQL("INSERT INTO syllabus(subject_id, unit_number, unit_title, content) VALUES(4, 1, 'Introduction to Programming', 'Basics, Variables, Control Flow')");
        db.execSQL("INSERT INTO syllabus(subject_id, unit_number, unit_title, content) VALUES(4, 2, 'Data Structures', 'Arrays, Lists, Stacks, Queues')");

        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('hello,hi,hey', 'Hello! How can I help you with your studies today?')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('algebra,what is algebra', 'Algebra is a branch of mathematics that uses symbols and letters to represent numbers and quantities in formulas and equations.')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('calculus,what is calculus', 'Calculus is the mathematical study of continuous change, with two main branches: differential calculus and integral calculus.')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('physics,what is physics', 'Physics is the natural science that studies matter, energy, and their interactions through space and time.')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('newton,newton laws', 'Newtons three laws of motion: 1) An object remains at rest or in motion unless acted upon by a force. 2) F=ma. 3) Every action has an equal and opposite reaction.')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('data structure,data structures', 'Data structures are ways of organizing and storing data so that they can be accessed and modified efficiently. Examples include arrays, linked lists, trees, and graphs.')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('algorithm,algorithms', 'An algorithm is a step-by-step procedure for solving a problem or accomplishing a task.')");
        
        // New Q&A Pairs
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('largest planet,planet', 'Jupiter')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('father of computer,computer', 'Charles Babbage')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('symbol for gold,gold', 'Au')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('organ purifying blood,kidney', 'Kidney')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('romeo juliet,william shakespeare', 'William Shakespeare')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('hardest substance,diamond', 'Diamond')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('continents,count of continents', 'Seven')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('boiling point,water boiling', '100 degrees Celsius')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('ship of desert,camel', 'Camel')");
        db.execSQL("INSERT INTO qa_pairs(keywords, answer) VALUES('capital of japan,tokyo,japan', 'Tokyo')");
    }

    public boolean registerUser(String name, String email, String phone, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("email", email);
        cv.put("phone", phone);
        cv.put("username", username);
        cv.put("password", password);
        long result = db.insert("users", null, cv);
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});
        if(cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public String getUserName(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM users WHERE username=?", new String[]{username});
        String name = "";
        if(cursor.moveToFirst()) {
            name = cursor.getString(0);
        }
        cursor.close();
        return name;
    }

    public Cursor getAllSubjects() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM subjects", null);
        return cursor;
    }

    public Cursor getTopicsBySubject(int subjectId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM topics WHERE subject_id=?", new String[]{String.valueOf(subjectId)});
        return cursor;
    }

    public String getNotesForTopic(int subjectId, String topicName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT notes FROM topics WHERE subject_id=? AND name=?", new String[]{String.valueOf(subjectId), topicName});
        String notes = "";
        if(cursor.moveToFirst()) {
            notes = cursor.getString(0);
        }
        cursor.close();
        return notes;
    }

    public Cursor getQuestionsByDifficulty(int subjectId, String difficulty) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM questions WHERE subject_id=? AND difficulty=?", new String[]{String.valueOf(subjectId), difficulty});
        return cursor;
    }

    public Cursor getSyllabusForSubject(int subjectId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM syllabus WHERE subject_id=? ORDER BY unit_number", new String[]{String.valueOf(subjectId)});
        return cursor;
    }

    public String findAnswer(String question) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM qa_pairs", null);
        String userQuestion = question.toLowerCase();

        while(cursor.moveToNext()) {
            String keywords = cursor.getString(cursor.getColumnIndexOrThrow("keywords"));
            String[] keywordList = keywords.split(",");

            for(int i = 0; i < keywordList.length; i++) {
                String keyword = keywordList[i].trim().toLowerCase();
                // Use regex to look for whole words only
                // \b matches word boundaries
                if(Pattern.compile("\\b" + Pattern.quote(keyword) + "\\b").matcher(userQuestion).find()) {
                    String answer = cursor.getString(cursor.getColumnIndexOrThrow("answer"));
                    cursor.close();
                    return answer;
                }
            }
        }
        cursor.close();
        return "Sorry, I dont have information about that topic. Try asking about algebra, calculus, physics, or data structures.";
    }
}
