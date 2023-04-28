package com.crashaviatorjogo.slod.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.crashaviatorjogo.slod.R
import com.crashaviatorjogo.slod.domain.repository.Status
import com.crashaviatorjogo.slod.domain.utils.Constants
import com.crashaviatorjogo.slod.presentation.viewmodel.StartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class AviatorActivity : AppCompatActivity() {

    private val activityViewModel: StartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashLayout = FrameLayout(this)
        splashLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.primary))

        val imageView = ImageView(this)
        imageView.setImageResource(R.drawable.icon)
        imageView.adjustViewBounds = true
        val layoutParams = FrameLayout.LayoutParams(
            140,
            140,
            Gravity.CENTER
        )
        imageView.layoutParams = layoutParams
        splashLayout.addView(imageView)

        setContentView(splashLayout)

        activityViewModel
            .fetchSettings()
            .take(1)
            .onEach { resource ->

                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { data ->
                            data.approve?.let { approve ->
                                when (approve) {
                                    true -> {
                                        activityViewModel
                                            .data
                                            .take(1)
                                            .onEach { data ->
                                                if (data.isNotEmpty())
                                                    supportFragmentManager.commit {
                                                        replace(android.R.id.content, AltScreen())
                                                    }
                                                else openGame()
                                            }
                                            .launchIn(lifecycleScope)
                                    }
                                    false -> {
                                        openGame()
                                    }
                                }
                            } ?: openGame()
                        } ?: openGame()
                    }
                    Status.ERROR -> {
                        openGame()
                    }
                    Status.LOADING -> {}
                }
            }
            .launchIn(lifecycleScope)
    }

    private fun openGame() {
        val intent = Intent(this, Constants.GAME_CLASS)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        this.startActivity(intent)
        this.finish()
    }
}
