const { defineConfig } = require('@vue/cli-service');
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 8080,
    proxy: {
      //proxy 요청을 보낼 api 시작 부분
      '^/api': {
        target: 'https://localhost:80',
        changeOrigin: true,
      },
    },
  },
});

module.exports = {
  lintOnSave: false,
};
