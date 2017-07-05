package msl.com.pairpyramid.model

data class PyramidInfo(var count:Int?=0, var recentlyPaired:Boolean?=false) {

    fun inc() {
        count = count!! + 1
    }
}