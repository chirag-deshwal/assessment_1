package com.demo.app.capsule.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.demo.app.capsule.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class TutorialFragment (private val ytVideoID : String)  : Fragment() {

    private lateinit var parent : View
    private lateinit var videoPlayer : YouTubePlayerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        parent = LayoutInflater.from(requireContext()).inflate(R.layout.tutorial_fragment, container, false)
        videoPlayer = parent.findViewById(R.id.ytVideoPlayer)

        lifecycle.addObserver(videoPlayer)

        videoPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(ytVideoID, 0f)
                youTubePlayer.pause()
            }
        })

        return parent
    }

}