package com.bakigoal.sleeptracker.sleeptracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bakigoal.sleeptracker.R
import com.bakigoal.sleeptracker.database.SleepDatabase
import com.bakigoal.sleeptracker.databinding.FragmentSleepTrackerBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A fragment with buttons to record start and end times for sleep, which are saved in
 * a database. Cumulative data is displayed in a simple scrollable TextView.
 * (Because we have not learned about RecyclerView yet.)
 */
class SleepTrackerFragment : Fragment() {

    private lateinit var sleepNightAdapter: SleepNightAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSleepTrackerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sleep_tracker, container, false
        )

        // Create and bind ViewModel
        val sleepTrackerViewModel = sleepTrackerViewModel()
        binding.sleepTrackerViewModel = sleepTrackerViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Create and bind Adapter
        sleepNightAdapter = SleepNightAdapter()
        binding.sleepList.adapter = sleepNightAdapter

        // add Observers to ViewModel
        observeViewModel(sleepTrackerViewModel)

        return binding.root
    }

    private fun observeViewModel(viewModel: SleepTrackerViewModel) {
        viewModel.navigateToSleepQuality.observe(viewLifecycleOwner, {
            it?.let {
                navigateToQuality(it.nightId)
                viewModel.doneNavigating()
            }
        })

        viewModel.showOnSnackbarEvent.observe(viewLifecycleOwner, {
            if (it == true) {
                showSnackbar(getString(R.string.cleared_message))
                viewModel.doneShowingSnackbar()
            }
        })

        viewModel.nights.observe(viewLifecycleOwner, {
            it?.let { sleepNightAdapter.data = it }
        })
    }

    private fun navigateToQuality(nightId: Long) {
        val toQuality = SleepTrackerFragmentDirections
            .actionSleepTrackerFragmentToSleepQualityFragment(nightId)
        findNavController().navigate(toQuality)
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(
            requireActivity().findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun sleepTrackerViewModel(): SleepTrackerViewModel {
        val application = requireActivity().application
        val dao = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepTrackerViewModelFactory(dao, application)
        return ViewModelProvider(this, viewModelFactory)[SleepTrackerViewModel::class.java]
    }
}
