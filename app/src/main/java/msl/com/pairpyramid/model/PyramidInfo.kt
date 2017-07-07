package msl.com.pairpyramid.model

import java.util.*

data class PyramidInfo(var count:Int?=0, var recentlyPaired:Boolean?=false, var recentPairDate: String?="") : Comparable<PyramidInfo>  {

    override fun compareTo(other: PyramidInfo): Int {
        if( this.count!!.compareTo(other.count!!) == 0 ) {
            if( this.recentPairDate!!.compareTo(other.recentPairDate!!) == 0 ) {
                return when(Random().nextInt(2)) {1 -> 1 else -> -1}
            } else {
                return this.recentPairDate!!.compareTo(other.recentPairDate!!)
            }
        } else {
            return this.count!!.compareTo(other.count!!)
        }
    }

    fun inc() {
        count = count!! + 1
    }
}