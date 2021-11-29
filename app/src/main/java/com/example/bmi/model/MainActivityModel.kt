package com.example.bodymassindex.model


import android.annotation.SuppressLint
import android.view.View
import android.widget.*
import com.example.bodymassindex.Helpers.Converter
import com.example.bodymassindex.MainActivity
import com.example.bodymassindex.R
import kotlinx.coroutines.*
import kotlin.math.pow


class MainActivityModel(mainActivity: MainActivity) {

    private final val view :MainActivity = mainActivity;
    private val heightValue :EditText = mainActivity.findViewById(R.id.height_EditText);//рост
    private val weightValue :EditText = mainActivity.findViewById(R.id.weight_EditText);//вес
    private val resultIMTButton:Button = mainActivity.findViewById<Button>(R.id.resultIMT_button);
    private val imtResultValue:TextView = mainActivity.requireViewById<TextView>(R.id.imt_result_formula);
    private val scale :ProgressBar = mainActivity.requireViewById<ProgressBar>(R.id.vertical_progressbar);
    private var duration = 5

    fun onInit() {
        imtResultValue.text = "";
        resultIMTButton.setOnClickListener(clickListener);
    }

    private val clickListener: View.OnClickListener =  View.OnClickListener { view ->
        when (view.id) {
            R.id.resultIMT_button-> {
                runBlocking{onCreateResult()};
            }
        }
    }

    @SuppressLint("WrongConstant")
    private suspend fun onCreateResult() {
        if(weightValue.text.toString() == ""){
            Toast.makeText(view,view.getString(R.string.not_weightValue) , duration).show()
            return
        }
        if(heightValue.text.toString() == ""){
            Toast.makeText(view,view.getString(R.string.not_heightValue) , duration).show()
            return
        }
        val weightInt : Int = weightValue.text.toString().toInt();
        val heightInt : Int = heightValue.text.toString().toInt();
        var result = Converter().roundOffTo2DecPlaces((weightInt ) / (heightInt/100.0).pow(2));
        val string: String = view.getString(R.string.result_text)
        imtResultValue.text = String.format(string, result);
        if (result != null) {
             scale.progressSetAnimation(result.toDouble().toInt(), 2);
        }
    }


    private suspend fun ProgressBar.progressSetAnimation(point: Int, delay: Long) {
        var countOfProgress = this.progress
        if (countOfProgress < point){
            var difference =  point - countOfProgress;
            onAddProgress(difference,this,delay)
        }
        else if (countOfProgress > point){
            var difference = countOfProgress - point
            onRemoveProgress(difference,this,delay)
        }
    }

    private suspend fun onRemoveProgress(difference: Int, progressBar: ProgressBar, delay: Long) = coroutineScope {
      var job = launch(start = CoroutineStart.LAZY) {
            for (I in 1..difference) {
                progressBar.setProgress(progressBar.progress - 1,true);
                delay(delay)
            }
        }
        job.start();
    }

    private suspend fun onAddProgress(difference: Int, progressBar: ProgressBar, delay: Long) = coroutineScope {
       var job = launch(start = CoroutineStart.LAZY) {
            for (I in 1..difference) {
                progressBar.setProgress(progressBar.progress + 1,true);
                delay(delay)
            }
        }
        job.start();
    }

}