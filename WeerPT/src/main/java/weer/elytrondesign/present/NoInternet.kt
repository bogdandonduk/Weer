package weer.elytrondesign.present

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import weer.elytrondesign.R
import weer.elytrondesign.core.AppLoader

class NoInternet() : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(AppLoader.context)
            .setView(R.layout.fragment_no_internet)
            .create()
    }
}