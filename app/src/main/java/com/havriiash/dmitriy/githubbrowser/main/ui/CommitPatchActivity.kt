package com.havriiash.dmitriy.githubbrowser.main.ui

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.havriiash.dmitriy.githubbrowser.R
import com.havriiash.dmitriy.githubbrowser.databinding.ActivityCommitPatchBinding
import com.havriiash.dmitriy.githubbrowser.main.ui.adapters.CommitPatchAdapter
import com.havriiash.dmitriy.githubbrowser.main.ui.base.BaseActivity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CommitPatchActivity : BaseActivity() {

    companion object {
        private const val FILE_NAME_PARAM = "CommitPatchActivity.Params.FileName"
        private const val PATCH_PARAM = "CommitPatchActivity.Params.Patch"

        fun showCommitPatch(context: Context, patch: String, fileName: String) {
            val intent = Intent(context, CommitPatchActivity::class.java)
            intent.putExtra(PATCH_PARAM, patch)
            intent.putExtra(FILE_NAME_PARAM, fileName)
            context.startActivity(intent)
        }
    }


    private lateinit var binding: ActivityCommitPatchBinding
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_commit_patch)

        setupUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupUI() {
        setupToolbar()
        binding.activityCommitPatchLrv?.layoutRecyclerViewSwipeRefresh?.setOnRefreshListener { parsePatch() }
        binding.activityCommitPatchLrv?.layoutRecyclerViewSwipeRefresh?.setColorSchemeResources(R.color.colorPrimary)
        parsePatch()
    }

    private fun setupToolbar() {
        val fullFileName = intent.getStringExtra(FILE_NAME_PARAM)
        val slashIndex = fullFileName.lastIndexOf("/")
        var fileName = fullFileName
        if (slashIndex != -1) {
            fileName = fullFileName.substring(slashIndex + 1)
        }

        setSupportActionBar(binding.activityCommitPatchToolbar)
        supportActionBar?.title = fileName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    private fun parsePatch() {
        disposable.add(
                Single.just(intent.getStringExtra(PATCH_PARAM).split("\n"))
                        .doOnSubscribe { showProgress(true) }
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { patches ->
                                    showProgress(false)
                                    binding.activityCommitPatchLrv?.layoutRecyclerViewRv?.adapter = CommitPatchAdapter(patches)
                                },
                                { throwable ->
                                    showProgress(false)
                                    showError(throwable.message!!)
                                }
                        )
        )
    }

    override fun showProgress(progress: Boolean) {
        binding.activityCommitPatchLrv?.layoutRecyclerViewSwipeRefresh?.isRefreshing = progress
    }
}