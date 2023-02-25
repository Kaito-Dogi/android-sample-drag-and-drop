package app.doggy.draganddropsample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import app.doggy.draganddropsample.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), View.OnTouchListener {

  private lateinit var binding: ActivityMainBinding

  private var preDx by Delegates.notNull<Float>()
  private var preDy by Delegates.notNull<Float>()

  @SuppressLint("ClickableViewAccessibility")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    preDx = binding.image.x
    preDy = binding.image.y

    binding.image.setOnTouchListener(this@MainActivity)
  }

  override fun onTouch(view: View, event: MotionEvent): Boolean {
    val newDx = event.rawX
    val newDy = event.rawY

    when (event.action) {
      MotionEvent.ACTION_MOVE -> {
        view.performClick()

        val dx = binding.image.left + (newDx - preDx).toInt()
        val dy = binding.image.top + (newDy - preDy).toInt()
        val imgW = dx + binding.image.width
        val imgH = dy + binding.image.height

        binding.image.layout(dx, dy, imgW, imgH)
      }
    }

    preDx = newDx
    preDy = newDy

    return true
  }
}
