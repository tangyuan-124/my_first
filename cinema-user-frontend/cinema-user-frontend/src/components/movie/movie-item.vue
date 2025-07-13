<template>
  <!-- 一部电影 -->
  <div class="movie-item">
    <div v-on:mouseover="isHover = true" v-on:mouseleave="isHover = false">
      <!-- 海报 -->
      <a :href="movieInfoUrl">
        <img class="poster" :src="this.global.base + movieItem.moviePoster" />
      </a>
      <!-- 鼠标划过时的效果 -->
      <div class="movie-item-hover" v-if="isHover">
        <a :href="movieInfoUrl">
          <img class="poster-hover" :src="this.global.base + movieItem.moviePoster" />
          <div class="movie-hover-info">
            <div class="title-hover">
              <span class="name-tags">{{ movieItem.movieName }}</span>
            </div>
            <div class="title-hover">
              <span class="name-tags">类型: </span>{{ movieItem.movieCategoryList }}
            </div>
            <div class="title-hover">
              <span class="name-tags">上映时间: </span>{{ movieItem.releaseDate }}
            </div>
          </div>
        </a>
      </div>
    </div>

    <!-- 评分（添加到右上角） -->


    <!-- 标题 -->
    <div class="title-style">
      <a :href="movieInfoUrl">{{ movieItem.movieName }}</a>
    </div>
  </div>
</template>

<script>
import moment from 'moment';
export default {
  name: "movie-item",
  props: {
    movieItem: Object
  },
  data() {
    return {
      isHover: false,
      movieInfoUrl: ''
    };
  },
  created() {
    // 格式化电影海报路径
    this.movieItem.moviePoster = JSON.parse(this.movieItem.moviePoster)[0];
    this.movieItem.moviePictures = JSON.parse(this.movieItem.moviePictures);
    this.movieItem.releaseDate = moment(this.movieItem.releaseDate).format('YYYY-MM-DD');

    // 格式化电影类别
    this.movieItem.movieCategoryList = this.movieItem.movieCategoryList.map((obj, index) => {
      return obj.movieCategoryName;
    }).join("/");

    // 确保评分字段正确赋值
    this.movieItem.movieScores = this.movieItem.movieScores || "暂无评分";

    // 赋值目标链接
    this.movieInfoUrl = '/movieInfo/' + this.movieItem.movieId;
  },
  watch: {
    'movieItem'() {
      this.movieItem.moviePoster = JSON.parse(this.movieItem.moviePoster)[0];
      this.movieItem.moviePictures = JSON.parse(this.movieItem.moviePictures);
      this.movieItem.releaseDate = moment(this.movieItem.releaseDate).format('YYYY-MM-DD');
      this.movieItem.movieCategoryList = this.movieItem.movieCategoryList.map((obj, index) => {
        return obj.movieCategoryName;
      }).join("/");
      this.movieItem.movieScores = this.movieItem.scores || "暂无评分"; // 默认值
      this.movieInfoUrl = '/movieInfo/' + this.movieItem.movieId;
    }
  }
};
</script>

<style scoped>
  .movie-item {
    margin-top: 30px;
    margin-left: 30px;
    display: inline-block;
    vertical-align: top;
    position: relative;
  }

  .poster {
    width: 160px;
    height: 220px;
  }

  .title-style {
    width: 160px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    text-align: center;
    font-size: 16px;
    color: #333;
    margin-top: 10px;
  }

  .score {
    position: absolute;
    top: 10px;
    right: 10px;
    font-size: 24px; /* 字体大小 */
    color: #ffd700; /* 金色字体颜色 */
    font-weight: bold;
    font-style: italic; /* 斜体 */
    font-family: 'Arial Black', Gadget, sans-serif; /* 字体家族 */
    text-shadow:
        1px 1px 2px #c0a080, /* 阴影1 */
        2px 2px 4px #a08000, /* 阴影2 */
        3px 3px 6px #804000; /* 阴影3 */
    letter-spacing: 1px; /* 字间距 */
  }


  .movie-score {
    position: absolute;
    top: 10px;
    right: 10px;
    background-color: rgba(0, 0, 0, 0.6);
    color: #fff;
    padding: 5px 10px;
    border-radius: 3px;
    font-size: 14px;
    z-index: 20;
  }

  a {
    text-decoration: none;
    color: #333;
  }

  .movie-item-hover {
    position: absolute;
    width: 218px;
    height: 300px;
    z-index: 10;
    top: -40px;
    left: -29px;
    overflow: hidden;
    background: #fff;
    box-shadow: 0 0 16px #fff, 0 0 6px rgba(0, 0, 0, .2);
  }

  .poster-hover {
    width: 218px;
    height: 300px;
  }

  .movie-hover-info {
    padding: 16px;
    height: 150px;
    box-sizing: border-box;
    position: absolute;
    z-index: 11;
    top: 150px;
    background: #fff;
    width: 218px;
    color: #999;
    text-align: center;
  }

  .title-hover:first-child {
    font-size: 20px;
    line-height: 20px;
    margin-top: 6px;
  }

  .title-hover {
    font-size: 16px;
    line-height: 16px;
    margin-top: 12px;
    width: 100%;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .name-tags {
    color: #333;
  }

</style>