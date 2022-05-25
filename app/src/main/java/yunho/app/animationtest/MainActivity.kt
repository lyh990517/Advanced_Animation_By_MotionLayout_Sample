package yunho.app.animationtest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import yunho.app.animationtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isAnimationOn = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.scrollVIew.viewTreeObserver.addOnScrollChangedListener {
            val scrollVal = binding.scrollVIew.scrollY
            if(scrollVal > 360f.dpToPx(this).toInt()){
                if(isAnimationOn.not()){
                    binding.motionLayout.transitionToEnd()
                }else{
                    if(isAnimationOn.not()){
                        binding.motionLayout.transitionToStart()
                    }
                }
            }
        }
        binding.motionLayout.setTransitionListener(object : MotionLayout.TransitionListener{
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {
                isAnimationOn = true
            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                when(currentId){
                    R.id.end1 -> {
                        binding.motionLayout.setTransition(R.id.start2,R.id.end2)
                        binding.motionLayout.transitionToEnd()
                    }
                    R.id.end2 -> {
                        binding.motionLayout.setTransition(R.id.start3,R.id.end3)
                        binding.motionLayout.transitionToEnd()
                    }
                    R.id.end3 -> {
                        binding.motionLayout.setTransition(R.id.start4,R.id.end4)
                        binding.motionLayout.transitionToEnd()
                    }
                }
                isAnimationOn = false
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }

        })
    }
    fun Float.dpToPx(context: Context): Float =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, context.resources.displayMetrics)

}