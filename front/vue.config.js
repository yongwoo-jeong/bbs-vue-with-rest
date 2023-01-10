const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  outputDir: "../main/resources/static",
  publicPath: "/api/v1",
  devServer: {
    proxy: {
      "/api": {
        target: "http://localhost:8080",
      },
    },
  },
});
