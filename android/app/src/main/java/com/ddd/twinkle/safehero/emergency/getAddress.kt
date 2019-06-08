package com.ddd.twinkle.safehero.emergency

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
import timber.log.Timber

object ConvertToString{

    fun getAddress(context: Context,latLng: LatLng) : String?{

        val geocoder = Geocoder(context)
        val addresses : List<Address>?
        val address : Address?
        var addressText : String? =""
        try{

            addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1)

            if(addresses!=null && addresses.isNotEmpty()){
                address = addresses[0]

                for(i in 0 .. address.maxAddressLineIndex){
                    addressText +=if (i==0) address.getAddressLine(i) else "\n" +
                            address.getAddressLine(i)
                }
            }
        }catch (e : Throwable){
            Timber.e("ConverToString errror ${e}")
        }
        return addressText

    }


}
