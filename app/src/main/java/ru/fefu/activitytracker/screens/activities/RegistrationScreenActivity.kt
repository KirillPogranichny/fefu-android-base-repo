package ru.fefu.activitytracker.screens.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ArrayAdapter
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityRegistrationBinding


class RegistrationScreenActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.goBackButton.setOnClickListener{
            finish()
        }
    }

    fun sexChoseDropDownMenu(view : View) {
        val sexes = listOf(
            getString(R.string.sex_man),
            getString(R.string.sex_woman),
            getString(R.string.sex_other))

        val inputLayout = bindingClass.sexInputLayout

        val adapter = ArrayAdapter(baseContext, R.layout.items_list, sexes)
        (inputLayout.editText as? MaterialAutoCompleteTextView)?.setAdapter(adapter)
    }

    fun enableLinks(view : View) {
        bindingClass.footerText.movementMethod = LinkMovementMethod.getInstance()
    }
}