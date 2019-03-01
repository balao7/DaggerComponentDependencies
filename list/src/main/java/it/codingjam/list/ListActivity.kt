package it.codingjam.list

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

interface ItemRepositoryProxy {
    fun getText(): String
}

interface ListDependencies {
    val repositoryProxy: ItemRepositoryProxy
}

@Component(
    dependencies = [ListDependencies::class]
)
@Singleton
interface ListComponent {
    fun inject(listActivity: ListActivity)
}

interface ListDependenciesProvider {
    val dependencies: ListDependencies
}

class ListActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: ItemRepositoryProxy

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dependencies = (application as ListDependenciesProvider).dependencies
        DaggerListComponent.builder()
            .listDependencies(dependencies)
            .build()
            .inject(this)

        val textView = TextView(this)
        textView.text = repository.getText()
        setContentView(textView)
    }
}