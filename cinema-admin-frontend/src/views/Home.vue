<!--
 * 后台信息首页管理
 *
 * @Author: ShanZhu
 * @Date: 2023-12-16
-->
<template>
  <el-container class="home-container">
    <el-header>
      <div class="span-container" style="padding: 5px" @click="toWelcome">
        <span>&nbsp;&nbsp;</span>
        <img src="../assets/homelogo.svg" alt="" height="40px" width="40px">
        <div style="margin: 5px; font-size: 28px;font-weight: 600;color: white">电影院管理系统</div>
      </div>
      <el-button
        type="danger"
        @click="logout"
        icon="iconfont icon-login-out"
        style="background-color: black; color: white; border-color: black;">
        退出
      </el-button>


    </el-header>
    <el-container>
      <el-aside>
        <!--侧边栏菜单-->
        <el-menu
            background-color="white"
            text-color="black"
            active-text-color="grey"
            unique-opened
            :collapse="isCollapsed"
            :collapse-transition="false"
            :router="true"
            :default-active="$route.path">
          <!--一级菜单-->
          <el-submenu v-for="item in menuList"
                      :index="String(item.id)" :key="item.id">
            <!--一级菜单的模板区域-->
            <template slot="title">
              <!--图标-->
              <i :class="iconList[item.id]" style="font-size: 24px;color: #3186cb;"> {{item.name}}</i>
              <!--文本-->
              <span> </span>
            </template>
            <!--二级菜单-->
            <el-menu-item v-for="subItem in item.children"
                          :index="'/' + subItem.path" :key="subItem.id">
              <template slot="title">
                <!--图标-->
                <i class="iconfont icon-detail-button" style="font-size: 20px;color: #3186cb;"> {{ subItem.name }}</i>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>



<script>
export default {
  name: "Home",
  data() {
    return {
      //左侧菜单数据
      menuList: [],
      iconList: {
        '1': 'iconfont icon-home-menu',
        '2': 'iconfont icon-movieManage-menu',
        '3': 'iconfont icon-movieLow-menu',
        '4': 'iconfont icon-order-menu',
        '5': 'iconfont icon-user-menu',
        '6': 'iconfont icon-power-menu',
        '7': 'iconfont icon-userPower-menu',
        '8': 'iconfont icon-edit-button'
      },
    }
  },
  created(){
    this.getMenuList()
  },
  methods: {
    logout() {
      //清空sessionStorage
      window.sessionStorage.clear();
      this.$router.push('/login')
    },
    async getMenuList(){
      const loginUser = JSON.parse(window.sessionStorage.getItem("loginUser"))
      this.menuList = loginUser.sysUser.sysRole.children
      this.$router.push('/welcome')
    },
    // 菜单展开与闭合：点击事件
    toggleCollapse() {
      this.isCollapsed = !this.isCollapsed
    },
    toWelcome(){
      this.$router.push('/welcome')
    }
  }
}
</script>

<style scoped>

.home-container{
  height: 100%;
}

.el-header {
  background-color: #9ac1f0;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #3186cb;
  font-size: 20px;
  border-bottom: 1px solid grey;
}

.el-header > div{
  display: flex;
  align-items: center;
}

div > span{
  margin-left: 15px;
}

.el-aside {
  background-color: white;
  border-right: 1px grey solid;
}

.span-container{
  cursor: pointer;
}

.el-main{
  /* background: url("../assets/welcome.jpg");
  background-size: cover; */
  background-color: white;
}

.el-menu{
  border: solid 0 grey;
}
</style>
