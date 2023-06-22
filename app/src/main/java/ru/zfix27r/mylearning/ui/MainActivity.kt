package ru.zfix27r.mylearning.ui

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::bind)
    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun setOnClickLoadingRetry(onClickListener: OnClickListener) {
        binding.loadingRetryButton.setOnClickListener(onClickListener)
    }

    fun viewLoading(state: Boolean) {
        binding.loadingProgress.isVisible = state
    }

    fun viewError(stringId: Int?) {
        binding.loadingErrorGroup.isVisible = stringId != null
        binding.navHostFragment.isVisible = stringId == null
        stringId?.let { binding.loadingErrorMessageTextView.text = getString(it) }
    }

    fun viewErrorInSnackBar(stringId: Int) {
        Snackbar.make(binding.root, stringId, Snackbar.LENGTH_SHORT).show()
    }

    private fun setToolbar() {
        lifecycleScope.launch {
            navController.currentBackStackEntryFlow.collectLatest {
                when (it.destination.id) {
                    R.id.main -> binding.toolbar.navigationIcon = null
                    else -> binding.toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_24)
                }
            }
        }
    }

    fun toolbar(
        mode: ToolbarMode,
        callback: ((View) -> Unit)? = null
    ) {
        println("@@##### set $mode")
        val search = binding.toolbar.menu.findItem(R.id.toolbar_search)
        val done = binding.toolbar.menu.findItem(R.id.toolbar_done)

        when (mode) {
            ToolbarMode.MAIN -> {
                search.isVisible = true
                done.isVisible = false
            }

            ToolbarMode.EDIT -> {
                search.isVisible = false
                done.isVisible = true
            }

            ToolbarMode.EMPTY -> {
                search.isVisible = false
                done.isVisible = false
            }
        }

        binding.toolbar.setOnClickListener() {
            println("@@@!!!@@@ ${it.id}")
        }
    }

    fun toolbarHome(callback: ((View) -> Unit)? = null) {
        callback?.let {
            println("@@!@!@ 1")
            binding.toolbar.setNavigationOnClickListener(callback)
        } ?: run {
            binding.toolbar.setNavigationOnClickListener {
                println("@@!@!@ 2")
                navController.popBackStack() }
        }
    }
}