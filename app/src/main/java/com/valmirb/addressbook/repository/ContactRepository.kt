package com.valmirb.addressbook.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.valmirb.addressbook.db.ContactDatabase
import com.valmirb.addressbook.db.dao.ContactDao
import com.valmirb.addressbook.db.entity.Contact
import io.reactivex.Flowable


class ContactRepository(application: Application){

    private val contactDao = ContactDatabase.getInstance(application.applicationContext).contactDao()

    private val allContacts: LiveData<List<Contact>> = contactDao.getAllContacts()

    fun insert(contact: Contact) {
        InsertContactAsync(contactDao).execute(contact)
    }


    fun deleteAllContacts() {
        DeleteAllContactsAsyncTask(contactDao).execute()
    }

    fun getAllContacts(): LiveData<List<Contact>> {
        return allContacts
    }

    fun getContactById(id: Int): LiveData<Contact>{
       return contactDao.getContactById(id)
    }


    fun searchContactByName(name: String): Flowable<List<Contact>>{
        return contactDao.searchContactByName(name)
    }


    private class InsertContactAsync(val contactDao: ContactDao) : AsyncTask<Contact, Unit, Unit>() {

        override fun doInBackground(vararg contact: Contact?) {
            contactDao.insert(contact[0]!!)
        }
    }


    private class DeleteAllContactsAsyncTask(val contactDao: ContactDao) : AsyncTask<Contact, Unit, Unit>() {

        override fun doInBackground(vararg contact: Contact?) {
            contactDao.deleteAllContacts()
        }
    }
}