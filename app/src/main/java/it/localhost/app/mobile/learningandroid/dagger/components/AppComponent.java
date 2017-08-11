package it.localhost.app.mobile.learningandroid.dagger.components;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import it.localhost.app.mobile.learningandroid.App;
import it.localhost.app.mobile.learningandroid.dagger.modules.ActivityBuilderModule;
import it.localhost.app.mobile.learningandroid.dagger.modules.AppModule;

/**
 * Dagger Component.<br>
 * I Component dicono dove effettuare l'injection dei moduli.<br>
 */
@Singleton
@Component(
        modules = {
                AndroidInjectionModule.class,
                ActivityBuilderModule.class,
                AppModule.class
        }
)
public interface AppComponent {

    // Nuovo Builder per Dagger > 2.10
    // Per ogni Component è possibile creare un builder che ritorna il Component
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(App application);

        AppComponent build();
    }

    void inject(App app);
}
