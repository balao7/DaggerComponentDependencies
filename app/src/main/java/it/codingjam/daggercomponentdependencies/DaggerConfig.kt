package it.codingjam.daggercomponentdependencies

import dagger.Component
import dagger.Module
import dagger.Provides
import it.codingjam.list.ItemRepositoryProxy
import it.codingjam.list.ListDependencies
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepositoryProxyImpl @Inject constructor(private val repository: ItemRepository) : ItemRepositoryProxy {
    override fun getText() = repository.list().toString()
}

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRepositoryProxy(impl: ItemRepositoryProxyImpl): ItemRepositoryProxy = impl
}

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : ListDependencies {
    val repository: ItemRepository
}