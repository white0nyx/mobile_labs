package com.example.dstu_4_2.db

import android.content.ContentValues
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
                $COLUMN_SPORT_ID INTEGER,
                FOREIGN KEY ($COLUMN_SPORT_ID) REFERENCES $TABLE_SPORTS($COLUMN_ID)
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
        val query = "SELECT * FROM $TABLE_PARTICIPANTS INNER JOIN $TABLE_SPORTS ON $TABLE_PARTICIPANTS.$COLUMN_SPORT_ID = $TABLE_SPORTS.$COLUMN_ID"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val age = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE))
                val sportId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SPORT_ID))
                participants.add(Participant(id, name, age, sportId))
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

    fun getSportById(sportId: Int): Sport? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_SPORTS,
            null,
            "$COLUMN_ID=?",
            arrayOf(sportId.toString()),
            null,
            null,
            null
        )

        var sport: Sport? = null
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            sport = Sport(id, name)
        }
        cursor.close()
        return sport
    }

    fun deleteSport(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_SPORTS, "$COLUMN_ID=?", arrayOf(id.toString()))
    }

    fun deleteParticipant(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_PARTICIPANTS, "$COLUMN_ID=?", arrayOf(id.toString()))
    }

    fun addParticipant(participant: Participant) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, participant.name)
            put(COLUMN_AGE, participant.age)
            put(COLUMN_SPORT_ID, participant.sportId)
        }
        db.insert(TABLE_PARTICIPANTS, null, values)
    }

    companion object {
        private const val DATABASE_NAME = "sports_competition.db"
        private const val DATABASE_VERSION = 3

        const val TABLE_PARTICIPANTS = "participants"
        const val TABLE_SPORTS = "sports"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_AGE = "age"
        const val COLUMN_SPORT_ID = "sport_id"
    }
}
