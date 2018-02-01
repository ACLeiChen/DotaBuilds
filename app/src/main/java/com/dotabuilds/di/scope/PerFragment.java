package com.dotabuilds.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Lei Chen on 2018/1/31.
 */
@Scope
@Retention(RUNTIME)
public @interface PerFragment {
}
