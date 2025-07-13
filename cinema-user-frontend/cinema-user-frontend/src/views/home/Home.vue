<!--
* 首页
*
* @Author: ShanZhu
* @Date: 2023-12-16
-->
<template>
  <div>
    <div class="whole">
      <div class="left">
        <!-- 新增的轮播组件 -->
        <div class="carousel">
          <el-carousel :interval="3000" arrow="always" height="300px">
            <el-carousel-item v-for="(poster, index) in posters" :key="index">
              <img :src="poster" alt="movie poster" class="carousel-image" />
            </el-carousel-item>
          </el-carousel>

        </div>

        <!-- 热播中 -->
        <div class="panel">
          <div class="panel-header">
            <div style="color: #eb6b51;font-size: 28px;">
              <i class="iconfont icon-r-team" style="font-size: 36px;"></i>
              <b>热映中</b>
            </div>
            <!-- 将“全部”文本放入<a>标签中 -->
            <a href="/movie/movieOngoing" style="text-decoration: none; color: #999;">全部</a>
          </div>
          <div class="panel-content">
            <movie-item :movieItem="item" v-for="(item, index) in ongoingMovieList" :key="index"></movie-item>
          </div>
        </div>

        <div class="panel">
          <div class="panel-header">
            <div style="color: #9dbcb8;font-size: 28px;">
              <i class="iconfont icon-r-refresh" style="font-size: 30px;"></i>
              <b>即将上映</b>
            </div>
            <!-- 将“全部”文本放入<a>标签中 -->
            <a href="/movie/movieUpcoming" style="text-decoration: none; color: #999;">全部</a>
          </div>
          <div class="panel-content">
            <movie-item :movieItem="item" v-for="(item, index) in upcomingMovieList" :key="index"></movie-item>
          </div>
        </div>

        <!-- 热播影片 -->
          <div class="panel">
            <div class="panel-header">
              <div style="color: #92adbf;font-size: 28px;">
                <i class="iconfont icon-r-love" style="font-size: 30px;"> </i>
                <b> 热播影片</b>
              </div>
              <a href="/movie/movieClassics">全部</a>
            </div>
            <div class="panel-content">
              <div class="panel-content">
                <movie-item :movieItem="item" v-for="(item,index) in classicMovieList" :key="index"></movie-item>
              </div>
            </div>
          </div>
        </div>

      <div class="right">
        <div class="panel">
          <div class="panel-header">
            <div style="color: #ffb400;font-size: 28px;">
              <i class="iconfont icon-r-paper" style="font-size: 28px;"></i>
              <b>票房榜</b>
            </div>
            <!-- 确保这是一个链接，并且href属性设置为正确的路径 -->
            <a href="/rankingList/totalBoxOfficeList" style="text-decoration: none; color: #999;">查看完整榜单</a>

          </div>
          <div class="panel-content">
            <div class="board" v-for="(item, index) in totalBoxOfficeList" :key="index">
              <div class="board-left">
                <i class="board-index">{{index+1}}</i>
              </div>
              <div class="board-middle">
                <a :href="'/movieInfo/' + item.movieId">
                  <p class="name">{{ item.movieName }}</p >
                </a >
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import movieItem from '../../components/movie/movie-item';
import moment from 'moment';


export default {
  name: "Home",
  components: {
    movieItem
  },
  data() {
    return {
      posters:[
        "/images/poster1.jpg",
        "/images/poster2.jpg",
        "/images/poster3.jpg",
        "/images/poster4.jpg",
        "/images/poster5.jpg",
      ],


      queryInfo1: {
        total: 0,
        pageSize: 8,
        pageNum: 1,
        startDate: moment().subtract(365, "days").format("YYYY-MM-DD"),
        endDate: moment().format('YYYY-MM-DD'),
      },
      queryInfo2: {
        total: 0,
        pageSize: 8,
        pageNum: 1,
        startDate: moment().format('YYYY-MM-DD')
      },
      queryInfo3: {
        total: 0,
        pageSize: 8,
        pageNum: 1
      },
      queryInfo4: {
        pageSize: 10,
        pageNum: 1
      },
      ongoingMovieList: [],
      upcomingMovieList: [],
      classicMovieList: [],
      totalBoxOfficeList: []
    };
  },
  created() {
    this.getOngoingMovieList();
    this.getUpcomingMovieList();
    this.getClassicMovieList();
    this.getTotalBoxOfficeList();
  },
  methods: {
    async getOngoingMovieList() {
      const { data: res } = await axios.get('sysMovie/find', { params: this.queryInfo1 });
      console.log(res.data); // 检查 res.data 的内容
      this.ongoingMovieList = res.data;
    },
    async getUpcomingMovieList() {
      const { data: res } = await axios.get('sysMovie/find', { params: this.queryInfo2 });
      this.upcomingMovieList = res.data;
    },
    async getClassicMovieList() {
      const { data: res } = await axios.get('sysMovie/find', { params: this.queryInfo3 });
      this.classicMovieList = res.data;
    },
    async getTotalBoxOfficeList() {
      const { data: resp } = await axios.get('sysMovie/find/rankingList/1', { params: this.queryInfo4 });
      if (resp.code !== 200) return this.$message.error(resp.msg);
      this.totalBoxOfficeList = resp.data;
    }
  }
};
</script>

<style scoped>
.carousel {
  margin-bottom: 20px; /* 轮播组件和下面内容的间距 */
}

.carousel-image {

  width: 100%;        /* 设置图片宽度为容器的宽度 */
  height: 300px;      /* 固定高度 */
  object-fit: cover;  /* 适应容器，不会拉伸图片 */
  border-radius: 10px; /* 可选：圆角 */
}

.el-carousel {
  width: 100%;
  margin: 20px auto;
}

.el-carousel__item > img {
  width: 100%;
  height: auto;
}

.whole {
  width: 1200px;
  margin: 30px auto;
  display: flex;
}

.left {
  width: 70%;
}

.right {
  width: 30%;
  margin-left: 100px;
}

h2 {
  font-size: 26px;
}

.panel-header > a {
  text-align: center;
  text-decoration: none;
  color: #999;
  padding-right: 14px;
}

.panel-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.movie-item {
  margin-left: 0;
  margin-right: 30px;
}

.movie-item:nth-child(4n) {
  margin-right: 0;
}

.board {
  display: flex;
  margin: 10px 10px;
  transition: background-color 0.3s; /* 添加过渡效果 */
}

.board:hover {
  background-color: #f0f0f0; /* 悬停时的背景颜色 */
}

.board-left {
  display: flex;
  align-items: center;
}

.board-middle {
  display: flex;
  margin-left: 10px;
  width: 150px;
  font-size: 18px;
}

.board-middle > a {
  text-decoration: none;
  color: #333;
  transition: color 0.3s; /* 添加颜色变化的过渡效果 */
}

.board-middle > a:hover {
  color: #92adbf; /* 悬停时的链接颜色 */
}

.board-index {
  color: #ffb400;
  display: inline-block;
  width: 50px;
  height: 50px;
  line-height: 50px;
  text-align: center;
  font-size: 18px;
}


.panel-content {
  margin: 0px 0px 50px 0px;
}
</style>
