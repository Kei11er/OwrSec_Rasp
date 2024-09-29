package com.owrsec.rasp

import android.content.Context
import com.scottyab.rootbeer.RootBeer

class rootDetector {
    // detecção com a library open source RootBeer
    fun checkRoot(context: Context): Boolean {
        var rootBeer:RootBeer = RootBeer(context);
        if (rootBeer.isRooted()) {
            return true;
        } else {
            return false;
        }
    }

}