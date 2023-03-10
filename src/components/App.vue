<template>
  <md-app
    v-cloak
    md-waterfall
    md-mode="fixed"
  >
    <md-app-content class="body">
      <h1 class="animate-charcter">Franz Stimpfl's Playlist</h1>
      <div class="middle">
        <input type="text" id="searchSong" class="animate-charcter3 white" placeholder="Search for a Song or Artist">
        <button @click="searchSong" class="animate-charcter buttonSearch">Search</button>
        <div v-if="searchedSongBool">No songs can be Found</div>
        <br>
        <button @click="getEditor" class="animate-charcter buttonAdd center">Add new Song</button>
      </div>
      <router-view/>
      <div class="songs">
        <div class="middle">
          <h1 class="animate-charcter2 ">Songs</h1>
        </div>
        <Song
          v-for="(title, index) in songs"
          :key="index"
          :song="title"
          class="white"
        />
      </div>
      <div class="middle">
        <button id="first" @click="firstPage" class="animate-charcter button" :disabled="pageNum===0">First</button>
        <button id="back" @click="backPage" class="animate-charcter button" :disabled="pageNum===0">Previous</button>
        <h1 class="animate-charcter">... {{ pageNum }}  ...</h1>
        <button id="next" @click="nextPage" class="animate-charcter button" :disabled="pageNum===Math.floor(maxPage)">Next</button>
        <button id="last" @click="lastPage" class="animate-charcter button" :disabled="pageNum===Math.floor(maxPage)">Last</button>
      </div>
    </md-app-content>
  </md-app>
</template>

<script>
import Song from "@/components/Song";
import axios from "axios";
import Vue from 'vue';
import JwPagination from 'jw-vue-pagination';
import MdVuelidated from '@undecaf/vue-material-vuelidate'
import '@undecaf/vue-material-vuelidate/dist/components.css'

export default {
  name: 'App',
  components: {
    Song,
  },
  data: function () {
    return {
      formData:{
        title:"",
        author:"",
        genre:"",
        length:""
      },
      totalEl:0,
      songs: [],
      pageOfItems: [],
      numOfTitles: [],
      pageNum: 0,
      maxPage: 20,
      searchedSong: "",
      searchedSongBool: null
    }
  },
  // axios
  async mounted(){
    this.$router.push('/').catch(()=>{});
    const response = await axios.get('http://localhost:8080/api/songs?page='+this.pageNum+'&size=5')
    this.songs = response.data._embedded.songs
    console.log(this.songs)
    const response2 = await axios.get('http://localhost:8080/api/songs')
    this.totalEl = response2.data.page.totalElements
    if (this.totalEl%5===0){
      this.maxPage=this.totalEl/5-1
    } else{
      this.maxPage= (this.totalEl/5)
    }
  },

  methods: {
    async nextPage(pageNum) {
      this.pageNum+=1
      if (this.pageNum>this.maxPage){
        this.pageNum=Math.floor(this.maxPage)
      }
      const response = await axios.get('http://localhost:8080/api/songs?page='+this.pageNum+'&size=5')
      this.songs = response.data._embedded.songs
    },
    async backPage(pageNum) {
      this.pageNum-=1
      if (this.pageNum<0){
        this.pageNum=0
      }
      const response = await axios.get('http://localhost:8080/api/songs?page='+this.pageNum+'&size=5')
      this.songs = response.data._embedded.songs

    },
    async lastPage(pageNum) {
      this.pageNum=Math.floor(this.maxPage)
      const response = await axios.get('http://localhost:8080/api/songs?page='+this.pageNum+'&size=5')
      this.songs = response.data._embedded.songs

    },
    async firstPage(pageNum) {
      this.pageNum=0
      const response = await axios.get('http://localhost:8080/api/songs?page='+this.pageNum+'&size=5')
      this.songs = response.data._embedded.songs

    },
     getEditor(){
       //this.$router.push('/').catch(()=>{});
       this.$router.push({name: 'editor', params: {}})
    },
    async searchSong(){
      this.songTitle=document.getElementById('searchSong').value
      const Title = await axios.get('http://localhost:8080/api/songs/search/findByTitleContainingIgnoreCase?text='+this.songTitle+'&page=0&size=50')
      const Author = await axios.get('http://localhost:8080/api/songs/search/findByAuthorIgnoreCase?author='+ this.songTitle+'&page=0&size=50')
      if (Title.data._embedded.songs.length===0 && Author.data._embedded.songs.length!==0){
        console.log("Only Author")
        this.songs=Author.data._embedded.songs
        this.searchedSongBool=false
      }
      if (Title.data._embedded.songs.length!==0 && Author.data._embedded.songs.length===0){
        console.log("Only Title")
        this.songs=Title.data._embedded.songs
        this.searchedSongBool=false
      }
      if (Title.data._embedded.songs.length===0 && Author.data._embedded.songs.length===0){
        console.log("Nothing")
        this.searchedSongBool=true
        this.songs=null
      }
      if (Title.data._embedded.songs.length!==0 && Author.data._embedded.songs.length!==0){
        console.log("Both")
        this.songs=Title.data._embedded.songs && Author.data._embedded.songs
        this.searchedSongBool=false
      }
      console.log(this.songs)
      },
    },
}


</script>

<style scoped>
.body{
  background-color: white !important;
}
.white {
  background-color: white !important;
}
.red{
  color: red;
}
.center {
  text-align: center;
}
.middle{
  text-align: center;
}
button{
  font-size: 50px;
}
h1 { color: #111; font-family: 'Helvetica Neue', sans-serif; font-size: 75px; font-weight: bold; letter-spacing: -1px; line-height: 1; text-align: center; }
.animate-charcter
{
  text-transform: uppercase;
  background-image: linear-gradient(
      -225deg,
      #231557 0%,
      #44107a 29%,
      #ff1361 67%,
      #fff800 100%
  );
  background-size: auto auto;
  background-clip: border-box;
  background-size: 200% auto;
  color: #fff;
  background-clip: text;
  text-fill-color: transparent;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: textclip 2s linear infinite;
  display: inline-block;
  font-size: 100px;
}
.animate-charcter2
{
  text-transform: uppercase;
  background-image: linear-gradient(
      -225deg,
      #231557 0%,
      #44107a 29%,
      #ff1361 67%,
      #fff800 100%
  );
  background-size: auto auto;
  background-clip: border-box;
  background-size: 200% auto;
  color: #fff;
  background-clip: text;
  text-fill-color: transparent;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: textclip 2s linear infinite;
  display: inline-block;
  font-size: 40px;
}

.animate-charcter3
{
  background-image: linear-gradient(
      -225deg,
      #231557 0%,
      #44107a 29%,
      #ff1361 67%,
      #fff800 100%
  );
  background-size: auto auto;
  background-clip: border-box;
  background-size: 200% auto;
  color: #fff;
  background-clip: text;
  text-fill-color: transparent;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  animation: textclip 2s linear infinite;
  display: inline-block;
  font-size: 20px;
}
@keyframes textclip {
  to {
    background-position: 200% center;
  }
}
.border2{
  font-size: 1.6rem;
  display: grid;
  place-items: center;
  min-height: 200px;
  border: 8px solid;
  padding: 1rem;
  box-shadow: rgba(0, 0, 0, 0.56) 0px 22px 70px 4px;}
.full {
  border-image: linear-gradient(45deg, blue, red) 1;
}
.button {
  width: 140px;
  height: 45px;
  font-family: 'Roboto', sans-serif;
  font-size: 11px;
  text-transform: uppercase;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: #000;
  background-color: #fff;
  border: none;
  border-radius: 45px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
  margin-bottom: 50px;
}

.buttonAdd {
  width: 300px;
  height: 70px;
  font-family: 'Roboto', sans-serif;
  font-size: 25px;
  text-transform: uppercase;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: #000;
  background-color: #fff;
  border: none;
  border-radius: 45px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
  margin-bottom: 50px;
  text-align: center;
  justify-content: center;
}
.buttonSearch {
  width: 200px;
  height: 50px;
  font-family: 'Roboto', sans-serif;
  font-size: 25px;
  text-transform: uppercase;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: #000;
  background-color: #fff;
  border: none;
  border-radius: 45px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
  margin-bottom: 50px;
  text-align: center;
  justify-content: center;
}
.buttonSearch:hover {
  background-color: purple;
  box-shadow: 0px 15px 20px purple;
  color: purple;
  transform: translateY(-7px);
}
.buttonAdd:hover {
  background-color: blue;
  box-shadow: 0px 15px 20px deepskyblue;
  color: blue;
  transform: translateY(-7px);
}

.button:hover {
  background-color: blue;
  box-shadow: 0px 15px 20px red;
  color: blue;
  transform: translateY(-7px);
}
.div2 {
  margin-right: 200px;
  margin-left: 200px;
  margin-bottom: 60px;
}

input[type=text] {
  color: white;
  margin-bottom: 30px;
  width: 600px;
  height: 40px;
  /* css 3 */
  border-radius:10px;
  /* mozilla */
  -moz-border-radius:10px;
  /* webkit */
  -webkit-border-radius:10px;
}

button:disabled,
button[disabled]{
  border: 7px solid #999999;
  background-color: #cccccc;
  color: #666666;
  pointer-events: none;
}

</style>