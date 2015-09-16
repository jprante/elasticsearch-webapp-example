yieldUnescaped '<!DOCTYPE html>'
html(lang: 'en') {
  head {
      title('Web application plugin for Elasticsearch')
      meta(charset: 'utf-8')
      meta('http-equiv': 'X-UA=Compatible', content: 'IE=edge')
      meta(name: 'viewport', content: 'width=device-width, initial-scale=1.0')
      link(rel: 'icon', href: 'dist/img/elasticsearch-icon.png')
      link(rel: 'stylesheet', href: 'bower_components/bootstrap/dist/css/bootstrap.min.css')
      link(rel: 'stylesheet', href: 'bower_components/font-awesome/css/font-awesome.min.css')
style(type: 'text/css') {
   yieldUnescaped '''
/*<![CDATA[*/
/* Move down content because we have a fixed navbar that is 50px tall */
body {
  padding-top: 50px;
  padding-bottom: 20px;
}
/*
  Cat photo "Beans"
  taken from https://www.flickr.com/photos/radarsmum67/8580546427/
  created by Alison Benbow (Radarsmum67)
  licensed under Creative Commons Attribution CC-BY-2.0 http://creativecommons.org/licenses/by/2.0/
*/
.jumbotron {
    position: relative;
    background: url("dist/img/cat.jpg") center center;
    width: 100%;
    height: 100%;
    background-size: cover;
    overflow: hidden;
    color: white;
}

/*]]>*/
  '''
}
  }
  body {
    nav(class: 'navbar navbar-fixed-top') {
      div(class: 'container') {
        div(class: 'navbar-header') {
          button(type: 'button', class: 'navbar-toggle collapsed', 'data-toggle': 'collapse', 'data-target': '#navbar', 'aria-expanded': 'false', 'aria-controls': 'navbar') {
            span(class: 'sr-only') { yield 'Toggle navigation' }
            span(class: 'icon-bar')
            span(class: 'icon-bar')
            span(class: 'icon-bar')
          }
          a(class: 'navbar-brand', href: '#') { yield 'Web application plugin for Elasticsearch' }
        }
      }
    }
    div(class: 'jumbotron') {
      div(class: 'container') {
        h1 'Welcome'
        p '''
This Elasticsearch plugin enables high performance, scalable, extensible, easy-to-use web application delivery
by utilizing Elasticsearch and the bundled Groovy and Netty.
Pre-packaged with Bootstrap, Font Awesome, and jQuery.
'''
        p {
          yield 'The status of your cluster is '
          yield client.admin().cluster().prepareHealth().get().status.name()
        }
        p {
          a(class: 'btn btn-primary btn-l', href: '#', role: 'button') {
            yieldUnescaped 'Learn more &raquo;'
          }
        }
      }
    }
    div(class: 'container') {
      div(class: 'row') {
        div(class: 'col-md-12') {
          h2 ''
          p '''
Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?

At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.
'''
        }
      }
      hr
      footer {
        div(style: 'width: 160px', class: 'thumbnail') {
          a(href: 'http://www.apache.org/licenses/LICENSE-2.0', class: 'external', target: '_blank') {
            img(class: 'size-medium', title: 'Apache 2 License', src: 'dist/img/feather-small.png', alt: 'Apache Feather')
            p(class: 'caption') { yield 'All the code on this site is licensed under the Apache License 2.0' }
          }
        }
      }
    }
  }
}
