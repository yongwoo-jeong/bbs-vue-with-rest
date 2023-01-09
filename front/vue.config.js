const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  outputDir: '../src/main/resources/static',
  devServer: {
    port: 3030,
    proxy:{
      '/api': {
        target: 'http://localhost:8080'
      }
    }
  }
})
