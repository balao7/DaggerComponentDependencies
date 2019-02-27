package it.codingjam.daggercomponentdependencies

import dagger.Component
import dagger.Module
import dagger.Provides
import it.codingjam.list.ItemRepositoryProxy
import it.codingjam.list.ListDependencies
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideRepositoryProxy(repository: ItemRepository): ItemRepositoryProxy {
        return object : ItemRepositoryProxy {
            override fun getText() = repository.list().toString()
        }
    }
}

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent : ListDependencies {
    val repository: ItemRepository
}