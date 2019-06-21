package com.ddd.twinkle.safehero.utills

import java.util.concurrent.TimeUnit

fun toReadableTime(millis :Long) : String{
    return String.format("%d:%d",
        TimeUnit.MILLISECONDS.toMinutes( millis),TimeUnit.MILLISECONDS.toSeconds(millis) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis) )
    )
}
