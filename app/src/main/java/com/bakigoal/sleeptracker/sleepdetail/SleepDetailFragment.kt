package com.bakigoal.sleeptracker.sleepdetail

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
import com.bakigoal.sleeptracker.databinding.FragmentSleepDetailBinding

class SleepDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSleepDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sleep_detail, container, false
        )

        val arguments = SleepDetailFragmentArgs.fromBundle(requireArguments())
        val viewModel = sleepDetailViewModel(arguments.sleepNightKey)

        // To use the View Model with data binding, you have to explicitly
        // give the binding object a reference to it.
        binding.sleepDetailViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Add an Observer to the state variable for Navigating when a Quality icon is tapped.
        observeViewModel(viewModel)

        return binding.root
    }

    private fun observeViewModel(viewModel: SleepDetailViewModel) {
        viewModel.navigateToSleepTracker.observe(viewLifecycleOwner, {
            if (it == true) { // Observed state is true.
                findNavController().navigate(
                    SleepDetailFragmentDirections.actionSleepDetailFragmentToSleepTrackerFragment()
                )
                // Reset state to make sure we only navigate once, even if the device
                // has a configuration change.
                viewModel.doneNavigating()
            }
        })
    }

    private fun sleepDetailViewModel(sleepNightKey: Long): SleepDetailViewModel {
        // Create an instance of the ViewModel Factory.
        val application = requireActivity().application
        val dao = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepDetailViewModelFactory(sleepNightKey, dao)

        // Get a reference to the ViewModel associated with this fragment.
        return ViewModelProvider(this, viewModelFactory).get(SleepDetailViewModel::class.java)
    }
}