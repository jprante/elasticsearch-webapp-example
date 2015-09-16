yieldUnescaped '<!DOCTYPE html>'
html(lang: 'en') {
  head {
      title {
        yield 'Extension example'
      }
  }
  body {
      p {
        yield 'This is an example of an extension: '
        yield example.printMessage()
      }
      p {
        yield 'The class name of the extension is '
        yield example.class.name
      }
  }
}
