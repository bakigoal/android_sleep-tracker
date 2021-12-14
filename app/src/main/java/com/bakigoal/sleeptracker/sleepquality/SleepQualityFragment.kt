package com.bakigoal.sleeptracker.sleepquality

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bakigoal.sleeptracker.R
import com.bakigoal.sleeptracker.database.SleepDatabase
import com.bakigoal.sleeptracker.databinding.FragmentSleepQualityBinding

/**
 * Fragment that displays a list of clickable icons,
 * each representing a sleep quality rating.
 * Once the user taps an icon, the quality is set in the current sleepNight
 * and the database is updated.
 */
class SleepQualityFragment : Fragment() {

    /**
     * Called when the Fragment is ready to display content to the screen.
     *
     * This function uses DataBindingUtil to inflate R.layout.fragment_sleep_quality.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSleepQualityBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sleep_quality, container, false
        )

        val args: SleepQualityFragmentArgs by navArgs()

        // Create and bind ViewModel
        val viewModel = sleepQualityViewModel(args.sleepNightKey)
        binding.sleepQualityViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // add Observers to ViewModel
        observeViewModel(viewModel)

        return binding.root
    }

    private fun observeViewModel(viewModel: SleepQualityViewModel) {
        viewModel.navigateToSleepTracker.observe(viewLifecycleOwner, {
            if (it == true) {
                findNavController().navigate(SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
                viewModel.doneNavigating()
            }
        })
    }

    private fun sleepQualityViewModel(sleepNightKey: Long): SleepQualityViewModel {
        val application = requireActivity().application
        val dao = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepQualityViewModelFactory(sleepNightKey, dao)
        return ViewModelProvider(this, viewModelFactory)[SleepQualityViewModel::class.java]
    }
}
