<template>
  <div class="border full middle">
    <h2 class="animate-charcter">{{ song.author }} - {{ song.title }} </h2>
    <h2 class="animate-charcter">{{ song.genre }}</h2>
    <h2 class="animate-charcter">{{ song.length }}</h2>
    <button class="buttonAdd animate-charcter" @click="editSong">Edit</button>
    <button  class="animate-charcter buttonDel" @click="deleteSong">Delete</button>
  </div>
</template>
<script>
import Vuelidate from 'vuelidate'
import axios from 'axios';
import {Song} from "@/models/Song";
import Editor from "@/components/Editor";
export default {
  name: "Song",
  components:{
    Editor,
  },
  props: {
    song: {
      type: Song,
      default: null
    },
  },
  methods:{
    getEditor(){
      this.$router.push({name: 'editor', params: {song:""}}).catch(()=>{});
    },
    async deleteSong(){
      await axios.delete(this.song._links.self.href)
          .then(response => console.log(response))
          .catch(error => console.log(error))
      window.location.reload()
    },
     editSong(){
       this.$router.push({name: 'editor', params: {song:this.song}}).catch(()=>{});
     }
    }
}
</script>
<style scoped>
.middle{
  justify-content: center;
}
.buttonAdd {
  width: 300px;
  height: 70px;
  font-family: 'Roboto', sans-serif;
  font-size: 25px;
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

.buttonAdd:hover {
  background-color: white;
  box-shadow: 0px 15px 20px lawngreen;
  color: green;
  transform: translateY(-7px);
}

.buttonDel {
  width: 300px;
  height: 70px;
  font-family: 'Roboto', sans-serif;
  font-size: 25px;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: red;
  background-color: red;
  border: none;
  border-radius: 45px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
  margin-bottom: 50px;
}

.buttonDel:hover {
  background-color: red;
  box-shadow: 0px 15px 20px red;
  color: red;
  transform: translateY(-7px);
}

div {
  margin-right: 200px;
  margin-left: 200px;
  margin-bottom: 60px;
}
.border{
  font-size: 1.6rem;
  display: grid;
  place-items: center;
  height: 450px;
  border: 8px solid;
  padding: 1rem;
  box-shadow: rgba(0, 0, 0, 0.56) 0px 22px 70px 4px;
}
.full {
  border-image: linear-gradient(45deg, blue, red) 1;
}
.animate-charcter
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
  font-size: 22px;
}

@keyframes textclip {
  to {
    background-position: 200% center;
  }
}


</style>