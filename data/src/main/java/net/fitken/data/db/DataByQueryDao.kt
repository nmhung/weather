package net.fitken.data.db

import androidx.room.*
import net.fitken.data.entities.DataByQuery

@Dao
interface DataByQueryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dataByQuery: DataByQuery)

    @Query("SELECT * FROM data_by_query where `query` = :query")
    suspend fun getDataByQuery(query: String): DataByQuery?

    @Update
    suspend fun updateDataByQuery(dataByQuery: DataByQuery)
}