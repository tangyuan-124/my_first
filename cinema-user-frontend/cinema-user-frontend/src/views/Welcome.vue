<!--
 * 欢迎页面
 *
 * @Author: ShanZhu
 * @Date: 2023-12-16
-->
<template>
  <el-container>
    <el-header>
      <div class="header-inner">
        <a href="/welcome" style="text-decoration: none;">

          <div style="color: #efe8e8; font-size: 26px;margin-top: 20px;">
            <!-- <img style="height: 30px; margin-bottom: -5px;" src="../assets/homelogo.svg"> -->
            <span style="color: white; font-weight: bold;">SSECINEMA</span>
          </div>
        </a>
        <el-menu
          :default-active="activeUrl"
          class="nav-menu"
          mode="horizontal"
          :router="true"
          active-text-color="#ffffff"
          text-color="#ffffff">

          <el-menu-item :index="item.path" v-for="item in menuList" :key="item.id">{{item.name}}</el-menu-item>
        </el-menu>
        <div class="searchContainer">
          <el-input v-model="kw" class="searchBar" placeholder="搜索电影"></el-input>
          <el-button id="searchBtn" icon="iconfont el-icon-search"  circle @click="search"></el-button>
        </div>
        <el-dropdown @command="handleCommand">
          <span class="el-dropdown-link">
            <el-avatar :src="url" :size="50">{{url === '' || url === null ? 'user': ''}}</el-avatar>
            <i class="el-icon-arrow-down el-icon--right icon-arrow"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="userMenu" v-if="isToken">个人中心</el-dropdown-item>
            <el-dropdown-item command="logout" v-if="isToken">退出</el-dropdown-item>
            <el-dropdown-item command="login" v-if="!isToken">登录</el-dropdown-item>
            <el-dropdown-item command="register" v-if="!isToken">注册账号</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>
    <el-footer>
      <div class="footer-mini"></div>
      <div class="footer" style="padding-bottom: 15px;">

      </div>
      <el-backtop></el-backtop>
    </el-footer>
  </el-container>
</template>

<script>
export default {
  name: "Welcome",
  data() {
    return {
      isToken: '',
      cinemaInfo: '',
      url: '',
      activeUrl: this.$route.path.substring(0, this.$route.path.indexOf('/',1) === -1 ? this.$route.path.length : this.$route.path.indexOf('/',1)),
      menuList: [
        {
          id: 1,
          name: '首页',
          path: '/home'
        },
        {
          id: 2,
          name: '影片',
          path: '/movie'
        },
        {
          id: 3,
          name: '榜单',
          path: '/rankingList'
        },
        {
          id: 4,
          name: '关于',
          path: '/aboutUs'
        }
      ],
      //搜索关键字
      kw: ''
    }
  },
  created() {
    this.getCinemaInfo()
    this.isToken = window.sessionStorage.getItem("token")
    try {
      const loginUser = window.sessionStorage.getItem('loginUser')
      if (loginUser) {
        const userInfo = JSON.parse(loginUser)
        if (userInfo && userInfo.userPicture) {
          const picture = JSON.parse(userInfo.userPicture)
          if (picture && picture.length > 0) {
            this.url = this.global.base + picture[0]
          }
        }
      }
    } catch (e) {
      console.error('Error loading user picture:', e)
    }
  },
  watch: {
    '$route'() {
      this.activeUrl = this.$route.path.substring(0, this.$route.path.indexOf('/',1) === -1 ? this.$route.path.length : this.$route.path.indexOf('/',1))
    }
  },
  methods: {
    async getCinemaInfo(){
      const _this = this
      await axios.get('sysCinema').then(resp => {
        _this.cinemaInfo = resp.data.data
      })
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    search() {
      let tmp = this.kw
      this.$router.push('/search/searchMovie?kw=' + tmp)
      this.kw = ''
    },
    handleCommand(command) {
      if(command === 'logout') {
        window.sessionStorage.clear();
        return this.$router.push('/login')
      }
      this.$router.push('/' + command)
    },
    toWelcome() {
      this.$router.push('/welcome')
    }
  }
}
</script>

<style scoped>

.header-inner{
  width: 75%;
  margin: 0 12.5%;
  display: flex;
}

.logo{
  height: 80px;
  width: 200px;
}

/* 导航栏样式 */
.el-header {
  height: 80px !important;

  background-color: #92adbf;
  border-bottom: none; /* 移除底部边框 */
}

/* 导航菜单容器样式 */
.nav-menu {

  background-color: transparent; /* 透明背景 */
  display: flex; /* 使用flex布局 */
  justify-content: space-around; /* 使子元素（导航项）均匀分布 */
  align-items: center; /* 垂直居中 */
  width: 80%; /* 使容器宽度占满父元素 */
  border-bottom: 0px solid #eee !important; /* 移除下边框 */
  height: 60px;
  margin: 0; /* 移除外边距 */
}

/* 导航项样式 */
.el-menu-item {
  font-size: 20px;
  background-color: transparent; /* 去除背景色 */
  border: none; /* 去除边框 */
  margin: 0; /* 移除默认的内外边距 */
  border-radius: 5px; /* 设置圆角 */
  transition: background-color 0.3s; /* 平滑过渡背景色变化 */
}

/* 导航项悬停效果 */
.el-menu-item:hover {
  color: #ffffff !important; /* 文字颜色 */
  background-color: #bec2ca !important; /* 背景色 */
  border-radius: 5px; /* 圆角 */
}
.el-menu-item.is-active {
  background-color: #92adbf !important;
}
.searchContainer{
  padding: 20px 0;
  display: flex;
}

.searchBar >>> input{
  border-radius: 50px;
}

#searchBtn{
  margin: 0; /* 移除默认的内外边距 */
  background-color: #92adbf !important;
  transform: translate(-100%, 0);
}

.el-dropdown-link{
  width: 70px;
  margin: 15px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.icon-arrow {
  transition: all 0.2s ease-in-out;
  -webkit-transition: all 0.2s ease-in-out;
  -moz-transition: all 0.2s ease-in-out;
  -o-transition: all 0.2s ease-in-out;
}

.el-dropdown-link:hover .icon-arrow{
  /* 定义动画的状态 */
  -webkit-transform: rotate(180deg) scale(1);
  -moz-transform: rotate(180deg) scale(1);
  -o-transform: rotate(180deg) scale(1);
  -transform: rotate(180deg) scale(1);
}

.footer-mini{
  border-top: 1px solid #EEE;
  padding-top: 20px;
  text-align: center;
}

.footer{
  margin-top: 20px;
  display: flex;
  justify-content: center;
}

.el-main{
  padding: 0px;
}
</style>
