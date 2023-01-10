const { defineConfig } = require("@vue/cli-service");
module.exports = defineConfig({
  transpileDependencies: true,
  outputDir: "../main/resources/static",
  devServer: {
    proxy: {
      "/api/v1": {
        target: "http://localhost:8080",
      },
    },
  },
});
