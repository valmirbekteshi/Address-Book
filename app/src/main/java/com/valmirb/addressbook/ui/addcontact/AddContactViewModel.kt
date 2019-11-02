package com.valmirb.addressbook.ui.addcontact

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.valmirb.addressbook.db.entity.Contact
import com.valmirb.addressbook.repository.ContactRepository

class AddContactViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: ContactRepository = ContactRepository(application)

    fun insert(contact: Contact) {
        repository.insert(contact)
    }

}