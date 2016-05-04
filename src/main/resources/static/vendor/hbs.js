'use strict';

import Handlebars from 'handlebars';

export function translate(load) {
  var precompiled = Handlebars.precompile(load.source);
  load.source = `module.exports = require('handlebars-runtime').template(${precompiled});`;
}
