package com.valmirb.addressbook.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.valmirb.addressbook.db.dao.ContactDao
import com.valmirb.addressbook.db.entity.Contact

@Database(entities = [Contact::class], version = 1,exportSchema = false)
abstract class ContactDatabase : RoomDatabase(){

    abstract fun contactDao(): ContactDao

    companion object {
        private  var instance: ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase {
            if (instance == null) {
                synchronized(ContactDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java, "contact_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

            }
        }

    }



}