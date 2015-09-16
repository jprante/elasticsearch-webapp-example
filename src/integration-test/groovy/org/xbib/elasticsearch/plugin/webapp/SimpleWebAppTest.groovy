package org.xbib.elasticsearch.plugin.webapp

import geb.Browser
import org.junit.Test
import org.xbib.elasticsearch.plugin.webapp.helper.AbstractNodeTestHelper

class SimpleWebAppTest extends AbstractNodeTestHelper {

    @Test
    void simpleWebappTest() {

        Browser.drive {
            go "http://127.0.0.1:9200/_webapp/"

            Thread.sleep(15000L)

            /*
            assert title == "Geb - Very Groovy Browser Automation"
            $("#sidebar .sidemenu a", text: "jQuery-like API").click()
            assert $("#main h1")*.text() == ["Navigating Content", "Form Control Shortcuts"]
            assert $("#sidebar .sidemenu a", text: "jQuery-like API").parent().hasClass("selected")
             */
        }
    }
}
