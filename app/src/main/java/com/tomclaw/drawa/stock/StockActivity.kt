package com.tomclaw.drawa.stock

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tomclaw.drawa.R
import com.tomclaw.drawa.main.getComponent
import com.tomclaw.drawa.stock.di.StockModule
import com.tomclaw.drawa.util.DataProvider
import javax.inject.Inject

class StockActivity : AppCompatActivity(), StockPresenter.StockRouter {

    @Inject
    lateinit var presenter: StockPresenter

    @Inject
    lateinit var dataProvider: DataProvider<StockItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        val presenterState = savedInstanceState?.getBundle(KEY_PRESENTER_STATE)
        application.getComponent()
                .stockComponent(StockModule(presenterState))
                .inject(activity = this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.stock)

        val adapter = StockAdapter(this, dataProvider)
        val view = StockViewImpl(window.decorView, adapter)

        presenter.attachView(view)
    }

    override fun onStart() {
        super.onStart()
        presenter.attachRouter(this)
    }

    override fun onStop() {
        presenter.detachRouter()
        super.onStop()
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBundle(KEY_PRESENTER_STATE, presenter.saveState())
    }

    override fun openDrawingScreen(item: StockItem) {
    }

}

private const val KEY_PRESENTER_STATE = "presenter_state"