package com.valmirb.addressbook.ui.addressbook

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.valmirb.addressbook.db.entity.Contact
import com.valmirb.addressbook.repository.ContactRepository
import io.reactivex.Flowable

class AddressBookViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: ContactRepository = ContactRepository(application)
    private var allContacts: LiveData<List<Contact>> = repository.getAllContacts()


    fun getAllContacts(): LiveData<List<Contact>> {
        return allContacts
    }

    fun searchByContact(name: String): Flowable<List<Contact>> {
        return repository.searchContactByName(name)
    }

}
