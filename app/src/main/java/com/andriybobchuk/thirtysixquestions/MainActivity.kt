package com.andriybobchuk.thirtysixquestions

import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.andriybobchuk.thirtysixquestions.presentation.ViewPagerSlider
import com.andriybobchuk.thirtysixquestions.ui.theme.ThirtySixQuestionsTheme
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThirtySixQuestionsTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ViewPagerSlider()
                }
            }
        }
    }
}
