package msl.com.pairpyramid

import msl.com.pairpyramid.view.entry.MakeEntryActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricGradleTestRunner::class)
@Config(
        manifest = "src/main/AndroidManifest.xml",
        constants = BuildConfig::class,
        emulateSdk = 21
)
class MakeEntryActivityTest {

        private val USER_ID = 123
        private val subject = Robolectric.buildActivity(MakeEntryActivity::class.java).create().get()


        @Test
        fun whenLoadPlayerListThenUpdatePlayerList(){

                subject.findViewById(R.id.btn_cancel).performClick()


        }


}