package com.denisgithuku.fluffy.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.denisgithuku.fluffy.entities.Dog

@Dao
interface DogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dog: Dog)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(dog: Dog)

    @Delete
    suspend fun delete(dog: Dog)

    @Query("SELECT * FROM dog ORDER BY dog_id DESC")
    fun getAllDogs(): LiveData<List<Dog>>
}