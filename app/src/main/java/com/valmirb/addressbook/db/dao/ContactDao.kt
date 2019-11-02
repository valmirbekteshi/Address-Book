package com.valmirb.addressbook.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.valmirb.addressbook.db.entity.Contact
import io.reactivex.Flowable

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)

    @Query("DELETE FROM contacts_table")
    fun deleteAllContacts()

    @Query("SELECT * FROM contacts_table ")
    fun getAllContacts(): LiveData<List<Contact>>

    @Query("SELECT * FROM contacts_table WHERE id like :contactId")
    fun getContactById(contactId: Int): LiveData<Contact>

    @Query("SELECT * FROM contacts_table WHERE name like '%' || :name || '%'")
    fun searchContactByName(name: String): Flowable<List<Contact>>

}

