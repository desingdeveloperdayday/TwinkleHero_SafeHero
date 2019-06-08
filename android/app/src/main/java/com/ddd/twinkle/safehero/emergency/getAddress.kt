package com.ddd.twinkle.safehero.emergency

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import timber.log.Timber
import java.io.IOException

object ConvertToString{

    fun getAddress(context: Context,latLng: LatLng) : String?{
        val geocoder = Geocoder(context)
        val addresses : List<Address>?
        val address : Address?
        var addressText : String? =""
        try{

            addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1)

            if (!addresses.isNullOrEmpty()){
                address = addresses[0]

                for(i in 0 until address.maxAddressLineIndex){
                    addressText +=if(i==0) address.getAddressLine(i) else "\n" +
                            address.getAddressLine(i)
                }
            }
        }catch (e : IOException){
            Timber.e("ConverToString errror ${e.localizedMessage}")
        }
        return addressText
    }


}
