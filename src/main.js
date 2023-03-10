import Vue from 'vue'
import options from '@/config'
import App from '@/components/App.vue'
import '@/main.css'
import Editor from './components/Editor.vue';
import VueRouter from 'vue-router'
import routes from './routes';
Vue.use(VueRouter)

// Uncomment this when building a PWA:
// import '@/registerServiceWorker'

new Vue({
    ...options(Vue),
    el: '#app',
    render: h => h(App),
})
