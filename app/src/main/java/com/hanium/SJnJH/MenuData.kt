package com.hanium.SJnJH

import android.os.Parcel
import android.os.Parcelable

class MenuData(var menu: String, var price: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        menu = parcel.readString()!!,
        price = parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(menu)
        parcel.writeInt(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MenuData> {
        override fun createFromParcel(parcel: Parcel): MenuData {
            return MenuData(parcel)
        }

        override fun newArray(size: Int): Array<MenuData?> {
            return arrayOfNulls(size)
        }
    }
}