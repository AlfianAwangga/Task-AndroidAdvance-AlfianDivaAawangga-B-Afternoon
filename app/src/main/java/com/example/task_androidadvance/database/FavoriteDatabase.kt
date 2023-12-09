package com.example.task_androidadvance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.task_androidadvance.dao.FavoriteDao
import com.example.task_androidadvance.models.UserItem

@Database(
    entities = [UserItem::class],
    version = 1
)
abstract class FavoriteDatabase : RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao

    companion object {
        @Volatile
        var INSTANCE: FavoriteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            FavoriteDatabase::class.java,
            "favorite"
        ).fallbackToDestructiveMigration().build()
    }
}