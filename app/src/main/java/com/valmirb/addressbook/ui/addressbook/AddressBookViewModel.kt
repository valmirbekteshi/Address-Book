package com.valmirb.addressbook.ui.addressbook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.valmirb.addressbook.db.entity.Contact
import com.valmirb.addressbook.repository.ContactRepository

class AddressBookViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: ContactRepository = ContactRepository(application)
    private var allContacts: LiveData<List<Contact>> = repository.getAllContacts()

    fun insert(note: Contact) {
        repository.insert(note)
    }

    fun deleteAllContacts() {
        repository.deleteAllContacts()
    }

    fun getAllContacts(): LiveData<List<Contact>> {
        return allContacts
    }

}
