package com.example.quizapp.data.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.quizapp.data.model.Question

@Database(entities = [Question::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE questions ADD COLUMN new_column TEXT")
    }
}

fun getAppDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "quizapp.db"
    )
        .addMigrations(MIGRATION_1_2)
        .fallbackToDestructiveMigration()
        .build()
}
