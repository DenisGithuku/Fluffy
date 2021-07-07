package com.denisgithuku.fluffy.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.denisgithuku.fluffy.entities.Dog

@Database(entities = [Dog::class], version = 1, exportSchema = false)
abstract class DogDatabase: RoomDatabase() {

    abstract val dao: DogDao

    companion object {
        @Volatile

        private var INSTANCE: DogDatabase? = null

        fun getDatabaseInstance(context: Context): DogDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DogDatabase::class.java,
                        "dog_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}

