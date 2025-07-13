
module.exports = {
    devServer: {
        port: 9233
    },
    publicPath:process.env.NODE_ENV === 'production' ? '/电影院后台管理' : '/'
}
