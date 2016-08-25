'use strict';
const webpack = require('webpack');
module.exports = {
  entry: ['babel-polyfill', './js/index.js'],
  output: {
    path: './app/src/main/res/raw',
    filename: 'extract.js'
  },

  module: {
    loaders: [{
      test: /\.js$/,
      loader: 'babel',
      include: /js/,
      query: {
        presets: ['es2015'],
      }
    }]
  }
}
