package msl.com.pairpyramid.view.presenter

interface MainContract{


    interface View {
        fun showPairStatus()
        fun updatePairStatus()
        fun showErrorMessage(message: String)
        fun startPairMatchingActivity()
    }

    interface Presenter {
        fun getPairStatus()


    }
}