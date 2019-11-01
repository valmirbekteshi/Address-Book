package com.valmirb.addressbook.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contacts_table")
data class Contact(
    val name: String,
    val lastname: String,
    val email: String,
    val phoneNumber: Int,
    val address: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}
