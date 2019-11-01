package com.valmirb.addressbook.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShareViewModel:ViewModel() {
    var onSearchLiveData  = MutableLiveData<String>()

}