import Vue from 'vue'
import App from "@/App";
import Router from "vue-router";
import {BootstrapVue, IconsPlugin} from 'bootstrap-vue'
import Vuelidate from 'vuelidate'
import {FontAwesomeIcon} from '@fortawesome/vue-fontawesome'
import { sync } from 'vuex-router-sync'

import {store} from "@/store/store";
import router from "@/router";

Vue.config.productionTip = false;
const originalPush = Router.prototype.push;
Router.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject);
  return originalPush.call(this, location).catch(err => err)
};
const unsync = sync(store, router)
unsync()

Vue.component('FontAwesomeIcon', FontAwesomeIcon)
Vue.use(require('vue-moment'));

Vue.use(Router);
Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(Vuelidate);

new Vue({
  render: h => h(App),
  router,
  store: store
}).$mount('#app');
