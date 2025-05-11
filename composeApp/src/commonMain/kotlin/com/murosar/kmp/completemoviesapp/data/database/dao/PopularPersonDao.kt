package com.murosar.kmp.completemoviesapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.murosar.kmp.completemoviesapp.data.database.entity.KnownForEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularPersonEntity
import com.murosar.kmp.completemoviesapp.data.database.entity.PopularPersonEntityWithKnownForEntity
import io.mockative.Mockable

@Mockable
@Dao
interface PopularPersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularPerson(popularPersonEntity: PopularPersonEntity)

    @Transaction
    @Query("SELECT * FROM popular_person")
    suspend fun getPopularPersons(): List<PopularPersonEntityWithKnownForEntity>
}

@Mockable
@Dao
fun interface KnownForDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKnownFor(knownForEntity: KnownForEntity)
}
