<template>
  <md-app
      v-cloak
      md-waterfall
      md-mode="fixed"
  >
    <md-app-content>
      <div class="middle">
        <h1 class="animate-charcter2">Editor</h1>
        <div class="border2 full songs div2">
          <label for="fname" class="animate-charcter3 required">Title:</label><br>
          <input type="text" id="title"  class="animate-charcter3 required" v-model="song.title"  v-bind:class="{ 'error': $v.song.title.$error }"><br>
          <span class="red md-required" v-if="!$v.song.title.required">Name is required</span>
          <label for="lname" class="animate-charcter3 md-required">Author:</label><br>
          <input type="text" id="author"  class="animate-charcter3 md-required" v-model="song.author" v-bind:class="{ 'error': $v.song.author.$error }"><br>
          <span class="red md-required" v-if="!$v.song.author.required">Author is required</span>
          <div class="md-layout-item">
            <label for="fname" class="animate-charcter3 md-required" >Genre:</label><br>
            <md-field>
              <md-select v-model="song.genre" name="genres" id="genres" multiple v-bind:class="{ 'error': $v.song.genre.$error }">
                <md-option v-for="(genre,i) in this.$store.state.allGenres" :key="i" :value="genre">{{ genre }}</md-option>
              </md-select>
            </md-field>
            <span class="red md-required" v-if="!$v.song.genre.required">Genre is required</span>
          </div>
          <!--<input type="text" id="genre" class="animate-charcter3 md-required" v-model="song.genre" ><br>-->
          <label for="lname" class="animate-charcter3 md-required">Length:</label><br>
          <input type="text" id="length"  class="animate-charcter3 md-required" v-model="song.length" v-bind:class="{ 'error': $v.song.length.$error }"><br><br>
          <span class="red md-required" v-if="!$v.song.length.required">Length is required</span>
          <button @click="addSong" class="animate-charcter buttonAdd">Add new Song</button>
          <button @click="editSong" class="animate-charcter buttonAdd">Save</button>
          <button @click="cancelEditor" class="animate-charcter buttonAdd">Cancel</button>
          <input id="fileupload" type="file" name="fileupload" />
          <button id="upload-button" @click="uploadFile()"> Upload </button>
        </div>
      </div>
    </md-app-content>
  </md-app>
</template>



<script>
import axios from "axios";
import Vue from 'vue';
import Vuelidate from 'vuelidate'
import useValidate from '@vuelidate/core'
import MdVuelidated from '@undecaf/vue-material-vuelidate'
import '@undecaf/vue-material-vuelidate/dist/components.css'
import {Song} from "@/models/Song";
import song from "@/components/Song";
import {Song_Genre} from "@/models/Song_Genre";
import { required } from 'vuelidate/lib/validators'
import songModel from '@/models/Song.js'

Vue.use(Vuelidate)

export default {
  name: 'Editor',
  components: {
    Song,
    Song_Genre,
    songModel
  },
  props: {
    addTitle: String,
    addGenre: String,
    addArtist: String,
    addLength: String,
    song: {
      type: Song,
      default: () => new songModel()
    },
    song_genre: {
      type: Song_Genre,
      default: null
    }
  },
  data() {
    return {
      savingSuccessful: null,
      text: "Success",
      etag: 0,
      genres:[]
    }
  },
  validations: {
    song: {
      title: {required},
      author: {required},
      genre: {required},
      length: {required},
    }
  },
  methods: {
    uploadFile() {
      let formData = new FormData();
      formData.append("file", fileupload.files[0]);
      axios.post('http://localhost:8080/api/upload', formData)
          .then(response => {
            if (response.status === 200) {
              alert("File successfully uploaded.");
            }
          })
          .catch(error => {
            console.error(error);
          });
    }
    ,
    addSong: function () {
      const newSong = {
        title: this.song.title,
        author: this.song.author,
        genre: this.song.genre,
        length: this.song.length
      };
      axios
          .post("http://localhost:8080/api/songs", newSong)
          .then(response => console.log(response))
    },
    cancelEditor() {
      this.$router.push('/').catch(() => {
      });
    },
     editSong() {
       axios.put(this.song._links.self.href, this.song, {
        headers: {
          'content-type': 'application/json',
          'accept': 'application/json',
          'If-Match': this.song.etag,
        },
      })
          .then(
              this.$router.push('/').catch(() => {
              })
          )
    }
  },
  uploadFile() {
    this.$refs.fileInput.click();
  },
  handleFileUpload(event) {
    let file = event.target.files[0];
    let formData = new FormData();
    formData.append('file', file);
    axios.post('http://localhost:8080/api/uploads', formData).then(response => {
      console.log(response.data);
    });
  }
}

</script>
<style scoped>
.green{
  color: green;
}
.red{
  color: red;
  font-size: 60%;
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
  text-align: center;
  justify-content: center;
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
  height: 580px;
  border: 8px solid;
  padding: 1rem;
  box-shadow: rgba(0, 0, 0, 0.56) 0px 22px 70px 4px;

}

.falseDiv{
  font-size: 1.5rem;
  display: grid;
  place-items: center;
  min-height: 50px;
  border: 2px solid;
  padding: 1rem;
  color:white;
  text-transform: uppercase;
  background-color: red;
  border-color: black;
  box-shadow: rgba(0, 0, 0, 0.56) 0px 22px 70px 4px;
}
.falseDiv:hover{
  background-color: red;
  box-shadow: 0px 15px 20px red;
  transform: translateY(-7px);
}
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
  margin-bottom: 20px;
}

.buttonAdd:hover {
  background-color: blue;
  box-shadow: 0px 15px 20px deepskyblue;
  color: blue;
  transform: translateY(-7px);
}
.buttonDel {
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
}

.buttonDel:hover {
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
  background-color: #3CBC8D;
  color: white;
  margin-bottom: 10px;
  /* css 3 */
  border-radius:10px;
  /* mozilla */
  -moz-border-radius:10px;
  /* webkit */
  -webkit-border-radius:10px;
}
</style>