<!--
 * 欢迎页面
 *
 * @Author: ShanZhu
 * @Date: 2023-12-16
-->
<template>
    <div id="welcome">
        <div id="bk">欢迎访问电影院管理后台</div>
    </div>
</template>

<script>
export default {
    name: "Welcome",
};
</script>

<style scoped>
#welcome {
    background: url("../assets/welcome.png");
    background-size: cover;
    height: 100%;
    width: 100%;
}
#bk {
  color: transparent; /* 将文字颜色设为透明 */
  margin: 0;
  text-align: center;
  font-size: 36px;
  padding-top: 20px;
  background: linear-gradient(to right, white, rgb(30, 30, 120)); /* 渐变背景 */
  -webkit-background-clip: text; /* 限制背景到文字部分 */
  background-clip: text; /* 为非 WebKit 内核的浏览器提供支持 */

}
</style>
