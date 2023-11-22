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
      host: '0.0.0.0',
      public: 'http://192.168.205.92:8080/', // 혹은 사용하는 주소
      disableHostCheck: true,
    },
  },
});

module.exports = {
  lintOnSave: false,
};
