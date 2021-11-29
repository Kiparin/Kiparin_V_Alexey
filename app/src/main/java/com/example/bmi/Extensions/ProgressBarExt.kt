package com.example.bodymassindex.Extensions

import android.widget.ProgressBar
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

public final class ProgressBarExt {

    suspend fun ProgressBar.progressSetAnimation(point: Int, delay: Long) {
        var countOfProgress = this.progress
            if (countOfProgress > point){
                var difference = countOfProgress - point
                onAddProgress(difference,this,delay)
            }
           else if (countOfProgress < point){
                var difference = point - countOfProgress
                onRemoveProgress(difference,this,delay)
           }
    }

    private suspend fun onRemoveProgress(difference: Int, progressBar: ProgressBar, delay: Long) = coroutineScope {
        launch {
            for (I in 1..difference) {
                progressBar.setProgress(progressBar.progress - 1,true);
                delay(delay)
            }
        }
    }

    private suspend fun onAddProgress(difference: Int, progressBar: ProgressBar, delay: Long) = coroutineScope {
        launch {
            for (I in 1..difference) {
                progressBar.setProgress(progressBar.progress + 1,true);
                delay(delay)
            }
        }
    }


}