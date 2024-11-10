package com.example.dstu_4_2.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.dstu_4_2.models.Participant
import com.example.dstu_4_2.models.Sport

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createParticipantTable = """
            CREATE TABLE $TABLE_PARTICIPANTS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_AGE INTEGER,
                $COLUMN_SPORT TEXT
            )
        """
        db.execSQL(createParticipantTable)

        val createSportTable = """
            CREATE TABLE $TABLE_SPORTS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT
            )
        """
        db.execSQL(createSportTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PARTICIPANTS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SPORTS")
        onCreate(db)
    }

    fun getAllParticipants(): List<Participant> {
        val participants = mutableListOf<Participant>()
        val db = readableDatabase
        val cursor = db.query(TABLE_PARTICIPANTS, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val age = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE))
                val sport = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SPORT))
                participants.add(Participant(id, name, age, sport))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return participants
    }

    fun getAllSports(): List<Sport> {
        val sports = mutableListOf<Sport>()
        val db = readableDatabase
        val cursor = db.query(TABLE_SPORTS, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                sports.add(Sport(id, name))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return sports
    }

    fun deleteSport(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_SPORTS, "$COLUMN_ID=?", arrayOf(id.toString()))
    }

    fun deleteParticipant(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_PARTICIPANTS, "$COLUMN_ID=?", arrayOf(id.toString()))
    }

    companion object {
        private const val DATABASE_NAME = "sports_competition.db"
        private const val DATABASE_VERSION = 2

        const val TABLE_PARTICIPANTS = "participants"
        const val TABLE_SPORTS = "sports"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
        const val COLUMN_SPORT = "sport"
    }
}
