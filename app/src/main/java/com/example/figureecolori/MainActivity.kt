package com.example.figureecolori

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private lateinit var shapeNameTextView: TextView
    private lateinit var shapeImageView: ImageView
    private lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shapeNameTextView = findViewById(R.id.shapeNameTextView)
        shapeImageView = findViewById(R.id.shapeImageView)
        tts = TextToSpeech(this, this)

        val buttonCircle: Button = findViewById(R.id.buttonCircle)
        val buttonSquare: Button = findViewById(R.id.buttonSquare)
        val buttonTriangle: Button = findViewById(R.id.buttonTriangle)

        buttonCircle.setOnClickListener {
            updateShape(R.drawable.circle, getString(R.string.circle))
        }

        buttonSquare.setOnClickListener {
            updateShape(R.drawable.square, getString(R.string.square))
        }

        buttonTriangle.setOnClickListener {
            updateShape(R.drawable.triangle, getString(R.string.triangle))
        }

        val buttonRed: Button = findViewById(R.id.buttonRed)
        val buttonGreen: Button = findViewById(R.id.buttonGreen)
        val buttonBlue: Button = findViewById(R.id.buttonBlue)

        buttonRed.setOnClickListener {
            shapeImageView.setColorFilter(ContextCompat.getColor(this, R.color.red))
        }

        buttonGreen.setOnClickListener {
            shapeImageView.setColorFilter(ContextCompat.getColor(this, R.color.green))
        }

        buttonBlue.setOnClickListener {
            shapeImageView.setColorFilter(ContextCompat.getColor(this, R.color.blue))
        }
    }

    private fun updateShape(drawableId: Int, shapeName: String) {
        shapeImageView.setImageResource(drawableId)
        shapeImageView.contentDescription = shapeName // Set content description for accessibility
        shapeNameTextView.text = shapeName
        speakOut(shapeName)
    }

    private fun speakOut(text: String) {
        if (this::tts.isInitialized) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.getDefault()
        }
    }

    override fun onDestroy() {
        if (this::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}