package com.example.gecis

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import com.example.gecis.databinding.FragmentABinding
import com.example.gecis.databinding.FragmentBBinding
import kotlin.concurrent.timer


class BFragment : Fragment() {

    private var _binding: FragmentBBinding? = null
    private val binding  get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        _binding = FragmentBBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startLoaderAnimate()

        timer(initialDelay = 1000L, period = 1000L ) {
            endLoaderAnimate()
        }
    }

    private fun startLoaderAnimate() {
        val objectAnimator = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                val startHeight = 170
                val newHeight = (startHeight + (startHeight + 40) * interpolatedTime).toInt()
                binding.loaderImage.layoutParams.height = newHeight
                binding.loaderImage.requestLayout()
            }

            override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
                super.initialize(width, height, parentWidth, parentHeight)
            }

            override fun willChangeBounds(): Boolean {
                return true
            }
        }

        objectAnimator.repeatCount = -1
        objectAnimator.repeatMode = ValueAnimator.REVERSE
        objectAnimator.duration = 1000
        binding.loaderImage.startAnimation(objectAnimator)
    }

    private fun endLoaderAnimate() {
        binding.loaderImage.clearAnimation()
        binding.loaderImage.visibility = View.GONE
    }
}