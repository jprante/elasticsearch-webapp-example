package com.example

import org.elasticsearch.common.inject.Inject
import org.elasticsearch.common.settings.Settings
import org.xbib.elasticsearch.plugin.webapp.WebappPlugin
import org.xbib.elasticsearch.webapp.Constants
import org.xbib.elasticsearch.module.webapp.WebappModule
import com.example.extensions.ExampleExtension

class MyWebappPlugin extends WebappPlugin implements Constants {

    final static String NAME = 'webapp'

    @Inject
    MyWebappPlugin(Settings settings) {
        super(settings)
    }

    @Override
    String name() {
        NAME
    }

    void onModule(WebappModule module) {
        module.addExtension(ExampleExtension)
    }

}