package it.codingjam.list

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dagger.Component
import javax.inject.Inject

interface ItemRepositoryProxy {
    fun getText(): String
}

interface ListDependencies {
    val repositoryProxy: ItemRepositoryProxy
}

@Component(
    dependencies = [ListDependencies::class]
)
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

        DaggerListComponent.builder()
            .listDependencies((application as ListDependenciesProvider).dependencies)
            .build()
            .inject(this)

        val textView = TextView(this)
        textView.text = repository.getText()
        setContentView(textView)
    }
}