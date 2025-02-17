package com.shmulik.riversidemovie.util

import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.lazy.LazyListState
import kotlinx.coroutines.delay

suspend fun centerItem(
    listState: LazyListState,
    index: Int
) {

    val layoutInfo = listState.layoutInfo
    val itemInfo = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index } ?: return

    val viewportCenter = layoutInfo.viewportStartOffset +
            (layoutInfo.viewportEndOffset - layoutInfo.viewportStartOffset) / 2f
    val itemCenter = itemInfo.offset + itemInfo.size / 2f

    val distanceToCenter = itemCenter - viewportCenter
    if (distanceToCenter != 0f) {

        listState.smoothScrollBy(distanceToCenter)
    }
}


suspend fun LazyListState.smoothScrollBy(
    distance: Float,
    durationMillis: Int = 300,
    steps: Int = 50
) {
    if (distance == 0f) return
    val delayPerStep = (durationMillis / steps).coerceAtLeast(1)
    var scrolledAlready = 0f

    repeat(steps) { step ->
        val fraction = (step + 1) / steps.toFloat()
        val currentTarget = distance * fraction
        val delta = currentTarget - scrolledAlready
        scrolledAlready += delta
        scrollBy(delta)
        delay(delayPerStep.toLong())
    }
}