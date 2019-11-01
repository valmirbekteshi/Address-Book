package com.valmirb.addressbook.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.valmirb.addressbook.db.ContactDatabase
import com.valmirb.addressbook.db.dao.ContactDao
import com.valmirb.addressbook.db.entity.Contact


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


    private class InsertContactAsync(val contactDao: ContactDao) : AsyncTask<Contact, Unit, Unit>() {

        override fun doInBackground(vararg note: Contact?) {
            contactDao.insert(note[0]!!)
        }
    }


    private class DeleteAllContactsAsyncTask(val contactDao: ContactDao) : AsyncTask<Contact, Unit, Unit>() {

        override fun doInBackground(vararg note: Contact?) {
            contactDao.deleteAllContacts()
        }
    }
}