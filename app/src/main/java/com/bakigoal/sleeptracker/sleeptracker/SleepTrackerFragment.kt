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

    private lateinit var viewModel: SleepTrackerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSleepTrackerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sleep_tracker, container, false
        )

        viewModel = sleepTrackerViewModel()
        binding.sleepTrackerViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.navigateToSleepQuality.observe(viewLifecycleOwner, {
            it?.let {
                val toQuality = SleepTrackerFragmentDirections
                    .actionSleepTrackerFragmentToSleepQualityFragment(it.nightId)
                findNavController().navigate(toQuality)
                viewModel.doneNavigating()
            }
        })

        viewModel.showOnSnackbarEvent.observe(viewLifecycleOwner, {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.doneShowingSnackbar()
            }
        })
    }

    private fun sleepTrackerViewModel(): SleepTrackerViewModel {
        val application = requireActivity().application
        val dao = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepTrackerViewModelFactory(dao, application)
        return ViewModelProvider(this, viewModelFactory)[SleepTrackerViewModel::class.java]
    }
}
