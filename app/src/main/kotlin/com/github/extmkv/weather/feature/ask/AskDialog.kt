package com.github.extmkv.weather.feature.ask

import ai.api.AIConfiguration
import ai.api.AIListener
import ai.api.android.AIService
import ai.api.model.AIError
import ai.api.model.AIResponse
import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.github.extmkv.weather.BuildConfig
import com.github.extmkv.weather.R
import com.github.extmkv.weather.base.mvp.DialogMVP
import com.github.extmkv.weather.model.ResultQuery
import com.massivedisaster.adal.permissions.PermissionsManager
import kotlinx.android.synthetic.main.dialog_ask.*


class AskDialog : DialogMVP<AskContract.Presenter>(), AskContract.View, AIListener {

    companion object {
        const val REQUEST_CODE = 2001
        const val RESULT_QUERY = "RESULT_QUERY"
    }

    private lateinit var aiService: AIService
    private lateinit var permissionsManager: PermissionsManager

    override fun layoutToInflate(): Int = R.layout.dialog_ask

    override fun createPresenter(): AskContract.Presenter = AskPresenterImpl(this)

    override fun onCreate() {
        btnDismiss.setOnClickListener { dismiss() }
        imgMic.setOnClickListener { startListening() }

        val config = ai.api.android.AIConfiguration(BuildConfig.AI_CLIENT_ACCESS_TOKEN,
                AIConfiguration.SupportedLanguages.English,
                ai.api.android.AIConfiguration.RecognitionEngine.System)

        aiService = AIService.getService(requireContext(), config)
        aiService.setListener(this)

        permissionsManager = PermissionsManager(this)

        startListening()
    }

    private fun startListening() {
        permissionsManager.requestPermissions(object : PermissionsManager.OnPermissionsListener {
            override fun onGranted() {
                aiService.startListening()
            }

            override fun onDenied(neverAskMeAgain: Boolean) {
                showMic()
            }
        }, Manifest.permission.RECORD_AUDIO)
    }

    override fun onDismiss(dialog: DialogInterface?) {
        aiService.stopListening()
        aiService.cancel()
        super.onDismiss(dialog)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permissionsManager.onPermissionResult(requestCode)
    }

    override fun onResult(response: AIResponse) {
        val queryResult = response.result

        // Get parameters
        //TODO
        var parameterString = ""
        if (queryResult.parameters != null && !queryResult.parameters.isEmpty()) {
            for ((key, value) in queryResult.parameters) {
                parameterString += "($key, $value) "
            }

            val result = ResultQuery()
            result.setValues(queryResult)

            sendResult(result)

            Toast.makeText(requireContext(),
                    "Query:" + queryResult.resolvedQuery +
                            "\nAction: " + queryResult.action +
                            "\nParameters: " + parameterString, Toast.LENGTH_LONG).show()

            dismiss()
        }

        showMic()
    }

    private fun showMic() {
        txtMessage.setText(R.string.tap_and_ask)
        ltnSun.visibility = View.GONE
        imgMic.visibility = View.VISIBLE
    }

    override fun onError(error: AIError) {
        showMic()

        Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onListeningStarted() {
        txtMessage.setText(R.string.listening)
        ltnSun.visibility = View.VISIBLE
        imgMic.visibility = View.GONE
    }

    private fun sendResult(result: ResultQuery) {
        val intent = Intent()
        intent.putExtra(RESULT_QUERY, result)
        targetFragment?.onActivityResult(
                targetRequestCode, REQUEST_CODE, intent)
    }

    override fun onAudioLevel(level: Float) {
    }

    override fun onListeningCanceled() {
    }

    override fun onListeningFinished() {
    }
}