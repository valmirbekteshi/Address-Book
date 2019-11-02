package com.valmirb.addressbook.ui.contactdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.valmirb.addressbook.db.entity.Contact
import com.valmirb.addressbook.repository.ContactRepository

class ContactDetailViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: ContactRepository = ContactRepository(application)


    fun getContactDetail(id: Int): LiveData<Contact> {
        return repository.getContactById(id)
    }


}