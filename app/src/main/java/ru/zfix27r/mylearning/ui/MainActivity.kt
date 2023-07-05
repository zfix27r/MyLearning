package ru.zfix27r.mylearning.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val binding by viewBinding(ActivityMainBinding::bind)
    private val navController by lazy {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }
    val toolbar by lazy { MainToolbar(binding.searchbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        prepareAppTopBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun viewLoading(state: Boolean) {
        binding.loadingProgress.isVisible = state
    }

    fun viewError(stringId: Int?) {
        binding.loadingErrorGroup.isVisible = stringId != null
        binding.navHostFragment.isVisible = stringId == null
        stringId?.let { binding.loadingErrorMessage.text = getString(it) }
    }

    fun viewErrorInSnackBar(stringId: Int) {
        Snackbar.make(binding.root, stringId, Snackbar.LENGTH_SHORT).show()
    }

    private fun prepareAppTopBar() {
        binding.topAppBar.statusBarForeground =
            MaterialShapeDrawable.createWithElevationOverlay(this)

        lifecycleScope.launch {
            navController.currentBackStackEntryFlow.collectLatest {
                toolbar.updateToolbarHome(it.destination.id)
                toolbar.updateToolbar()
            }
        }

        binding.searchbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }

    fun onClickSearchBar() {
        binding.searchView.show()
    }
}