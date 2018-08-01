package com.example.paulofelipeoliveirasouza.weatherapp.mpv.mainActivity.ui

import android.view.View
import junit.framework.Assert
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private lateinit var mActivity: MainActivity
    private lateinit var mController: ActivityController<MainActivity>

    @Before
    fun setUp(){
        mController = Robolectric
                .buildActivity(MainActivity::class.java)
                .create()
                .start()

        mActivity = mController.get()

        mController.resume()
    }

    @Test
    fun checkActivityNotNull(){
        Assert.assertNotNull(mActivity)
    }

    @Test
    fun setProgressBar() {
        assertNotNull(mActivity.progress_bar)
        mActivity.progress_bar.visibility = View.VISIBLE
        assertThat(mActivity.progress_bar.visibility, equalTo(View.VISIBLE))
    }

    @Test
    fun setVisibleFrameLayout() {
    }


    @Test
    fun snackBarIsNetWorking() {
    }

    @Test
    fun snackBarOnError() {
    }

    @Test
    fun isNetworkAvaliableToContext() {
    }


    @Test
    fun setVisibleMessage() {
    }
}