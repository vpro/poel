
System.config( {
    baseURL: '/',
    transpiler: 'traceur',
    defaultJSExtensions: false
} );


System.config( {
    map: {
        handlebars: 'vendor/handlebars.min.js',
        'handlebars-runtime': 'vendor/handlebars.runtime.min.js',
        hbs: 'vendor/hbs.js',
        jquery: 'vendor/jquery.min.js',
        stapes: 'vendor/stapes.min.js',
        traceur: 'vendor/traceur.js'
    }
} );