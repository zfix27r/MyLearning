package ru.zfix27r.mylearning.ui.activity

import android.content.SharedPreferences
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.search.SearchView.TransitionState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.zfix27r.domain.activity.SearchModel
import ru.zfix27r.mylearning.R
import ru.zfix27r.mylearning.databinding.ActivityMainBinding
import ru.zfix27r.mylearning.ui.search.SearchAdapter
import ru.zfix27r.mylearning.ui.search.SearchAdapterCallback


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    val binding by viewBinding(ActivityMainBinding::bind)
    private val viewModel by viewModels<MainViewModel>()
    private val navController by lazy {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHost.navController
    }
    private val searchCallback =
        object : SearchAdapterCallback {
            override fun onClick(searchModel: SearchModel) {
                TODO("Not yet implemented")
            }
        }
    private val adapter = SearchAdapter(searchCallback)
    val searchbar by lazy { Searchbar(binding.searchbar) }

    fun viewLoading(state: Boolean) {
        binding.loadingProgress.isVisible = state
    }

    fun viewError(stringId: Int?) {
        binding.loadingErrorGroup.isVisible = stringId != null
        binding.navHostFragment.isVisible = stringId == null
        stringId?.let { binding.loadingErrorMessage.text = getString(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        prepAppTopBar()
        binding.prepSearch()
        viewModel.observeResponse()
        viewModel.preferenceManager.registerOnSharedPreferenceChangeListener(this)
    }

    private fun prepAppTopBar() {
        lifecycleScope.launch {
            navController.currentBackStackEntryFlow.collectLatest {
                searchbar.updateToolbarHome(it.destination.id)
                searchbar.updateToolbar()
            }
        }

        binding.searchbar.setNavigationOnClickListener {
            when (navController.currentDestination?.id) {
                R.id.main -> navController.navigate(R.id.action_global_profile)
                else -> navController.popBackStack()
            }
        }
    }

    private fun ActivityMainBinding.prepSearch() {
        searchRecycler.adapter = adapter

        searchView.addTransitionListener { _, _, newState ->
            when (newState) {
                TransitionState.SHOWN -> fixOnBackFromSearchViewAddCallback()

                TransitionState.HIDING -> fixOnBackFromSearchViewRemoveCallback()

                TransitionState.HIDDEN -> viewModel.searchClear()
                else -> {}
            }
        }

        searchView.editText.setOnEditorActionListener { v, _, event ->
            event?.let {
                if (event.keyCode == KeyEvent.KEYCODE_ENTER)
                    viewModel.search(v.text.toString())
            }
            false
        }
    }

    private fun fixOnBackFromSearchViewAddCallback() {
        onBackPressedDispatcher.addCallback {
            binding.searchView.hide()
            remove()
        }
    }

    private fun fixOnBackFromSearchViewRemoveCallback() {
        onBackPressedDispatcher.onBackPressed()
    }

    private fun MainViewModel.observeResponse() =
        response.observeForever { adapter.submitList(it) }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        if (key == viewModel.themeKey) setTheme()
    }

    private fun setTheme() {
        when (viewModel.getCurrentTheme()) {
            viewModel.themeLight -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            viewModel.themeDark -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.preferenceManager.unregisterOnSharedPreferenceChangeListener(this)
    }
}