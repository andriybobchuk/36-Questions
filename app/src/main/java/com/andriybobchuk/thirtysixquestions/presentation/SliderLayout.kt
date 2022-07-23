package com.andriybobchuk.thirtysixquestions.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.andriybobchuk.thirtysixquestions.R
import com.andriybobchuk.thirtysixquestions.data.repository.backgrounds
import com.andriybobchuk.thirtysixquestions.data.repository.questions
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import androidx.compose.ui.util.lerp
import com.andriybobchuk.thirtysixquestions.data.model.Question


@ExperimentalPagerApi
@Composable
fun ViewPagerSlider() {

    val pagerState = rememberPagerState(
        pageCount = questions.size,
        initialPage = 0
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .height(65.dp)
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 15.dp),
                text = "36 Questions",
                color = MaterialTheme.colors.primary,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.billabong))
            )
        }

        //Spacer(modifier = Modifier.height(30.dp))
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .padding(0.dp, 20.dp, 0.dp, 30.dp)
        ) { page ->
            Card(modifier = Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth()
                .padding(15.dp, 0.dp, 15.dp, 0.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                val question = questions[page]
                setCardContent(question = question, page = page)
            }

        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(45.dp),
            indicatorWidth = 4.dp,
            activeColor = MaterialTheme.colors.primary
        )
    }
}


@Composable
fun setCardContent(question: Question, page: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(setCardColor(page))
            .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
    ) {

        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(35.dp)
        ) {

            Text(
                text = question.text,
                fontSize = 32.sp,
                style = MaterialTheme.typography.h5,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(25.dp)
        ) {
            Text(
                text = page.toString(),
                fontSize = 25.sp,
                style = MaterialTheme.typography.h5,
                color = if (page == 0 || page == 37) {
                    Color.Transparent
                } else {
                    Color.White.copy(alpha = 0.5f)
                },
                fontWeight = FontWeight.Bold
            )
        }
    }
}

fun setCardColor(page: Int): Color {
    return if (page == 0 || page == 37) {
        Color(
            backgrounds.last().toColorInt() // Black
        )
    } else {
        Color(
            backgrounds
                .random()
                .toColorInt()
        )
    }
}

