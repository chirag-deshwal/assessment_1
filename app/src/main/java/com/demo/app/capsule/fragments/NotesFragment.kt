package com.demo.app.capsule.fragments

import android.content.res.Resources
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.demo.app.capsule.R
import java.io.BufferedReader
import java.io.InputStreamReader


/** @param notes Provide the notes into HTML formatting */
class NotesFragment(private val notes : String)  : Fragment() {

    private lateinit var parent : View
    private lateinit var notesText : TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        parent = LayoutInflater.from(requireContext()).inflate(R.layout.notes_fragment, container, false)
        notesText = parent.findViewById(R.id.notesText)

        // Get a reference to the Resources object
        val resources: Resources = resources

// Specify the resource ID of the raw file
        val resourceId: Int = R.raw.notes

// Open the raw resource and read it into a string
        val inputStream = resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String? = reader.readLine()
        while (line != null) {
            stringBuilder.append(line).append("\n")
            line = reader.readLine()
        }
        val content = stringBuilder.toString()



        // Convert HTML to a Spanned object
        val spannedText = Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY)


        // Set the Spanned text to the TextView
        notesText.text = spannedText


        return parent
    }
}