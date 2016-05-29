var path = require('path');
var Builder = require('systemjs-builder');
var baseURL = __dirname +'/src/main/resources/static';

var builder = new Builder();

builder.loadConfig( baseURL +'/systemjs.config.js' ).then( function () {

    builder.config({
        baseURL: baseURL +'/'
    });

    builder
        .buildStatic( baseURL +'/js/impl.js', __dirname + '/target/classes/static/js/impl.sfx.js', {
            minify: true
        })
        .then(function () {
            console.log('Build complete');
            process.exit(0);
        })
        .catch(function (err) {
            console.log('Build error');
            console.log(err);
            process.exit(1);
        });

});

