package it.codingjam.daggercomponentdependencies

import android.app.Application
import it.codingjam.list.ListDependencies
import it.codingjam.list.ListDependenciesProvider

class MyApp : Application(), ListDependenciesProvider {

    private val appComponent = DaggerAppComponent.create()

    override val dependencies: ListDependencies
        get() = appComponent

}