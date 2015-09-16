package com.example.extensions

import org.xbib.elasticsearch.webapp.WebappAdapter

class ExampleExtension extends WebappAdapter {

    @Override
    String name() {
        'example'
    }

    @Override
    ExampleExtension object() {
        this
    }

    String printMessage() {
        'This is a message from the example extension!'
    }
}
