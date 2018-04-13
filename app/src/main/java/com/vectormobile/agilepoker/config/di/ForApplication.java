package com.vectormobile.agilepoker.config.di;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by daniel on 3/3/17.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ForApplication {
}
