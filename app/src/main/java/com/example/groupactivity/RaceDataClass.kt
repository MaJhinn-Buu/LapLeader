package com.example.groupactivity
import android.os.Parcel
import android.os.Parcelable

data class RaceDataClass(
    val raceId: String,
    var year: Int,
    var date: String,
    var location: String,
    var laps: Int,
    var driverList: ArrayList<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.createStringArrayList() ?: arrayListOf()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(raceId)
        parcel.writeInt(year)
        parcel.writeString(date)
        parcel.writeString(location)
        parcel.writeInt(laps)
        parcel.writeStringList(driverList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RaceDataClass> {
        override fun createFromParcel(parcel: Parcel): RaceDataClass {
            return RaceDataClass(parcel)
        }

        override fun newArray(size: Int): Array<RaceDataClass?> {
            return arrayOfNulls(size)
        }
    }
}
