package com.example.pokemons

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "poke_table")
data class Pokemon(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    var id: Int,

    @ColumnInfo
    @SerializedName("name")
    var name: String,

    @ColumnInfo
    @SerializedName("base_experience")
    var baseExperience: Int,

    @ColumnInfo
    @SerializedName("height")
    var height: Int,

    @ColumnInfo
    @SerializedName("weight")
    var weight: Int

) : Serializable
