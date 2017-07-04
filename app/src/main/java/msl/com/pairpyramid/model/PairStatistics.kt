package msl.com.pairpyramid.model

import java.util.*

data class PairStatistics(var count: Int, var recentPairDate: String) : Comparable<PairStatistics> {

    override fun compareTo(other: PairStatistics): Int {

         if( this.count.compareTo(other.count) == 0 ) {
             if( this.recentPairDate.compareTo(other.recentPairDate) == 0 ) {
                 return when(Random().nextInt(2)) {1 -> 1 else -> -1}
             } else {
                 return this.recentPairDate.compareTo(other.recentPairDate)
             }
         } else {
             return this.count.compareTo(other.count)
         }
    }

}
