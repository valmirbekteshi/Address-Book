package com.valmirb.addressbook.utlis




fun String.isEmail():Boolean{
    return this.matches("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}".toRegex())
}