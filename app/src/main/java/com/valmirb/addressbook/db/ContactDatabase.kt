package com.valmirb.addressbook.db

import android.content.Context
import android.os.AsyncTask
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
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }

    }

    class PopulateDbAsyncTask(db: ContactDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val contactDao = db?.contactDao()

        override fun doInBackground(vararg p0: Unit?) {
            contactDao?.insert(Contact("Title 1", "description 1","s",11,"sss"))
            contactDao?.insert(Contact("Title 2", "description 2","sss",123,"ssss"))
            contactDao?.insert(Contact("Title 3", "description 3","sdff",322,"ddd"))
        }
    }



}